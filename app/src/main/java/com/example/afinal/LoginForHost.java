package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginForHost extends AppCompatActivity {
    private String TAG="LoginForHost";
    private FirebaseAuth mAuth;
    private Button subHost;
    private EditText email3;
    private EditText password3;
    private String role;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_login_for_host);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FireBaseHandler f = new FireBaseHandler(auth,this);
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        subHost = findViewById(R.id.GoToFinishedVolunteers);
        email3 = findViewById(R.id.hLoginEmail);
        password3 = findViewById(R.id.hLoginPassword);
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
        subHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmail = email3.getText().toString().trim();
                String sPassword = password3.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user=null;
                                    try {
                                         user = mAuth.getCurrentUser();
                                        Log.d(TAG, "succseded to get user");
                                    }
                                    catch(Exception e) {
                                        Log.d(TAG, "failed to get user",e);
                                    }

                                    Intent intent = new Intent(getApplicationContext(), HostHome.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Context cntx = getApplicationContext();
                                    Toast.makeText(cntx, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });
    }
}