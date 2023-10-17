package com.robertomr99.rickandmortycharacters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem


class CharacterAdapter(private val result: ArrayList<CharacterItem>, private val onItemSelected:(CharacterItem) -> Unit):RecyclerView.Adapter<CharacterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }
    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = result?.get(position)
        item?.let {
            holder.bind(item, onItemSelected)
        }
    }


}