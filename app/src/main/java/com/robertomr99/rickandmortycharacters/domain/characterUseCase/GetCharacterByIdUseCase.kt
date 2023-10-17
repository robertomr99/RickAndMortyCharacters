package com.robertomr99.rickandmortycharacters.domain.characterUseCase

import com.robertomr99.rickandmortycharacters.data.repository.CharacterRepository
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import retrofit2.Response
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    suspend operator fun invoke(query: String): Response<CharacterItem> = repository.getCharactersById(query)
}