package com.example.afinal;
import android.content.Context;
import android.content.Intent;
import com.google.firebase.auth.FirebaseAuth;
public class LogoutManager {

    public static void logout(Context context) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
}
}
