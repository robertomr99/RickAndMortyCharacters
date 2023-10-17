package com.robertomr99.rickandmortycharacters.domain.episodeUseCase

import com.robertomr99.rickandmortycharacters.data.repository.EpisodeRepository
import com.robertomr99.rickandmortycharacters.data.model.Episodes
import retrofit2.Response
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val repository: EpisodeRepository
){
    suspend operator fun invoke(query: String): Response<Episodes> = repository.getEpisodes(query)
}