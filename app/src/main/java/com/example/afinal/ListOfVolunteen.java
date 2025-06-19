package com.example.afinal;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.google.firebase.database.Query;
import java.util.ArrayList;
public class ListOfVolunteen extends AppCompatActivity {
    private ListView simpleList;
    private String TAG="ListOfVolunteen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_of_volunteen);
        simpleList = findViewById(R.id.simpleList);
        String countryList[] = {"תנו לחיות לחיות", "צער בעלי חיים", "חקלאות ישראל", "עמותה לניצולי שואה", "התנדבות בשומרון", "חושבים על העתיד"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.listview, R.id.textView,countryList);
        simpleList.setAdapter(arrayAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userId=sharedPref.getString("UID","");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("HostEvents");
        Query query = reference.child("animals").orderByChild("ownerId").equalTo(userId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Data exists where hostEventId is ownerId
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        // Process each item found
                       Log.d(TAG,"Found item: " + childSnapshot.getKey() + " -> " + childSnapshot.getValue());
                        // You can convert childSnapshot.getValue() to your data model object here
                    }
                } else {
                    // No data found where hostEventId is 111
                   Log.d(TAG,"No data found with hostEventId");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle potential errors
                System.err.println("Database query cancelled: " + error.getMessage());
            }
        });
    }


}