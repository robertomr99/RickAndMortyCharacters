package com.robertomr99.rickandmortycharacters.data.repository

import com.robertomr99.rickandmortycharacters.data.model.Character
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.data.model.ItemsInfo
import com.robertomr99.rickandmortycharacters.data.network.APIClient
import retrofit2.Response
import javax.inject.Inject

private const val CHARACTER : String = "character/"

class CharacterRepository @Inject constructor(
    private val api: APIClient
) {
    suspend fun getCharacters(query: String): Response<Character> = api.getCharacters(CHARACTER +query)
    suspend fun getCharactersByName(name: String): Response<ItemsInfo> = api.getCharactersByName(
        CHARACTER +name)
    suspend fun getCharactersById(id: String): Response<CharacterItem> = api.getCharactersById(
        CHARACTER +id)
}