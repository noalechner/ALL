package com.example.afinal;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private DatabaseReference mDatabase;
    public String username;
    public String email;
    private String TAG = "Vuser";

    public User() {
                    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public User(String username, String email) {

        this.username = username;
        this.email = email;
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void writeNewUser(String userId) {
        mDatabase.child(userId).child("email").setValue(this.email);
        mDatabase.child(userId).child("username").setValue(this.username);
        Log.d(TAG, "users saved");

    }

}

