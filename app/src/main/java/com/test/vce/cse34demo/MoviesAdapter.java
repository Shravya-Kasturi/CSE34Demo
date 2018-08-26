package com.test.vce.cse34demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<String> {

    Context context;
    int resource;
    List<String> movies;

    public MoviesAdapter(@NonNull Context context, int resource, @NonNull List<String> movies) {
        super(context, resource, movies);
        this.context=context;
        this.resource=resource;
        this.movies=movies;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View v = inflater.inflate(resource,null,true);
        ImageView imageView=(ImageView)v.findViewById(R.id.movielogo);
        TextView movieName=(TextView)v.findViewById(R.id.moviename);
        switch(position)
        {
            case 0:
                imageView.setImageResource(R.mipmap.flipped);
                break;

            case 1:
                imageView.setImageResource(R.mipmap.arjunreddy);
                break;

            case 2:
                imageView.setImageResource(R.mipmap.twilight);
                break;

            case 3:
                imageView.setImageResource(R.mipmap.deadpool);
                break;

            case 4:
                imageView.setImageResource(R.mipmap.kick);
                break;

            case 5:
                imageView.setImageResource(R.mipmap.got);
                break;

            case 6:
                imageView.setImageResource(R.mipmap.mirchi);
                break;

            case 7:
                imageView.setImageResource(R.mipmap.premam);
                break;

            case 8:
                imageView.setImageResource(R.mipmap.stardust);
                break;

        }

        movieName.setText(movies.get(position));
        return v;
    }
}
