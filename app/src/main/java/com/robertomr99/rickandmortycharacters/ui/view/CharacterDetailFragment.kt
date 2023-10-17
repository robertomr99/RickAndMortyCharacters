package com.robertomr99.rickandmortycharacters.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.robertomr99.rickandmortycharacters.EpisodesAdapter
import com.robertomr99.rickandmortycharacters.R
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.data.model.Episodes
import com.robertomr99.rickandmortycharacters.databinding.FragmentCharacterDetailBinding
import com.robertomr99.rickandmortycharacters.ui.viewmodel.CharacterViewModel
import com.robertomr99.rickandmortycharacters.ui.viewmodel.EpisodeViewModel
import com.robertomr99.rickandmortycharacters.util.Utils
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val args:CharacterDetailFragmentArgs by navArgs()
    private val characterViewModel: CharacterViewModel by viewModels()
    private val episodesViewModel: EpisodeViewModel by viewModels()

    private lateinit var adapter: EpisodesAdapter
    private lateinit var binding: FragmentCharacterDetailBinding
    private lateinit var characterItem : CharacterItem
    private lateinit var listEpisodes : Episodes
    private lateinit var context : Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchCharacters()
        listEpisodes = Episodes()
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        context = inflater.context

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(CharacterDetailFragmentDirections.actionCharacterDetailFragmentToCharacterFragment())
        }

        characterViewModel.characterItemResponse.observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                characterItem  = it.body()!!
                searchEpisodes(characterItem)
                setDetailsView()

                episodesViewModel.episodesListResponse.observe(viewLifecycleOwner, Observer { it ->
                    it?.let{
                        listEpisodes.clear()
                        listEpisodes  = it.body()!!
                        setRecyclerViewEpisodes()
                    }
                })

                episodesViewModel.episodesResponse.observe(viewLifecycleOwner, Observer { it ->
                    it?.let{
                        listEpisodes.clear()
                        listEpisodes.add(it.body()!!)
                        setRecyclerViewEpisodes()
                    }
                })

            }
        })


        return binding.root
    }

    private fun searchCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            characterViewModel.onCreateCharacterItemById(args.idCharacter.toString())
        }
    }

    private fun searchEpisodes(characterItem: CharacterItem){
        CoroutineScope(Dispatchers.IO).launch {
            var episodes =  Utils.obtainNumFromURLs(characterItem.episode);

            if(episodes.contains(",")){
                episodesViewModel.onCreateListEpisodes(episodes)
            }else{
                episodesViewModel.onCreateEpisode(episodes)
            }
        }
    }

    private fun setDetailsView(){
        Picasso.get().load(characterItem.image).into(binding.ivDetailCharacter)
        binding.tvDetailName.text = characterItem.name
        binding.tvDetailStatus.text = characterItem.status

        when(characterItem.status.lowercase()){
            "alive" -> binding.ivDetailAlive.background = ContextCompat.getDrawable(context ,R.drawable.green_circle)
            "dead" -> binding.ivDetailAlive.background = ContextCompat.getDrawable(context,R.drawable.red_circle)
            "unknown" ->binding.ivDetailAlive.background = ContextCompat.getDrawable(context,R.drawable.gray_circle)
        }
        binding.tvDetailSpecies.text = characterItem.species
        binding.tvDetailLocation.text = characterItem.location.name
        binding.tvDetailGender.text = characterItem.gender
    }

    private fun setRecyclerViewEpisodes(){
        adapter = EpisodesAdapter(listEpisodes)
        adapter.notifyDataSetChanged()
        binding.rvEpisodes.layoutManager = LinearLayoutManager(activity)
        binding.rvEpisodes.adapter = adapter
    }

}