package com.example.afinal;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FarmingOrganizations extends AppCompatActivity {
    String[] organs = {"Organizations","התאחדות חקלאי ישראל", "ארצנו", "השומר החדש"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animal_organizations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinnerAniOrgans);

        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_reg_student_each, organs);
        adapter.setDropDownViewResource(R.layout.view_dropdown_item);

        // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Handle Spinner item selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedAnimal = organs[position];

                // Navigate to the corresponding page based on selection
                if (selectedAnimal.equals("התאחדות חקלאי ישראל")) {
                    Intent haitahdootIntent = new Intent(FarmingOrganizations.this, ItahdootHaklaeyIsraelDetailsPage.class);
                    startActivity(haitahdootIntent);
                }
                else if (selectedAnimal.equals("ארצנו")) {
                    Intent artsenooIntent = new Intent(FarmingOrganizations.this, ArtsenooDetailsPage.class);
                    startActivity(artsenooIntent);
                }
                else if (selectedAnimal.equals("השומר החדש")) {
                    Intent hashomerIntent = new Intent(FarmingOrganizations.this, HashomerHaHadashDetailsPage.class);
                    startActivity(hashomerIntent);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
}