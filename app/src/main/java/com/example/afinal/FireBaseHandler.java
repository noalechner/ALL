package com.example.afinal;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Logger;

public class FireBaseHandler {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference myRef = database.getReference();
    private static FirebaseAuth auth;
    private static Context context;
    private static final String TAG = "noa";

    public FireBaseHandler(FirebaseAuth auth, Context context) {
        this.auth = auth;
        this.context = context;
    }

    public void signIn(String sEmail, String sPassword) {
        if (TextUtils.isEmpty(sEmail) || TextUtils.isEmpty(sPassword)) {
            Toast.makeText(context, "error, please try again ", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(sEmail, sPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(context, "good job! ", Toast.LENGTH_SHORT).show();
                    User user = getUserRole();
                    Class page = null;
//                    if(user.getUsername().equals("student")) {
//                        Intent intent = new Intent(context, RegisterStudent.class);
//                        context.startActivity(intent);
//                    }
//                    if(user.getUsername().equals("teacher")) {
//                        Intent intent = new Intent(context, RegisterTeacher.class);
//                        context.startActivity(intent);
//
//                    }
//                    if(user.getUsername().equals("admin")) {
//                        Intent intent = new Intent(context, RegisterHost.class);
//                        context.startActivity(intent);
//                    }



                }
            }); //לא יכול להיות במיין
        }
    }


    public void RegisterUser(String sEmail, String sPassword, int selectedId ) {
        auth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        myRef.child("users").child("userId").setValue(selectedId);
//                        Toast.makeText(context,sEmail+ " " + sPassword, Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "success! ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "failed "+ task ,Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "test failed");


                        }

                    }
                });
    }
    public static User getUserRole()
    {
        FirebaseUser user2 = auth.getCurrentUser();
        final User[] userData = new User[1];
        // Save user role in Firebase Database
        String userId = user2.getUid();
        myRef.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
                }
                else {
                    DataSnapshot dataSnapshot = task.getResult();

                    // מיפוי הנתונים לאובייקט User
                    if (dataSnapshot.exists()) {
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String role = dataSnapshot.child("username").getValue(String.class);

                        userData[0] = new User(email, role);
                    }

                }

            }
        });

        return userData[0];
    }

}


