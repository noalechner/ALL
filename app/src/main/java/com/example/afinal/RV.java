package com.example.afinal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RV extends AppCompatActivity {

    RecyclerView steve;
    ArrayList<Animals> list;
    RecyclerCustomAdapter adapter;
    String[] VolunteenNames= {"anim", "farm"};
    int[] TypeVolunteenPics= {R.drawable.anim, R.drawable.farm};
    int[] AnimalRates = {4, 8};
    String AnimalName;
    int AnimalPic;
    int AnimalRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rv);
        steve =findViewById(R.id.steve);
        list = new ArrayList<>();
        for (int i = 0; i < VolunteenNames.length;i++)
        {
            list.add(new Animals(VolunteenNames[i], TypeVolunteenPics[i], AnimalRates[i] ));

        }

        adapter = new RecyclerCustomAdapter(list);

        steve.setAdapter(adapter);

        steve.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}