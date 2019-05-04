package com.example.flex.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.flex.models.*
import com.squareup.picasso.Picasso
import java.lang.IllegalArgumentException


class RvAdapter(private val mediaList: ArrayList<Media>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun getItemViewType(position: Int): Int {
        return mediaList[position].mediaType.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        when(viewType){
            MediaType.MOVIE.value -> {
                val v = LayoutInflater
                    .from(parent.context)
                    .inflate(com.example.flex.R.layout.adapter_movie_layout, parent, false)

                return MovieViewHolder(v)
            }
            MediaType.FOLDER.value ->{
                val v = LayoutInflater
                    .from(parent.context)
                    .inflate(com.example.flex.R.layout.adapter_folder_layout, parent, false)

                return FolderViewHolder(v)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when(mediaList[position].mediaType){
            MediaType.MOVIE -> {
                val media = mediaList[position] as Movie
                val holder = viewHolder as MovieViewHolder

                holder.movieId?.text    = media.id.toString()
                holder.name?.text       = media.name
                holder.duration?.text   = media.duration.toString()
                holder.resolution?.text = media.resolution
                holder.size?.text       = media.size

                Picasso
                    .get()
                    .load(media.posterPath)
                    .into(holder.poster)
            }
            MediaType.FOLDER -> {
                val media = mediaList[position] as Folder
                val holder = viewHolder as FolderViewHolder

                holder.folderId?.text = media.id.toString()
                holder.name?.text = media.name

                Picasso
                    .get()
                    .load(media.posterPath)
                    .into(holder.poster)
            }
        }
    }


    class MovieViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        val movieId    = itemView.findViewById<TextView>(com.example.flex.R.id.movieId)
        val name       = itemView.findViewById<TextView>(com.example.flex.R.id.movieName)
        val duration   = itemView.findViewById<TextView>(com.example.flex.R.id.movieDuration)
        val resolution = itemView.findViewById<TextView>(com.example.flex.R.id.movieResolution)
        val size       = itemView.findViewById<TextView>(com.example.flex.R.id.movieSize)
        val poster     = itemView.findViewById<ImageView>(com.example.flex.R.id.moviePoster)
    }

    class FolderViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        val folderId    = itemView.findViewById<TextView>(com.example.flex.R.id.folderId)
        val name       = itemView.findViewById<TextView>(com.example.flex.R.id.folderName)
        val poster     = itemView.findViewById<ImageView>(com.example.flex.R.id.folderPoster)
    }
}