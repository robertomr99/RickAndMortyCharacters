package com.robertomr99.rickandmortycharacters.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertomr99.rickandmortycharacters.data.model.Episodes
import com.robertomr99.rickandmortycharacters.data.model.EpisodesItem
import com.robertomr99.rickandmortycharacters.domain.episodeUseCase.GetEpisodeByIdUseCase
import com.robertomr99.rickandmortycharacters.domain.episodeUseCase.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getEpisodeUseCase: GetEpisodesUseCase,
    private val getEpisodeByIdUseCase : GetEpisodeByIdUseCase,
): ViewModel(){

    val episodesListResponse = MutableLiveData<Response<Episodes>>()
    val episodesResponse = MutableLiveData<Response<EpisodesItem>>()

    fun onCreateListEpisodes(episodesN: String){
        viewModelScope.launch {
        val listEpisodes = getEpisodeUseCase(episodesN)

            listEpisodes?.let {
                episodesListResponse.postValue(listEpisodes)
            }
        }
    }


    fun onCreateEpisode(episodeN: String){
        viewModelScope.launch {
        val episode = getEpisodeByIdUseCase(episodeN)

            episode?.let {
                episodesResponse.postValue(episode)
            }
        }
    }

}