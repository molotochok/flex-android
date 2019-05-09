package com.example.flex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.flex.adapters.ViewHolders.SettingsViewHolder
import com.example.flex.models.Settings
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.example.flex.R

class RvSettingsAdapter (private val settingsList: ArrayList<Settings>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return settingsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(com.example.flex.R.layout.adapter_settings_layout, parent, false)

        return SettingsViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        val media = settingsList[position]
        val holder = viewHolder as SettingsViewHolder

        holder.id?.text = media.id.toString()
        holder.name?.text = media.name
        Picasso
            .get()
            .load(media.iconDrawableId)
            .placeholder(media.iconDrawableId)
            .into(holder.icon)
    }
}