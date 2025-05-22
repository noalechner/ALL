package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class TopicAnimals extends AppCompatActivity {
    private String topicA="animals";
    ArrayList<String> itemsOfTopic = new ArrayList<>();
    String TAG="TopicAnimals";
    private Button launcher1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topic_animals);
        launcher1 = findViewById(R.id.launcher);

// Create a HashMap
        HashMap<String, String> volunteerHashMap = new HashMap<>();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //****** ככה אנחנו קוראים מהפיירבייס
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HostEvents").child(topicA);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //this is the key that should appear in the spinner
                    String key1 = snapshot.getKey();
                    itemsOfTopic.add(key1);
                    //this is the value of the key
                    String value = snapshot.getValue().toString();
                    volunteerHashMap.put(key1,value);
                    Log.d(TAG,value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"data base canclled" + databaseError);
            }
        });
        // ******
        ActivityResultLauncher<Intent> detailActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d(TAG,"returned data " + result.getResultCode());
                        if (result.getResultCode() == 1) {
                            Log.d(TAG,"user agreed!");
                        }
                        if (result.getResultCode() == 0) {
                            Log.d(TAG,"user declined");
                        }
                            // There are no request codes
//                            Intent data = result.getData();

//                        }
                    }
                });
        launcher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ActivityForResult1.class);
                String dataToBeTransfered = "abc";
                i.putExtra("data",dataToBeTransfered);
                detailActivityResultLauncher.launch(i);
            }
        });
    }
}