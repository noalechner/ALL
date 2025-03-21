package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button buttonSubLogin;
    private Button reg;
    private EditText email;
    private EditText password;
    private String role;
    private Button goStudent;
    private Button goTeacher;
    private Button goHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_main);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FireBaseHandler f = new FireBaseHandler(auth,this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        buttonSubLogin = findViewById(R.id.submitLogin);
        reg = findViewById(R.id.buttonLoginToRegister);
        email = findViewById(R.id.LoginEmail);
        password = findViewById(R.id.LoginPassword);
        goStudent = findViewById(R.id.goToStudent);
        goTeacher = findViewById(R.id.goToTeacher);
        goHost = findViewById(R.id.goToHost);
        ref.child("STUDENTS").child("studentsId").setValue("volunteerPlace");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User post = dataSnapshot.getValue(User.class);
                role = post.getRole();
                Toast.makeText(getApplicationContext(), "the role is "+ role,Toast.LENGTH_SHORT).show();

                // ..
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        buttonSubLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();

//                if(role == "student")
//                {
//                    Intent intent = new Intent(getApplicationContext(), RegisterStudent.class);
//                    startAoctivity(intent);
//                }
                f.signIn(sEmail,sPassword);
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


        goStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterStudent.class);
                startActivity(intent);
            }
        });


        goTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterTeacher.class);
                startActivity(intent);
            }
        });



        goHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterHost.class);
                startActivity(intent);
            }
        });


    }
}