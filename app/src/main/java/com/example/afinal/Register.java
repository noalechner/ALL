package com.example.afinal;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
public class Register extends AppCompatActivity {
    private Button buttonReg;
    private EditText email;
    private EditText password;
    private FirebaseDatabase firebaseDatabase;
    private static Context context;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FireBaseHandler f = new FireBaseHandler(auth, this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.RegisterEmailAddress);
        password = findViewById(R.id.RegisterPassword);
        buttonReg = findViewById(R.id.buttonOfRegister);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmailRegister = email.getText().toString().trim();
                String sPasswordRegister = password.getText().toString().trim();
                Context cntx = getApplicationContext();
                Toast.makeText(cntx, "done", Toast.LENGTH_SHORT).show();
                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                f.RegisterUser(sEmailRegister, sPasswordRegister);
                f.signIn(sEmail, sPassword);
//***   thats how we save data into firebase
                FirebaseUser user2=null;
                try {
                    user2 = auth.getCurrentUser();
                } catch (RuntimeException e){
                    Context cntx1 = getApplicationContext();
                    Toast.makeText(cntx1, "unable to get current user from firebase", Toast.LENGTH_SHORT).show();
                }
                // Save user role in Firebase Database
                String userId = user2.getUid();
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                edit.putString("UID",userId);
                edit.apply();
                User vUser = new User(role, sEmailRegister);
                firebaseDatabase.getReference("Users").child(userId).setValue(vUser)
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(cntx, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//שלא תהיה היסטוריה
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
//***
            }
        });
    }
}