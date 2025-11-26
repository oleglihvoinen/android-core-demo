package com.example.coreelements

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: AnimalAdapter
    private lateinit var allAnimals: ArrayList<Animal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        searchView = findViewById(R.id.searchView)

        val btnSendBroadcast = findViewById<Button>(R.id.btnSendBroadcast)
        val btnStartIntentService = findViewById<Button>(R.id.btnStartIntentService)

        // BUTTON LOGIC

        btnSendBroadcast.setOnClickListener {
            val intent = Intent("MY_CUSTOM_ACTION")
            intent.putExtra("msg", "Hello from MainActivity!")
            sendBroadcast(intent)
            Toast.makeText(this, "Broadcast Sent", Toast.LENGTH_SHORT).show()
        }

        btnStartIntentService.setOnClickListener {
            val intent = Intent(this, MyIntentService::class.java)
            intent.putExtra("task", "run_job")
            startService(intent)
            Toast.makeText(this, "IntentService Started", Toast.LENGTH_SHORT).show()
        }

        // Make search text white for dark mode
        val id = searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val searchEditText = searchView.findViewById<EditText>(id)

        searchEditText?.apply {
            setTextColor(Color.BLACK)
            setHintTextColor(Color.DKGRAY)
        }


        // ANIMAL LIST
        allAnimals = arrayListOf(
            Animal("Cat", R.drawable.cat, "Cats love naps and sunlight."),
            Animal("Dog", R.drawable.dog, "Dogs are loyal and friendly."),
            Animal("Rabbit", R.drawable.rabbit, "Rabbits love carrots."),
            Animal("Hamster", R.drawable.hamster, "Hamsters enjoy running wheels."),
            Animal("Parrot", R.drawable.parrot, "Parrots can mimic human speech.")
        )

        adapter = AnimalAdapter(this, allAnimals)
        listView.adapter = adapter

        // OPEN DETAIL ACTIVITY
        listView.setOnItemClickListener { _, _, position, _ ->
            val animal = adapter.getItem(position)
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("animal_name", animal?.name)
            i.putExtra("animal_info", animal?.description)
            i.putExtra("animal_image", animal?.imageResId)
            startActivity(i)
        }

        // SEARCH FILTER
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(text: String?): Boolean {
                val filtered = allAnimals.filter {
                    it.name.contains(text ?: "", true)
                }
                adapter = AnimalAdapter(this@MainActivity, ArrayList(filtered))
                listView.adapter = adapter
                return true
            }
        })
    }
}
