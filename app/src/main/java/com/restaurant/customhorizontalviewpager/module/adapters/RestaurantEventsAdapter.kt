package com.restaurant.customhorizontalviewpager.module.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.restaurant.customhorizontalviewpager.R
import kotlinx.android.synthetic.main.layout_restaurant_event.view.*

class RestaurantEventsAdapter(
    private val itemList: List<String>,
    private val onItemClickListener: (position: Int) -> Unit) : RecyclerView.Adapter<RestaurantEventsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_restaurant_event, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: String = itemList[position]
        holder.view.apply {
            val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(context).load(item).apply(requestOptions).into(event_image)
            event_image_ll.setOnClickListener {
                onItemClickListener(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = itemList.size
}