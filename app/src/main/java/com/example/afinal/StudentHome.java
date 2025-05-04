package com.example.afinal;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
import com.google.firebase.database.annotations.NotNull;

public class StudentHome extends AppCompatActivity {
    String[] items = {"Volunteen Type","animals", "farming", "holocaust survivors","cancer patients"};
    private FirebaseDatabase firebaseDatabase;
    private Button fVolun;
    private Button scheduledVolunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_student);
        fVolun = findViewById(R.id.GoToFinishedVolunteers);
        scheduledVolunteers = findViewById(R.id.scheduledV);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinnerVolunTypesForStudent);

            // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_reg_student_each, items);
        adapter.setDropDownViewResource(R.layout.view_dropdown_item);
        //****** ככה אנחנו קוראים מהפיירבייס
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HostEvents");
        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    Log.d("dataSnapshot", snapshot.getValue().toString());
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }

                                        });
                // ******


                // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Handle Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnimal = items[position];

                // Navigate to the corresponding page based on selection
                if (selectedAnimal.equals("animals")) {
                    Intent animalsIntent = new Intent(StudentHome.this, AnimalOrganizations.class);
                    startActivity(animalsIntent);
                }
                else if (selectedAnimal.equals("farming")) {
                    Intent farmingIntent = new Intent(StudentHome.this, FarmingOrganizations.class);
                    startActivity(farmingIntent);
                }
//                else if (selectedAnimal.equals("Snake")) {
//                    Intent snakeIntent = new Intent(MainActivity.this, SnakesActivity.class);
//                    startActivity(snakeIntent);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        fVolun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Finished1.class);
                startActivity(intent);
            }
        });

        scheduledVolunteers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), reminderManager.class);
                startActivity(intent);
            }
        });
    }
}