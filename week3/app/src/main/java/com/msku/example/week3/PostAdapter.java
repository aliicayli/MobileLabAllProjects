package com.msku.example.week3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = posts.get(position);

        View view = View.inflate(context, R.layout.list_item_post, null);

        ImageView imageView = view.findViewById(R.id.imgPost);
        TextView textView = view.findViewById(R.id.txtPostMessage);

        imageView.setImageBitmap(post.getImage());
        textView.setText(post.getMessage());

        return view;
    }
}
