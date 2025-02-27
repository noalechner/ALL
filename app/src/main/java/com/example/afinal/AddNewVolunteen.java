package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewVolunteen extends AppCompatActivity {
    private EditText nameVolunteen;
    private EditText dateVolunteen;
    private EditText timeVolunteen;
    private EditText adressVolunteen;
    private Button doneButton;
    private FirebaseDatabase firebaseDatabase1;


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
        nameVolunteen = findViewById(R.id.inputNameVolun);
        dateVolunteen = findViewById(R.id.inputDateVolun);
        firebaseDatabase1 = FirebaseDatabase.getInstance();
        timeVolunteen = findViewById(R.id.inputTimeVolun);
        adressVolunteen = findViewById(R.id.inputAdressVolun);
        doneButton = findViewById(R.id.doneNewVolun);
        FirebaseAuth auth = FirebaseAuth.getInstance();


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vName = nameVolunteen.getText().toString().trim();
                String vDate = dateVolunteen.getText().toString().trim();
                String vTime = timeVolunteen.getText().toString().trim();
                String vAddress = adressVolunteen.getText().toString().trim();
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vName", vName); // Example: storing user role
                editor.apply(); // Save changes
                Context cntx = getApplicationContext();


                FirebaseUser user2 = auth.getCurrentUser();
                // Save user role in Firebase Database
                String userId = user2.getUid();
                HostEvents e = new HostEvents(vName,vDate,vTime,vAddress);
                firebaseDatabase1.getReference("HostEvents").child(userId).setValue(e)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Intent intent = new Intent(cntx, RegisterHost.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AddNewVolunteen.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        });
            }

        });

    }
}