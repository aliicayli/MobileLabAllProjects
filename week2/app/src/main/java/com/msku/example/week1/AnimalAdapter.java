package com.msku.example.week1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Animal> animalList;

    public AnimalAdapter(Activity activity, List<Animal> animals){
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.animalList = animals;
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int i) {
        return animalList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Animal animal = animalList.get(i);

        View row = layoutInflater.inflate(R.layout.listview_row, null);
        TextView textView = row.findViewById(R.id.textViewRow);
        ImageView imageView = row.findViewById(R.id.imageViewRow); // Use the correct ID for the ImageView
        textView.setText(animal.getText());
        imageView.setImageResource(animal.getImage());

        return row;
    }
}
