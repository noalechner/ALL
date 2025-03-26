package com.example.afinal;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.zip.Inflater;

public class CustomAdapter extends AppCompatActivity {
    Context context;
    String customList[];
    int flags[];
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] customList) {
        this.context = context;
        this.customList = customList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return customList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
////        view = inflter.inflate(R.layout.activity_listview, null);
////        TextView country = (TextView)           view.findViewById(R.id.textView);
////        ImageView icon = (ImageView) view.findViewById(R.id.icon);
////        country.setText(countryList[i]);
////        icon.setImageResource(flags[i]);
//        return view;
//    }
}