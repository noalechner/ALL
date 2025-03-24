package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginForStudent extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button subStudent;
    private EditText email1;
    private EditText password1;
//    private String role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_login_for_student);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FireBaseHandler f = new FireBaseHandler(auth,this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        subStudent = findViewById(R.id.submitGoToStudent);
        email1 = findViewById(R.id.sLoginEmail);
        password1 = findViewById(R.id.sLoginPassword);
        ref.child("STUDENTS").child("studentsId").setValue("volunteerPlace");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
//                User post = dataSnapshot.getValue(User.class);
//                role = post.getRole();
//                Toast.makeText(getApplicationContext(), "the role is "+ role,Toast.LENGTH_SHORT).show();

                // ..
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        subStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email1.getText().toString().trim();
                String sPassword = password1.getText().toString().trim();
                f.signIn(sEmail,sPassword);
                Intent intent = new Intent(getApplicationContext(), RegisterStudent.class);
                startActivity(intent);
            }
        });

    }
}