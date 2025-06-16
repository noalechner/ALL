package com.example.afinal;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
public class LoginForStudent extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button subStudent;
    private EditText email1;
    private EditText password1;

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
                mAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("log in!", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();
                            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPref.edit();
                            edit.putString("UID",userId);
                            edit.apply();
                            Intent intent = new Intent(getApplicationContext(), StudentHome.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//שלא תהיה היסטוריה
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("log in!", "signInWithEmail:failure", task.getException());
                            Context cntx = getApplicationContext();
                            Toast.makeText(cntx, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}