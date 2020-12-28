package com.example.td3_ahmed_guizani.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.td3_ahmed_guizani.R
import com.example.td3_ahmed_guizani.Single
import com.example.td3_ahmed_guizani.presentation.model.Pokemon

class DetailActivity : AppCompatActivity() {
    private var imgDetail: ImageView? = null
    private var nmDetail: TextView? = null
    private var hgDetail: TextView? = null
    private var wgDetail: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        imgDetail = findViewById<View>(R.id.icon_detail) as ImageView
        nmDetail = findViewById<View>(R.id.name_detail) as TextView
        hgDetail = findViewById<View>(R.id.height) as TextView
        wgDetail = findViewById<View>(R.id.weight) as TextView
        val intent = intent
        val pokemonJson = intent.getStringExtra("pokemonKey")
        val pokemon = Single.gson?.fromJson(pokemonJson, Pokemon::class.java)
        if (pokemon != null) {
            showDetail(pokemon)
        }
    }

    private fun showDetail(pokemon: Pokemon) {
        Glide.with(applicationContext).load(pokemon.img).into(imgDetail!!)
        nmDetail!!.text = pokemon.name
        hgDetail!!.text = pokemon.height
        wgDetail!!.text = pokemon.weight
    }
}