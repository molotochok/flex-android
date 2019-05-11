package com.example.flex.adapters.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val movieId    = itemView.findViewById<TextView>(com.example.flex.R.id.movieId)
    val name       = itemView.findViewById<TextView>(com.example.flex.R.id.movieName)
    val duration   = itemView.findViewById<TextView>(com.example.flex.R.id.movieDuration)
    val resolution = itemView.findViewById<TextView>(com.example.flex.R.id.movieResolution)
    val size       = itemView.findViewById<TextView>(com.example.flex.R.id.movieSize)
    val poster    = itemView.findViewById<ImageView>(com.example.flex.R.id.moviePoster)
}

class FolderViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val folderId= itemView.findViewById<TextView>(com.example.flex.R.id.folderId)
    val name= itemView.findViewById<TextView>(com.example.flex.R.id.folderName)
    val poster= itemView.findViewById<ImageView>(com.example.flex.R.id.folderPoster)
}

class SettingsViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id = itemView.findViewById<TextView>(com.example.flex.R.id.settings_id)
    val name= itemView.findViewById<TextView>(com.example.flex.R.id.settings_name)
    val icon= itemView.findViewById<ImageView>(com.example.flex.R.id.settings_icon)
}

class DownloadingMovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieId)
    val name= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieName)
    val speed= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieSpeed)
    val speedType = itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieSpeedType)
    val currSize= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieCurrSize)
    val currSizeType= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieCurrSizeType)
    val maxSize= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieMaxSize)
    val maxSizeType= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieMaxSizeType)
    val timeLeft = itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieTimeLeft)
    val timeLeftType = itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieTimeLeftType)
    val poster= itemView.findViewById<ImageView>(com.example.flex.R.id.downloadMoviePoster)
    val progress = itemView.findViewById<TextView>(com.example.flex.R.id.downloadProgress)
}

class DownloadedMovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieId)
    val name= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieName)
    val currSize= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieCurrSize)
    val currSizeType= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieCurrSizeType)
    val maxSize= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieMaxSize)
    val maxSizeType= itemView.findViewById<TextView>(com.example.flex.R.id.downloadMovieMaxSizeType)
    val poster= itemView.findViewById<ImageView>(com.example.flex.R.id.downloadMoviePoster)
}