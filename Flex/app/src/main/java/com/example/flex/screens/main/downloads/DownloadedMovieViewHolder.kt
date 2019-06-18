package com.example.flex.screens.main.downloads

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class DownloadedMovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieId)
    val name: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieName)
    val currSize: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieCurrSize)
    val currSizeType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieCurrSizeType)
    val maxSize: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieMaxSize)
    val maxSizeType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieMaxSizeType)
    val poster: ImageView = itemView.findViewById(com.example.flex.R.id.downloadMoviePoster)
}