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
    private ListView listView;
    private ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_of_volunteen);
        listView=findViewById(R.id.listView);
        simpleList = findViewById(R.id.simpleList);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<String> list= new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_list_of_volunteen,list);
        listView.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("HostEvents");
        reference.addValueEventListener(new ValueEventListener() {


            ListView simpleList;
            String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
//            int flags[] = {R.drawable.india, R.drawable.china, R.drawable.australia, R.drawable.portugle, R.drawable.america, R.drawable.new_zealand};
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList);
                simpleList.setAdapter(CustomAdapter);

        }
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