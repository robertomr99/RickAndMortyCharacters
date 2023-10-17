package com.robertomr99.rickandmortycharacters.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.robertomr99.rickandmortycharacters.CharacterAdapter
import com.robertomr99.rickandmortycharacters.R
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.databinding.FragmentCharacterBinding
import com.robertomr99.rickandmortycharacters.ui.viewmodel.CharacterViewModel
import com.robertomr99.rickandmortycharacters.util.Constants
import com.robertomr99.rickandmortycharacters.util.Utils.Companion.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterFragment : Fragment(), SearchView.OnQueryTextListener{

    private lateinit var binding: FragmentCharacterBinding
    private lateinit var adapter: CharacterAdapter
    private val characterViewModel: CharacterViewModel by viewModels()
    private var notFound: Boolean = false
    private var searchCharacter: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(CharacterFragmentDirections.actionCharacterFragmentToStartFragment())
        }

        initListComponents()
        return binding.root
    }

    private fun searchCharacters(){
        characterViewModel.onCreateList()
    }

    private fun searchCharacterByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            characterViewModel.onCreateCharacterByName(query)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            val lowercaseQuery = query.lowercase()
            searchCharacterByName(lowercaseQuery)
            context?.let { hideKeyboard(it, binding.svCharacter) }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun initListComponents(){
        binding.svCharacter.setOnQueryTextListener(this)
        searchCharacters()

        characterViewModel.charactersResponse.observe(viewLifecycleOwner , Observer { it ->

            it.let{
                val characterList : ArrayList<CharacterItem> = it.body()!!
                adapter = CharacterAdapter(characterList){ character ->
                    onItemSelected(
                        character
                    )
                }
                adapter.notifyDataSetChanged()
            }

            characterViewModel.isLoading.observe(viewLifecycleOwner, Observer{
                binding.pbCharacter.isVisible = it
            })

            binding.rvCharacters.layoutManager = LinearLayoutManager(activity)
            binding.rvCharacters.adapter = adapter
        })

        characterViewModel.characterListItemResponse.observe(viewLifecycleOwner, Observer { it ->

            if(it.code() != Constants.NOT_FOUND_CODE){
                notFound = false
                searchCharacter = true

                it.let{

                    val characterList : ArrayList<CharacterItem> = it.body()!!.results

                    adapter = CharacterAdapter(characterList){ character ->
                        onItemSelected(
                            character
                        )
                    }
                    adapter.notifyDataSetChanged()
                }

                characterViewModel.isLoading.observe(viewLifecycleOwner, Observer{
                    binding.pbCharacter.isVisible = it
                })

                binding.rvCharacters.layoutManager = LinearLayoutManager(activity)
                binding.rvCharacters.adapter = adapter

            }else{
                notFound = true
            }

            showNotFound()
        })
    }

    private fun onItemSelected(characterItem : CharacterItem) {
        notFound = false
        findNavController().navigate(CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(idCharacter = characterItem.id))
    }

    private fun showNotFound() {
        if(notFound) {
            val sbError = Snackbar.make(
                binding.root,
                R.string.character_not_found, Snackbar.ANIMATION_MODE_SLIDE
            )
                .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red_dead))
            sbError.show()
        }
    }
}