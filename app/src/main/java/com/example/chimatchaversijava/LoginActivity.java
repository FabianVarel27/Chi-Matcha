package com.example.chimatchaversijava;

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

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button etBtnLogin;
    private TextView linkRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        etBtnLogin = findViewById(R.id.btnLogin);
        linkRegis = findViewById(R.id.linkRegister);

        etBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty()){
                    etUsername.setError("Username must be filled");
                    etUsername.requestFocus();
                } else if (password.isEmpty()) {
                    etPassword.setError("Password must be filled");
                    etPassword.requestFocus();
                }else if(username.length() <= 6){
                    etUsername.setError("Length of Username must be greater than 6");
                    etUsername.requestFocus();
                }else {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("GLOBAL_USERNAME", etUsername.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
        linkRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}