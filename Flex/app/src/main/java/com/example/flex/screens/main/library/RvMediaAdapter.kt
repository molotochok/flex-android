package com.example.flex.screens.main.library


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide


class RvMediaAdapter(private val mediaList: List<Media>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
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

                holder.movieId.text = media.id.toString()
                holder.name.text = media.name
                holder.duration.text = media.duration
                holder.resolution.text = media.resolution
                holder.size.text = media.size

                Glide
                    .with(holder.itemView)
                    .load(media.thumbnail)
                    .into(holder.poster)
            }
            MediaType.FOLDER -> {
                val media = mediaList[position] as Folder
                val holder = viewHolder as FolderViewHolder

                holder.folderId.text = media.id.toString()
                holder.name.text = media.name

                Glide
                    .with(holder.itemView)
                    .load(media.posterPath)
                    .into(holder.poster)
            }
        }
    }
}