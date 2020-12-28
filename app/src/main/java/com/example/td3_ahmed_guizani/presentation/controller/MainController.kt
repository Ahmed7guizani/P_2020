package com.example.td3_ahmed_guizani.presentation.controller

import com.example.td3_ahmed_guizani.presentation.view.MainActivity
import com.google.gson.Gson
import android.content.SharedPreferences
import android.util.Log
import com.example.td3_ahmed_guizani.Constants
import com.example.td3_ahmed_guizani.presentation.model.Pokemon
import com.example.td3_ahmed_guizani.presentation.model.RestPokemonResponse
import com.example.td3_ahmed_guizani.Single
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainController(private val view: MainActivity, private val gson: Gson?, private val sharedPreferences: SharedPreferences) {
    fun onStart() {
        val pokemonList = dataFromCache
        if (pokemonList != null) {
            view.showList(pokemonList)
        } else {
            makeApiCall()
        }
    }

    private fun makeApiCall() {
        Log.d("ahmed", "before Callback: ")
        val call = Single.pokeApi?.pokemonResponse
        if (call != null) {
            call.enqueue(object : Callback<RestPokemonResponse?> {
                override fun onResponse(call: Call<RestPokemonResponse?>, response: Response<RestPokemonResponse?>) {
                    Log.d("ahmed", "inside Callback ")
                    if (response.isSuccessful && response.body() != null) {
                        val pokemonList = response.body()!!.pokemon
                        pokemonList?.let { saveList(it) }
                        view.showList(pokemonList)
                    } else {
                        view.showError()
                    }
                }

                override fun onFailure(call: Call<RestPokemonResponse?>, t: Throwable) {
                    view.showError()
                }
            })
        }
        Log.d("ahmed", "After Callback: ")
    }

    private fun saveList(pokemonList: List<Pokemon>) {
        val jsonString = gson?.toJson(pokemonList)
        sharedPreferences
                .edit()
                .putString(Constants.KEY_POKEMON_LIST, "jsonString")
                .apply()
    }

    private val dataFromCache: List<Pokemon>?
        private get() {
            val jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null)
            return if (jsonPokemon == null) {
                null
            } else {
                val listType = object : TypeToken<List<Pokemon>>() {}.type
                gson?.fromJson(jsonPokemon, listType)
            }
        }

    fun onItemClick(pokemon: Pokemon) {
        view.navigateToDetails(pokemon)
    }


}