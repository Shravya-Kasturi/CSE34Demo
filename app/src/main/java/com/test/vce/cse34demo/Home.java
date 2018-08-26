package com.test.vce.cse34demo;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ListView listView;
    ArrayList<String> movieNames;
    MoviesAdapter moviesAdapter;
    static String movie="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView=(ListView)findViewById(R.id.moviesList);
        movieNames=new ArrayList<String>();
        movieNames.add("Flipped");
        movieNames.add("Arjun Reddy");
        movieNames.add("Twilight");
        movieNames.add("Dead Pool");
        movieNames.add("kick buttowski");
        movieNames.add("GOT");
        movieNames.add("Mirchi");
        movieNames.add("Premam");
        movieNames.add("Star Dust");

        moviesAdapter=new MoviesAdapter(Home.this,R.layout.moviesview,movieNames);
        listView.setAdapter(moviesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movie=movieNames.get(position);
                startActivity(new Intent(Home.this,Review.class));
            }
        });
    }
}
