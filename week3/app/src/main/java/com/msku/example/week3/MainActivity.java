package com.msku.example.week3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts.add(ornekPostOlustur("Post 1", "İlk Paylaşım", R.drawable.img));
        posts.add(ornekPostOlustur("Post 2", "2.Post", R.drawable.images));
        posts.add(ornekPostOlustur("Post 3", "3.Post.", R.drawable.download));



        ListView listView = findViewById(R.id.listView);
        PostAdapter postAdapter = new PostAdapter(this, posts);
        listView.setAdapter(postAdapter);
    }

    private Post ornekPostOlustur(String title, String message, int imageResource) {
        Post post = new Post();
        post.setMessage(message);
        post.setImage(BitmapFactory.decodeResource(getResources(), imageResource));
        return post;
    }
}