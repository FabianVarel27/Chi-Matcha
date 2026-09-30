package com.example.chimatchaversijava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etConfirmPassword;
    private Button btn_Register;
    private TextView toLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.nameText);
        etPassword = findViewById(R.id.passwordText);
        etConfirmPassword = findViewById(R.id.confrimPassword);
        btn_Register = findViewById(R.id.btnRegister2);
        toLogin = findViewById(R.id.toLogin);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (username.isEmpty()) {
                    etUsername.setError("Username must be filled.");
                    etUsername.requestFocus();
                }
                else if (password.isEmpty()) {
                    etPassword.setError("Password must be filled.");
                    etPassword.requestFocus();
                }
                else if (confirmPassword.isEmpty()) {
                    etConfirmPassword.setError("Confirm password must be filled.");
                    etConfirmPassword.requestFocus();
                }
                else if (username.length() <= 6) {
                    etUsername.setError("Length of username must be greater than 6.");
                    etUsername.requestFocus();
                }
                else if (!password.equals(confirmPassword)) {
                    etConfirmPassword.setError("Password and Confirm Password must be the same.");
                    etConfirmPassword.requestFocus();
                }
                else {
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    intent.putExtra("GLOBAL_USERNAME", etUsername.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    };


}
