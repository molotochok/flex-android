package com.example.flex.screens.main.settings

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class SettingsViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
    val id: TextView = itemView.findViewById(com.example.flex.R.id.settings_id)
    val name: TextView = itemView.findViewById(com.example.flex.R.id.settings_name)
    val icon: ImageView = itemView.findViewById(com.example.flex.R.id.settings_icon)
}