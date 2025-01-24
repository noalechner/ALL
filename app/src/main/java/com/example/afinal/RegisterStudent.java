package com.example.afinal;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterStudent extends AppCompatActivity {
    String[] items = {"Volunteen Type","חיות", "חקלאות", "ניצולי שואה","חולי סרטן"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinnerVolunTypes);

        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_reg_student_each, items);
        adapter.setDropDownViewResource(R.layout.view_dropdown_item);

        // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Handle Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnimal = items[position];

                // Navigate to the corresponding page based on selection
                if (selectedAnimal.equals("חיות")) {
                    Intent animalsIntent = new Intent(RegisterStudent.this, AnimalOrganizations.class);
                    startActivity(animalsIntent);
                }
                else if (selectedAnimal.equals("חקלאות")) {
                    Intent farmingIntent = new Intent(RegisterStudent.this, FarmingOrganizations.class);
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
    }
}