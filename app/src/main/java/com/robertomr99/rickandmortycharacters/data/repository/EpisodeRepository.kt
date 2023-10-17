package com.robertomr99.rickandmortycharacters.data.repository

import com.robertomr99.rickandmortycharacters.data.model.Episodes
import com.robertomr99.rickandmortycharacters.data.model.EpisodesItem
import com.robertomr99.rickandmortycharacters.data.network.APIClient
import retrofit2.Response
import javax.inject.Inject

private const val EPISODE : String = "episode/"

class EpisodeRepository @Inject constructor(
    private val api: APIClient
) {
    suspend fun getEpisodes(query: String): Response<Episodes> = api.getEpisodes(EPISODE +query)
    suspend fun getEpisodeById(query: String): Response<EpisodesItem> = api.getEpisodesById(EPISODE +query)
}