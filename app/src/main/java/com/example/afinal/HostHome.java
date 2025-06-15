package com.example.afinal;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
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
    private Button logout;
    private String TAG="HostHome";
    FirebaseAuth auth2 = FirebaseAuth.getInstance();
    private WifiReceiver wifiReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_host);
        add = findViewById(R.id.addVolun);
        volunList = findViewById(R.id.goToList);
        logout = findViewById(R.id.logout);
        String date;
        String time;
        String adress;
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
            Log.d(TAG, "HostHome loaded ");
            boolean newEventAdded = getIntent().getBooleanExtra("newEventAdded", false);
            if (newEventAdded) {
                FirebaseUser user2 = auth2.getCurrentUser();
                String userId = user2.getUid();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HostEvents").child(userId);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                        Event post = dataSnapshot.getValue(Event.class);
                        if (post != null) {
                            String name = post.getName();
                            String adress = post.getAdress();
                            String date = post.getDate();
                            String time = post.getTime();
                            String message = "שם ההתנדבות: " + name + "\n"
                                    + "מיקום: " + adress + "\n"
                                    + "תאריך: " + date + "\n"
                                    + "שעה: " + time;
                            new AlertDialog.Builder(HostHome.this)  // <-- החלף בשם של הקונטקסט (האקטיביטי שלך)
                                    .setTitle("פרטי ההתנדבות שלך")
                                    .setMessage(message)
                                    .setPositiveButton("סגור", null)
                                    .show();
                        } else {
                            Log.w("Firebase", "Event is null");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Firebase", "Database error: " + databaseError.getMessage());
                    }
                });
            }
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
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            return insets;
        });
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String nameVol = sharedPreferences.getString("vName", ""); // Default is empty if not found
        list.add(nameVol);
        // Create an ArrayAdapter using a simple spinner item layout and the string array
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
            }




////            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Do nothing
//            }
//        });
//
//
//    }

    public void alertDone(){
        FirebaseUser user2 = auth2.getCurrentUser();
        String userId = user2.getUid();
    }





//    private void showAlertDialogue() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Alert");
//        builder.setMessage();
//    }
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
}