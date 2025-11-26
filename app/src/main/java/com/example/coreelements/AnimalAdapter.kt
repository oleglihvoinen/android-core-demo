package com.example.coreelements

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class AnimalAdapter(
    context: Context,
    private val items: ArrayList<Animal>
) : ArrayAdapter<Animal>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        val item = getItem(position)
        val name = view.findViewById<TextView>(R.id.textView)
        val image = view.findViewById<ImageView>(R.id.imageView)

        name.text = item?.name ?: ""
        image.setImageResource(item?.imageResId ?: 0)

        return view
    }
}
