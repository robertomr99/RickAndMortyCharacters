package com.robertomr99.rickandmortycharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robertomr99.rickandmortycharacters.data.model.Episodes

class EpisodesAdapter(private val episodes: Episodes):RecyclerView.Adapter<EpisodesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EpisodesViewHolder(layoutInflater.inflate(R.layout.item_episode, parent, false))
    }
    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        val item = episodes[position]
        item?.let {
            holder.bind(item)
        }
    }


}