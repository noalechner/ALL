package com.example.afinal;

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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HostHome extends AppCompatActivity {
    String[] items = {"Volunteen Pages","תנו לחיות לחיות-20/3/25", "תנו לחיות לחיות 19/3/25", "טוב השדה- 13/11/24"};
    List<String> list = new ArrayList<>();

    private Button add;
    private Button volunList;
    private Button bAlert;
    FirebaseAuth auth2 = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_host);
        add = findViewById(R.id.addVolun);
        volunList = findViewById(R.id.goToList);
        bAlert = findViewById(R.id.btnAlert);
        String name;
        String date;
        String time;
        String adress;
        FirebaseUser user2 = auth2.getCurrentUser();
        String userId = user2.getUid();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HostEvents").child(userId);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.d("dataSnapshot", snapshot.getValue().toString());
                        name=FirebaseDatabase.getInstance().getReference("HostEvents").child(userId).Get
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AddNewVolunteen.class);
                    startActivity(intent);
                }
            });

            volunList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ListOfVolunteen.class);
                    startActivity(intent);
                }
            });

            bAlert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialogue();
                }
            });

            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String nameVol = sharedPreferences.getString("vName", ""); // Default is empty if not found
        list.add(nameVol);

        Spinner spinner = findViewById(R.id.spinner2);

        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);

        // Set the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set an item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Show a toast with the selected item
                String selectedItem = parentView.getItemAtPosition(position).toString();
                Toast.makeText(HostHome.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }




            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });


    }

    private void showAlertDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage();
    }
}