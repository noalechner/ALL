package com.example.afinal;

import static android.app.PendingIntent.getActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
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

public class RegisterTeacher extends AppCompatActivity {
    String[] items = {"noa@gmail.com", "ben@gmail.com", "tal@gamil.com", "joe@gmail.com4", "dana@gmail.com"};
    private WifiReceiver wifiReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_teacher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            Spinner spinner = findViewById(R.id.spinner2);

        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set an item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Show a toast with the selected item
                String selectedItem = parentView.getItemAtPosition(position).toString();
                Toast.makeText(RegisterTeacher.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }





        });



    }

    @Override
    public void onResume() {
        super.onResume();

        // Create and register the BroadcastReceiver to listen for connectivity changes
        wifiReceiver = new WifiReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver, filter);  // Use getActivity() to access the context
    }

    @Override
    public void onPause() {
        super.onPause();

        // Unregister the receiver when the fragment is paused
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
        }
    }

//******
}