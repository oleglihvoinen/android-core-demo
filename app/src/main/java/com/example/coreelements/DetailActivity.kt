package com.example.coreelements

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val animalName = intent.getStringExtra("animal_name").orEmpty()

        val titleText = findViewById<TextView>(R.id.textViewDetail)
        titleText.text = "Selected Animal: $animalName"

        @Suppress("DEPRECATION")
        run {
            val serviceIntent = Intent(this, MyIntentService::class.java).apply {
                putExtra("animal_name", animalName)
            }
            startService(serviceIntent)
        }

        val fav = findViewById<ToggleButton>(R.id.favoriteToggle)
        fav.setOnCheckedChangeListener { _, isChecked ->
            val msg = if (isChecked) "$animalName added to favorites!" else "$animalName removed from favorites!"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        val facts = arrayOf(
            "Cats sleep 70% of their lives!",
            "Dogs can understand up to 250 words!",
            "Rabbits have almost 360° vision!",
            "Hamsters are nocturnal and love wheels!",
            "Parrots can mimic human speech!"
        )

        val factButton = findViewById<Button>(R.id.factButton)
        val factText = findViewById<TextView>(R.id.factText)
        factButton.setOnClickListener {
            val idx = (facts.indices).random()
            factText.text = facts[idx]
        }
    }
}
