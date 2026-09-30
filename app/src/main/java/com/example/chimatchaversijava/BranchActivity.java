package com.example.chimatchaversijava;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BranchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_branch);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView iconMenu = findViewById(R.id.icon_menu);
        iconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

    }

    private void showMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.getMenu().add("Home");
        popupMenu.getMenu().add("Market");
        popupMenu.getMenu().add("Log Out");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                if (title.equals("Home")) {
                    Intent intent= new Intent(BranchActivity.this, HomeActivity.class);
                    startActivity(intent);

                } else if (title.equals("Market")) {
                    Intent intent= new Intent(BranchActivity.this, ItemActivity.class);
                    startActivity(intent);

                } else if (title.equals("Log Out")) {
                    Intent intent= new Intent(BranchActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                return true;
            }
        });

        popupMenu.show();
    }
}