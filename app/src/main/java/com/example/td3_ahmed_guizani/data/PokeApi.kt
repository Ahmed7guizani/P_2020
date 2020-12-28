package com.example.td3_ahmed_guizani.data

import com.example.td3_ahmed_guizani.presentation.model.RestPokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokeApi {
    @get:GET("pokedex.json")
    val pokemonResponse: Call<RestPokemonResponse?>?
}