package com.robertomr99.rickandmortycharacters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.robertomr99.rickandmortycharacters.data.model.Episodes
import com.robertomr99.rickandmortycharacters.data.model.EpisodesItem
import com.robertomr99.rickandmortycharacters.databinding.ItemEpisodeBinding

class EpisodesViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemEpisodeBinding.bind(view)

    fun bind(episode: EpisodesItem){
        binding.tvEpisodeName.text = episode.name
    }
}