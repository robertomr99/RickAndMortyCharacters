package com.robertomr99.rickandmortycharacters.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.robertomr99.rickandmortycharacters.R
import com.robertomr99.rickandmortycharacters.databinding.FragmentStartBinding

private lateinit var binding: FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentStartBinding.inflate(inflater, container, false)

        binding.bStart.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_characterFragment)
        }

        return binding.root
    }

}