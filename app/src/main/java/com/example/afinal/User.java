package com.example.afinal;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    private DatabaseReference mDatabase;
    public String role;
    public String email;
    private String TAG = "Vuser";

    public User() {
                    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public User(String role, String email) {

        this.role = role;
        this.email = email;
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void writeNewUser(String userId) {
        mDatabase.child(userId).child("email").setValue(this.email);
        mDatabase.child(userId).child("role").setValue(this.role);
        Log.d(TAG, "users saved");

    }

}

