package com.robertomr99.rickandmortycharacters

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemCharacterBinding.bind(view)

    fun bind(characterItem: CharacterItem, onItemSelected:(CharacterItem) -> Unit){
        binding.tvName.text = characterItem.name
        binding.tvStatus.text = characterItem.status
        binding.tvSpecies.text = characterItem.species

        when(characterItem.status.lowercase()){
            "alive" -> binding.ivAlive.background = ContextCompat.getDrawable(itemView.context ,R.drawable.green_circle)
            "dead" -> binding.ivAlive.background = ContextCompat.getDrawable(itemView.context ,R.drawable.red_circle)
            "unknown" -> binding.ivAlive.background = ContextCompat.getDrawable(itemView.context ,R.drawable.gray_circle)
        }

        Picasso.get().load(characterItem.image).into(binding.ivCharacter)

        itemView.setOnClickListener{
            onItemSelected(characterItem)
        }
    }
}