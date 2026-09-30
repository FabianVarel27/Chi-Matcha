package com.example.chimatchaversijava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity{
    private ViewFlipper carousel;
    private ImageView btnLeft, btnRight,  btnToHome, btnMenuMarket, btnMenuBranch;
    private TextView textName, btnLogout, btnToMarket, btnToBranch;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carousel = findViewById(R.id.carouser);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        textName = findViewById(R.id.textName);
        btnToMarket = findViewById(R.id.btn_to_market);
        btnToBranch = findViewById(R.id.btn_to_branch);
        btnToHome = findViewById(R.id.btn_menu_home);
        btnMenuMarket = findViewById(R.id.btn_menu_market);
        btnMenuBranch = findViewById(R.id.btn_menu_branch);
        btnLogout = findViewById(R.id.btn_logout);



        String username = getIntent().getStringExtra("GLOBAL_USERNAME");
        if (username != null && !username.isEmpty()) {
            textName.setText(username);
        }

        int[] matchaImages = {
                R.drawable.matcha1,
                R.drawable.matcha2,
                R.drawable.matcha3,
                R.drawable.matcha4
        };

        for (int imageResId : matchaImages) {
            ImageView imageView = new ImageView(this);

            imageView.setImageResource(imageResId);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            imageView.setLayoutParams(layoutParams);
            carousel.addView(imageView);
        }

        carousel.setAutoStart(false);
        carousel.setFlipInterval(3500);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.setInAnimation(HomeActivity.this, android.R.anim.fade_in);
                carousel.setOutAnimation(HomeActivity.this, android.R.anim.fade_out);
                carousel.showNext();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carousel.setInAnimation(HomeActivity.this, android.R.anim.fade_in);
                carousel.setOutAnimation(HomeActivity.this, android.R.anim.fade_out);
                carousel.showPrevious();
            }
        });

        btnToMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });

        btnToBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BranchActivity.class);
                startActivity(intent);
            }
        });
        btnMenuMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });

        btnMenuBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BranchActivity.class);
                startActivity(intent);
            }
        });
        btnToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                finish();
            }
        });
    }
}