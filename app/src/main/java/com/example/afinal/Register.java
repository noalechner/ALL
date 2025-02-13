package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private Button buttonReg;
    private Button submitButton;
    private EditText email;
    private EditText password;
    private RadioButton teacher;
    private RadioButton student;
    private RadioButton host;
    private RadioGroup roleRadioGroup;
    private FirebaseDatabase firebaseDatabase;
    private static Context context;
    private String role;


//    public Register(Context context) {
//        this.context = context;
//    }
//

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
        submitButton = findViewById(R.id.Submit);
        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);
        host = findViewById(R.id.host);
        roleRadioGroup = findViewById(R.id.radioGroupRole);





        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmailRegister = email.getText().toString().trim();
                String sPasswordRegister = password.getText().toString().trim();
                int selectedId = roleRadioGroup.getCheckedRadioButtonId();
                Context cntx = getApplicationContext();
                Toast.makeText(cntx, "noa", Toast.LENGTH_SHORT).show();

//                Class page = RegisterStudent.class;
                Class page = null;
                if (host.isChecked()) {
                    page = RegisterHost.class;
                }
                if (student.isChecked()) {
                    page = RegisterStudent.class;
                }
                if (teacher.isChecked()) {
                    page = RegisterTeacher.class;
                }

                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                f.RegisterUser(sEmailRegister, sPasswordRegister);
                f.signIn(sEmail, sPassword);

//               ולעשות לכל סוג אינטנט משלו לוודא שיוזר בוחר  רק דבר אחד


//                if (TextUtils.isEmpty(sEmailRegister) || TextUtils.isEmpty(sPasswordRegister)) {
//                    Toast.makeText("Please enter email and password", Toast.LENGTH_SHORT).show();
//                    return;
//                }


//                if (selectedId == -1) {
//                    Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                RadioButton selectedRadioButton = findViewById(selectedId);
                String role = selectedRadioButton.getText().toString();

                FirebaseUser user2 = auth.getCurrentUser();

                    // Save user role in Firebase Database
                    String userId = user2.getUid();
                Class finalPage = page;
                User vUser = new User( role, sEmailRegister);
                firebaseDatabase.getReference("Users").child(userId).setValue(vUser)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
//                                                    redirectToRoleActivity(role);
                                    Intent intent = new Intent(cntx, finalPage);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Register.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            });


            }

        });
//        public static class User {
//            public String rEmail, role;
//
//            public User() {
//                // Default constructor required for calls to DataSnapshot.getValue(User.class)
//            }
//
//            public User(String email, String role) {
//                this.rEmail = email;
//                this.role = role;
//            }
//        }



    }

}