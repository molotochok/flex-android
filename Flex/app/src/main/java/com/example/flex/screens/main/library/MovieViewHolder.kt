package com.example.flex.screens.main.library

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val movieId: TextView = itemView.findViewById(com.example.flex.R.id.movieId)
    val name: TextView = itemView.findViewById(com.example.flex.R.id.movieName)
    val duration: TextView = itemView.findViewById(com.example.flex.R.id.movieDuration)
    val resolution: TextView = itemView.findViewById(com.example.flex.R.id.movieResolution)
    val size: TextView = itemView.findViewById(com.example.flex.R.id.movieSize)
    val poster: ImageView = itemView.findViewById(com.example.flex.R.id.moviePoster)
}