package com.example.td3_ahmed_guizani.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td3_ahmed_guizani.R
import com.example.td3_ahmed_guizani.Single
import com.example.td3_ahmed_guizani.presentation.controller.MainController
import com.example.td3_ahmed_guizani.presentation.model.Pokemon

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mAdapter: ListAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var controller: MainController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller = Single.gson.let { Single.getSharedPreferencesInstance(applicationContext)?.let { it1 ->
                MainController(
                    this,
                        it,
                        it1
            )
            }
        }
        controller!!.onStart()
    }

    fun showList(pokemonList: List<Pokemon?>?) {
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        mAdapter = ListAdapter(pokemonList as List<Pokemon>, applicationContext, object : ListAdapter.OnItemClickListener {
            override fun onItemClick(item: Pokemon?) {
                if (item != null) {
                    controller!!.onItemClick(item)
                }
            }
        })
        recyclerView!!.adapter = mAdapter
    }

    fun showError() {
        Toast.makeText(applicationContext, "Api Error", Toast.LENGTH_SHORT).show()
    }

    fun navigateToDetails(pokemon: Pokemon) {
        val myIntent = Intent(this@MainActivity, DetailActivity::class.java)
        myIntent.putExtra("pokemonKey", Single.gson!!.toJson(pokemon))
        this@MainActivity.startActivity(myIntent)
    }
}