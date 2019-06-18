package com.example.flex.screens.main.downloads

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class DownloadingMovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieId)
    val name: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieName)
    val speed: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieSpeed)
    val speedType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieSpeedType)
    val currSize: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieCurrSize)
    val currSizeType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieCurrSizeType)
    val maxSize: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieMaxSize)
    val maxSizeType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieMaxSizeType)
    val timeLeft: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieTimeLeft)
    val timeLeftType: TextView = itemView.findViewById(com.example.flex.R.id.downloadMovieTimeLeftType)
    val poster: ImageView = itemView.findViewById(com.example.flex.R.id.downloadMoviePoster)
    val progress: ProgressBar = itemView.findViewById(com.example.flex.R.id.downloadProgress)
}