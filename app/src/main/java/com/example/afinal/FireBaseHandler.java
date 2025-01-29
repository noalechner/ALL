package com.example.afinal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


                }
            }); //לא יכול להיות במיין
        }
    }

    public void RegisterUser(String sEmail, String sPassword) {
        auth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
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

}


