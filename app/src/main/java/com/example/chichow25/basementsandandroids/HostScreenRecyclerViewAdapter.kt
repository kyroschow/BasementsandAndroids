package com.example.chichow25.basementsandandroids

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chichow25.basementsandandroids.databinding.HostItemBinding

class HostScreenRecyclerViewAdapter(private val names: List<String>): RecyclerView.Adapter<HostScreenRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(HostItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: Holder, pos: Int) {
        holder.binding.apply {
            position = pos
            name = names[pos]
            executePendingBindings()
        }
    }

    fun onRemoveClick(view: View, pos: Int) {
        //TODO: remove user from game and screen
    }

    fun onAcceptClick(view: View, pos: Int) {
        //TODO: accept user and add to game
    }

    fun onDeclineClick(view: View, pos: Int) {
        //TODO: remove user from game
    }

    class Holder(val binding: HostItemBinding): RecyclerView.ViewHolder(binding.root)
}