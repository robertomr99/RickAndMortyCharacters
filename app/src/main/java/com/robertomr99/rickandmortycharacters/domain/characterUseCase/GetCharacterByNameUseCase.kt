package com.robertomr99.rickandmortycharacters.domain.characterUseCase

import com.robertomr99.rickandmortycharacters.data.repository.CharacterRepository
import com.robertomr99.rickandmortycharacters.data.model.ItemsInfo
import retrofit2.Response
import javax.inject.Inject

class GetCharacterByNameUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    suspend operator fun invoke(name: String): Response<ItemsInfo> = repository.getCharactersByName(name)
}