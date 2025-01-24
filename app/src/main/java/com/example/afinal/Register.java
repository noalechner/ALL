package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private Button buttonReg;
    private Button submitButton;
    private EditText email;
    private EditText password;
    private RadioButton teacher;
    private RadioButton student;
    private RadioButton host;
    private static Context context;

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
        FireBaseHandler f = new FireBaseHandler(auth,this);
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


        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sEmailRegister = email.getText().toString().trim();
                String sPasswordRegister = password.getText().toString().trim();
                f.RegisterUser(sEmailRegister,sPasswordRegister);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Context cntx=getApplicationContext();
                Toast.makeText(cntx, "noa", Toast.LENGTH_SHORT).show();

//                Class page = RegisterStudent.class;
                Class page = null;
                if (host.isChecked())
                {
                     page = RegisterHost.class;
                }
                if (student.isChecked())
                {
                    page = RegisterStudent.class;
                }
                if (teacher.isChecked())
                {
                    page = RegisterTeacher.class;
                }

                Intent intent = new Intent(cntx, page);
                startActivity(intent);
                String sEmail = email.getText().toString().trim();
                String sPassword = password.getText().toString().trim();
                f.signIn(sEmail,sPassword);
//               ולעשות לכל סוג אינטנט משלו לוודא שיוזר בוחר  רק דבר אחד

            }
        });
    }
}