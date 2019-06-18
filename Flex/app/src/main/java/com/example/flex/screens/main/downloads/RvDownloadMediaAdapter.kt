package com.example.flex.screens.main.downloads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.IllegalArgumentException

class RvDownloadMediaAdapter (private val list: ArrayList<DownloadMedia>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].downloadStatus.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            DownloadStatus.Downloaded.value -> {
                val v = LayoutInflater
                    .from(parent.context)
                    .inflate(com.example.flex.R.layout.adapter_downloaded_movie_layout, parent, false)

                return DownloadedMovieViewHolder(v)
            }
            DownloadStatus.Downloading.value -> {
                val v = LayoutInflater
                    .from(parent.context)
                    .inflate(com.example.flex.R.layout.adapter_download_movie_layout, parent, false)

                return DownloadingMovieViewHolder(v)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when(list[position].downloadStatus)
        {
            DownloadStatus.Downloaded -> {
                val media = list[position] as DownloadedMovie
                val holder = viewHolder as DownloadedMovieViewHolder

                holder.id?.text           = media.id.toString()
                holder.name?.text         = media.name
                holder.currSize?.text     = media.currSize.toString()
                holder.currSizeType?.text = media.currSizeType
                holder.maxSize?.text      = media.maxSize.toString()
                holder.maxSizeType?.text  = media.maxSizeType

                Glide
                    .with(holder.itemView)
                    .load(media.posterPath)
                    .into(holder.poster)
            }
            DownloadStatus.Downloading -> {
                val media = list[position] as DownloadingMovie
                val holder = viewHolder as DownloadingMovieViewHolder

                holder.id?.text           = media.id.toString()
                holder.name?.text         = media.name
                holder.speed?.text        = media.speed.toString()
                holder.speedType?.text    = media.speedType
                holder.currSize?.text     = media.currSize.toString()
                holder.currSizeType?.text = media.currSizeType
                holder.maxSize?.text      = media.maxSize.toString()
                holder.maxSizeType?.text  = media.maxSizeType
                holder.timeLeft?.text     = media.timeLeft.toString()
                holder.timeLeftType?.text = media.timeLeftType
                holder.progress?.progress = media.progress

                Glide
                    .with(holder.itemView)
                    .load(media.posterPath)
                    .into(holder.poster)
            }
        }

    }
}