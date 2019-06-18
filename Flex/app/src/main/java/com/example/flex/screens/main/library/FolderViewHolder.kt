package com.example.flex.screens.main.library

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class FolderViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val folderId: TextView = itemView.findViewById(com.example.flex.R.id.folderId)
    val name: TextView = itemView.findViewById(com.example.flex.R.id.folderName)
    val poster: ImageView = itemView.findViewById(com.example.flex.R.id.folderPoster)
}