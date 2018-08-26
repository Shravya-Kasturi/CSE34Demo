package com.test.vce.cse34demo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Review extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> reviews;
    EditText reviewText;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        listView=(ListView)findViewById(R.id.revieslist);
        reviewText=(EditText)findViewById(R.id.myreview);
        submit=(Button)findViewById(R.id.submit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Home.movie+" Reviews");
        databaseReference=FirebaseDatabase.getInstance().getReference(Home.movie);
        reviews=new ArrayList<String>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.removeEventListener(this);
                reviews.clear();
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    reviews.add(d.getValue(String.class));
                }
                listView.setAdapter(new ArrayAdapter<String>(Review.this,android.R.layout.simple_list_item_1,reviews));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(reviewText.getText().toString());
                reviews.add(reviewText.getText().toString());
                reviewText.setText("");
                listView.setAdapter(new ArrayAdapter<String>(Review.this,android.R.layout.simple_list_item_1,reviews));
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
