package com.example.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class AddNewVolunteen extends AppCompatActivity {
        private EditText nameVolunteen;
        private EditText dateVolunteen;
        private EditText timeVolunteen;
        private EditText adressVolunteen;

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
        timeVolunteen = findViewById(R.id.inputTimeVolun);
        adressVolunteen = findViewById(R.id.inputAdressVolun);
    }

    public void onClick(View v) {
        String vName = nameVolunteen.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("vName", vName); // Example: storing user role
        editor.apply(); // Save changes
        Intent intent = new Intent(getApplicationContext(), RegisterHost.class);
        startActivity(intent);
    }
}