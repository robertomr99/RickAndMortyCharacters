package com.robertomr99.rickandmortycharacters.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertomr99.rickandmortycharacters.data.model.Character
import com.robertomr99.rickandmortycharacters.data.model.CharacterItem
import com.robertomr99.rickandmortycharacters.data.model.ItemsInfo
import com.robertomr99.rickandmortycharacters.domain.characterUseCase.GetCharacterByIdUseCase
import com.robertomr99.rickandmortycharacters.domain.characterUseCase.GetCharacterByNameUseCase
import com.robertomr99.rickandmortycharacters.domain.characterUseCase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getCharacterByNameUseCase: GetCharacterByNameUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel(){

    val charactersResponse = MutableLiveData<Response<Character>>()
    val characterListItemResponse = MutableLiveData<Response<ItemsInfo>>()
    val characterItemResponse = MutableLiveData<Response<CharacterItem>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreateList(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val query = "1,2,3,4,5,6,7,8,9,10,11,12"
            val listResult = getCharacterUseCase(query)

            listResult?.let {
                charactersResponse.postValue(listResult)
                isLoading.postValue(false)
            }
        }
    }

    fun onCreateCharacterByName(characterName: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val character = getCharacterByNameUseCase("?name=$characterName")
            character?.let {
                characterListItemResponse.postValue(character)
                isLoading.postValue(false)
            }
        }
    }


    fun onCreateCharacterItemById(characterId: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val character = getCharacterByIdUseCase(characterId)
            character?.let {
                characterItemResponse.postValue(character)
                isLoading.postValue(false)
            }
        }
    }

}
