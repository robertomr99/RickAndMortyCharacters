package com.robertomr99.rickandmortycharacters.domain.episodeUseCase

import com.robertomr99.rickandmortycharacters.data.repository.EpisodeRepository
import com.robertomr99.rickandmortycharacters.data.model.EpisodesItem
import retrofit2.Response
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val repository: EpisodeRepository
){
    suspend operator fun invoke(query: String): Response<EpisodesItem> = repository.getEpisodeById(query)
}