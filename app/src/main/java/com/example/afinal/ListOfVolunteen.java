package com.example.afinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfVolunteen extends AppCompatActivity {
    private ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_of_volunteen);
        simpleList = findViewById(R.id.simpleList);
        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listview, R.id.textView,countryList);
        simpleList.setAdapter(arrayAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("HostEvents");
        reference.addValueEventListener(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



//                list.clear();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    list.add(snapshot1.getValue().toString());
//
//                }
//                adapter.notifyDataSetChanged();
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}