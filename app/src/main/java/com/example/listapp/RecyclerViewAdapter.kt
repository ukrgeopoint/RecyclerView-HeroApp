package com.example.listapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.listapp.data.HeroItem

class RecyclerViewAdapter(val items: ArrayList<HeroItem>, val onClick: (String) -> Unit) : RecyclerView.Adapter<RecyclerViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_list_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = items[position].name

        Glide
            .with(holder.itemView)
            .load(items[position].images.lg)
            .into(holder.image)

        holder.itemView.setOnClickListener{
            onClick("${items[position].name}")
        }
    }

}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title = view.findViewById<TextView>(R.id.list_title)
    val image = view.findViewById<ImageView>(R.id.list_image)
}