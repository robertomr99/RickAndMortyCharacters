package com.robertomr99.rickandmortycharacters.data.network

import com.robertomr99.rickandmortycharacters.data.model.Character
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.data.model.Episodes
import com.robertomr99.rickandmortycharacters.data.model.EpisodesItem
import com.robertomr99.rickandmortycharacters.data.model.ItemsInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIClient {

    // CHARACTER API-CALLS

    @GET
    suspend fun getCharacters(@Url url: String): Response<Character>
    @GET
    suspend fun getCharactersById(@Url url: String): Response<CharacterItem>
    @GET
    suspend fun getCharactersByName(@Url url: String): Response<ItemsInfo>

    // EPISODES API-CALLS

    @GET
    suspend fun getEpisodes(@Url url: String): Response<Episodes>
    @GET
    suspend fun getEpisodesById(@Url url: String): Response<EpisodesItem>

}