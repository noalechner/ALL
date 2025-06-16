package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class TopicAnimals extends AppCompatActivity {
    private String topicA="animals";
    ArrayList<String> itemsOfTopic = new ArrayList<>();
    String TAG="TopicAnimals";
    private boolean isFirstSelection = true;
    private String selectedAnswer;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_topic_animals);
        firebaseDatabase = FirebaseDatabase.getInstance();
// Create a HashMap-where we saved all volunteers data
        HashMap<String, String> volunteerHashMap = new HashMap<>();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        itemsOfTopic.add("Offered Volunteers");
        Spinner spinner = findViewById(R.id.spinnerVolunteersWithSameTopicAnimals);
        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_reg_student_each, itemsOfTopic);
        adapter.setDropDownViewResource(R.layout.view_dropdown_item);
        //****** thats how we read from firebase
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
        // Set the adapter to the spinner
        spinner.setAdapter(adapter);
        ActivityResultLauncher<Intent> detailActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d(TAG,"returned data " + result.getResultCode());
                        if (result.getResultCode() == 1) {
                            Toast.makeText(TopicAnimals.this, " user agreed! ", Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            String userId=sharedPref.getString("UID","");
                            firebaseDatabase.getReference("HostEvents").child("animals_volunteers").child(selectedAnswer).push().setValue(userId)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
//                                            Query myTopPostsQuery = firebaseDatabase.getReference("HostEvents").child(topic);
//                                            Log.d("HostEvents", myTopPostsQuery.toString());
                                        } else {
//                                            Toast.makeText(AddNewVolunteen.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            Log.d(TAG,"user agreed!" + userId + selectedAnswer);
                        }
                        if (result.getResultCode() == 0) {
                            Toast.makeText(TopicAnimals.this, " user declined... ", Toast.LENGTH_LONG).show();
                            Log.d(TAG,"user declined");
                        }
                    }
                });
        // Handle Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                    return; // דילוג על הבחירה הראשונה האוטומטית
                }
                selectedAnswer = itemsOfTopic.get(position).toString();
                Log.d("SpinnerSelection", "נבחר: " + selectedAnswer);
                Intent i = new Intent(getApplicationContext(), ActivityForResult1.class);
                i.putExtra("data",volunteerHashMap.get(selectedAnswer));
                detailActivityResultLauncher.launch(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}