package com.example.td3_ahmed_guizani

import android.content.Context
import android.content.SharedPreferences
import com.example.td3_ahmed_guizani.data.PokeApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Single {
    private var gsonInstance: Gson? = null
    private var pokeApiInstance: PokeApi? = null
    private var sharedPreferencesInstance: SharedPreferences? = null
    val gson: Gson?
        get() {
            if (gsonInstance == null) {
                gsonInstance = GsonBuilder()
                        .setLenient()
                        .create()
            }
            return gsonInstance
        }
    val pokeApi: PokeApi?
        get() {
            if (pokeApiInstance == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                pokeApiInstance = retrofit.create(PokeApi::class.java)
            }
            return pokeApiInstance
        }

    fun getSharedPreferencesInstance(context: Context): SharedPreferences? {
        if (sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE)
        }
        return sharedPreferencesInstance
    }
}