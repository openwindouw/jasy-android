package com.example.jasy.helpers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jasy.model.ApodModel
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
        val imageView: ImageView = itemView.findViewById(R.id.apodImageView)
        val title: TextView = itemView.findViewById(R.id.apodTextView)
        val date: TextView = itemView.findViewById(R.id.apodTextViewDate)
        val cardView: MaterialCardView = itemView.findViewById(R.id.apodCardView)
    }
}