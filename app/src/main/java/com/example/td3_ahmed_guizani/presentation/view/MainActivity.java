package com.example.td3_ahmed_guizani.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.td3_ahmed_guizani.R;
import com.example.td3_ahmed_guizani.presentation.controller.MainController;
import com.example.td3_ahmed_guizani.presentation.model.Pokemon;
import com.google.gson.GsonBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                 new GsonBuilder()
                        .setLenient()
                        .create(),
                 getSharedPreferences("application_esiea", Context.MODE_PRIVATE)

        );
        controller.onStart();
    }



    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new ListAdapter(pokemonList, getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }





    public void showError() {
        Toast.makeText(getApplicationContext(), "Api Error", Toast.LENGTH_SHORT).show();
    }


}