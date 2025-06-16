package com.example.afinal;
import android.util.Log;
public class User {
    public String role;
    public String email;
    private String TAG = "Vuser";

    public User() {
                    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
    public User(String role, String email) {
        this.role = role;
        this.email = email;
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
        Log.d(TAG,"email is " + email);
        this.email = email;
    }
}



