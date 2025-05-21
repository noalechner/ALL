package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AddNewVolunteen extends AppCompatActivity {
    String[] items = {"Volunteen Topic","animals", "farming", "holocaust survivors","cancer patients"};
    private Spinner topicVolunteen;
    private EditText nameVolunteen;
    private EditText dateVolunteen;
    private EditText timeVolunteen;
    private EditText adressVolunteen;
    private Button doneButton;
    private FirebaseDatabase firebaseDatabase1;
    private String topic="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_volunteen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        topicVolunteen = findViewById(R.id.spinnerVolunTypesForHost);
        nameVolunteen = findViewById(R.id.inputNameVolun);
        dateVolunteen = findViewById(R.id.inputDateVolun);
        firebaseDatabase1 = FirebaseDatabase.getInstance();
        timeVolunteen = findViewById(R.id.inputTimeVolun);
        adressVolunteen = findViewById(R.id.inputAdressVolun);
        doneButton = findViewById(R.id.doneNewVolun);
        FirebaseAuth auth = FirebaseAuth.getInstance();





        Spinner spinnerH = findViewById(R.id.spinnerVolunTypesForHost);

        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_reg_student_each, items);
        adapter.setDropDownViewResource(R.layout.view_dropdown_item);
        spinnerH.setAdapter(adapter);

        // Handle Spinner item selection
        spinnerH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnswer = items[position];

                // Navigate to the corresponding page based on selection

                if (selectedAnswer.equals("animals")) {
                    topic="animals";
                }
                else if (selectedAnswer.equals("farming")) {
                    topic="farming";
                }
                else if (selectedAnswer.equals("holocaust survivors")) {
                    topic="holocaustSurvivors";
                }
                else if (selectedAnswer.equals("cancer patients")) {
                    topic="cancerPatients";
                }
//
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


            doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topic=topic;
                String vName = nameVolunteen.getText().toString().trim();
                String vDate = dateVolunteen.getText().toString().trim();
                String vTime = timeVolunteen.getText().toString().trim();
                String vAddress = adressVolunteen.getText().toString().trim();
                String allDetails = "your new volunteen is: topic: " + topic + "name: " + vName + " date: " + vDate + " time: " + vTime + " adress: " + vAddress;
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vName", vName); // Example: storing user role
                editor.apply(); // Save changes
                Context cntx = getApplicationContext();


                FirebaseUser user2 = auth.getCurrentUser();
                // Save user role in Firebase Database
                String userId = user2.getUid();
                HostEvents e = new HostEvents(topic,vName, vDate, vTime, vAddress);
                firebaseDatabase1.getReference("HostEvents").child(topic).child(vName).setValue(e)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
//                                Intent intent = new Intent(cntx, RegisterHost.class);
//                                startActivity(intent);
                                Query myTopPostsQuery = firebaseDatabase1.getReference("HostEvents").child(topic);
                                Log.d("HostEvents", myTopPostsQuery.toString());
                            } else {
                                Toast.makeText(AddNewVolunteen.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        });
                Intent intent = new Intent(cntx, HostHome.class);
                intent.putExtra("newEventAdded", true);
                startActivity(intent);
            }

        });



    }
}