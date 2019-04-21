package com.example.flex.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.flex.R
import com.example.flex.models.*

class RvAdapter(val userList: ArrayList<TestModel>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_item_layout, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.name?.text = userList[p1].name
        p0.count?.text = userList[p1].count.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.tvName);
        val count = itemView.findViewById<TextView>(R.id.tvCount);
    }
}