package com.example.jasy.Helpers.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jasy.Model.ApodModel
import com.example.jasy.R
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class ApodListAdapter(private val list: List<ApodModel>, private val onClickListenerCallback: (ApodModel) -> Unit): RecyclerView.Adapter<ApodListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apod = list[position]
        holder.title.text = apod.title
        holder.date.text = apod.date
        Picasso.get().load(apod.url).into(holder.imageView)
        holder.cardView.setOnClickListener {
            val currentModel = list[position]
            onClickListenerCallback(currentModel)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.apod_image_view)
        val title: TextView = itemView.findViewById(R.id.apod_text_view)
        val date: TextView = itemView.findViewById(R.id.apod_text_view_date)
        val cardView: MaterialCardView = itemView.findViewById(R.id.apod_card_view)
    }
}