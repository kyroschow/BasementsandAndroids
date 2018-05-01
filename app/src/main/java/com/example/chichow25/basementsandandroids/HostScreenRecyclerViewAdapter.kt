package com.example.chichow25.basementsandandroids

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chichow25.basementsandandroids.databinding.HostItemBinding

class HostScreenRecyclerViewAdapter(private val hostInfoList: List<HostInfo>): RecyclerView.Adapter<HostScreenRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(HostItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = hostInfoList.size

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.binding.apply {
            position = pos
            hostInfo = hostInfoList[pos]
            adapter = this@HostScreenRecyclerViewAdapter
            executePendingBindings()
        }
    }

    fun onRemoveClick(view: View, pos: Int) {
        //TODO: remove user from game and screen
        onDeclineClick(view, pos) //decline before removing
    }

    fun onAcceptClick(view: View, pos: Int) {
        //TODO: accept user and add to game
    }

    fun onDeclineClick(view: View, pos: Int) {
        //TODO: remove user from game
    }

    class Holder(val binding: HostItemBinding): RecyclerView.ViewHolder(binding.root)

    data class HostInfo(val name: String, val iconSrc: String)
}