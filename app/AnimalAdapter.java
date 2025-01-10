package com.example.recycleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class AnimalAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Animals> list;
    private int layout;

    public AnimalAdapter(@NonNull Context context, int layout, @NonNull ArrayList<Animals> list)
    {
        super(context, layout, list);
        this.context=context;
        this.layout=layout;
        this.list=list;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        LayoutInflater layoutInflater= ((AppCompatActivity)context).getLayoutInflater();

        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(layout,parent,false);


        Animals Animals=this.list.get(position);


        TextView tvName=view.findViewById(R.id.tvName);
        TextView tvRate=view.findViewById(R.id.tvRate);
        ImageView ivPic=view.findViewById(R.id.ivPic);



        tvName.setText(Animals.getAnimalNames());
        tvRate.setText(String.valueOf(Animals.getAnimalRates()));
        ivPic.setImageResource(Animals.getAnimalPics());

        return view;
    }


}
