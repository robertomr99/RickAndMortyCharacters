package com.robertomr99.rickandmortycharacters.domain.characterUseCase

import com.robertomr99.rickandmortycharacters.data.repository.CharacterRepository
import com.robertomr99.rickandmortycharacters.data.model.Character
import retrofit2.Response
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    suspend operator fun invoke(query: String): Response<Character> = repository.getCharacters(query)
}