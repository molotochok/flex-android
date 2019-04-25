package com.example.flex.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.flex.models.*
import com.squareup.picasso.Picasso


class RvAdapter(val movieList: ArrayList<Movie>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(com.example.flex.R.layout.adapter_item_layout, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.index?.text = movieList[p1].index.toString()
        p0.name?.text = movieList[p1].name
        p0.duration?.text = movieList[p1].duration.toString()
        p0.resolution?.text = movieList[p1].resolution
        p0.size?.text = movieList[p1].size

        Picasso
            .get()
            .load(movieList[p1].posterPath)
            .into(p0.poster)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val index      = itemView.findViewById<TextView>(com.example.flex.R.id.movieIndex)
        val name       = itemView.findViewById<TextView>(com.example.flex.R.id.movieName)
        val duration   = itemView.findViewById<TextView>(com.example.flex.R.id.movieDuration)
        val resolution = itemView.findViewById<TextView>(com.example.flex.R.id.movieResolution)
        val size       = itemView.findViewById<TextView>(com.example.flex.R.id.movieSize)
        val poster     = itemView.findViewById<ImageView>(com.example.flex.R.id.moviePoster)
    }
}