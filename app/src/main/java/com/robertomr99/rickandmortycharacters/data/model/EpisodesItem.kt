package com.robertomr99.rickandmortycharacters.data.model

data class EpisodesItem(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)