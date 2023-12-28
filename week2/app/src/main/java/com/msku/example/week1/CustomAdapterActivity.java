package com.msku.example.week1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_adapter);


        List<Animal> animalList = new ArrayList<>(); // Replace this with your list of animals
        animalList.add(new Animal("Lion", R.drawable.lion_image));
        animalList.add(new Animal("Elephant", R.drawable.elephant));
        animalList.add(new Animal("Tiger", R.drawable.tiger_image));
        animalList.add(new Animal("Giraffe", R.drawable.giraffe_image));
        animalList.add(new Animal("Donkey", R.drawable.download));

        ListView listView = findViewById(R.id.myListView);
        AnimalAdapter adapter = new AnimalAdapter(this, animalList);
        listView.setAdapter(adapter);
    }
}

