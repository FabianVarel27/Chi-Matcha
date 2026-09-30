package com.example.chimatchaversijava;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    List<Matcha> listMatcha = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item);
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

        listMatcha.add(new Matcha("Honey Matcha Latte", "Rich Japanese matcha sweetened with natural honey and finished with...", R.drawable.matcha1));
        listMatcha.add(new Matcha("Matcha Latte", "Whisked ceremonial-grade Uji matcha layered with velvety steamed oat milk…", R.drawable.matcha2));
        listMatcha.add(new Matcha("Strawberry Matcha", "Premium matcha blended with sweet strawberry puree and creamy milk...", R.drawable.matcha3));
        listMatcha.add(new Matcha("Matcha Coconut", "Ceremonial-grade matcha combined with smooth coconut milk...", R.drawable.matcha4));

        LinearLayout[] cards = {
                findViewById(R.id.card1),
                findViewById(R.id.card2),
                findViewById(R.id.card3),
                findViewById(R.id.card4)
        };


        Button[] btn_filter = {
                findViewById(R.id.button1_aktif),
                findViewById(R.id.button2_nonaktif),
                findViewById(R.id.button3_nonaktif)
        };

        View.OnClickListener btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = (int) view.getTag();

                for (Button btn : btn_filter) {
                    btn.setBackgroundTintList(androidx.core.content.ContextCompat.getColorStateList(ItemActivity.this, R.color.btn_nonAktif));
                    btn.setTextColor(androidx.core.content.ContextCompat.getColor(ItemActivity.this, R.color.button));
                }

                Button clickedButton = (Button) view;
                clickedButton.setBackgroundTintList(androidx.core.content.ContextCompat.getColorStateList(ItemActivity.this, R.color.button));
                clickedButton.setTextColor(androidx.core.content.ContextCompat.getColor(ItemActivity.this, R.color.white));
            }
        };

        for (int i = 0; i < btn_filter.length; i++){
            Button btn = btn_filter[i];

            btn.setTag(i);
            btn.setOnClickListener(btnClickListener);
        }



        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) v.getTag();
                Matcha selectedMatcha = listMatcha.get(index);

                Intent intent = new Intent(ItemActivity.this, DetailItemActivity.class);
                intent.putExtra("MATCHA_NAME", selectedMatcha.getName());
                intent.putExtra("MATCHA_DESC", selectedMatcha.getDescription());
                intent.putExtra("MATCHA_IMAGE", selectedMatcha.getImg());

                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };

        for (int i = 0; i < cards.length; i++) {
            LinearLayout currentCard = cards[i];
            Matcha currentMatcha = listMatcha.get(i);

            ImageView img = currentCard.findViewById(R.id.img);
            TextView name = currentCard.findViewById(R.id.name);
            TextView description = currentCard.findViewById(R.id.description);

            img.setImageResource(currentMatcha.getImg());
            name.setText(currentMatcha.getName());
            description.setText(currentMatcha.getDescription());

            currentCard.setTag(i);
            currentCard.setOnClickListener(cardClickListener);
        }
    }
    private void showMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.getMenu().add("Home");
        popupMenu.getMenu().add("Branch");
        popupMenu.getMenu().add("Log Out");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String title = item.getTitle().toString();
                if (title.equals("Home")) {
                    Intent intent= new Intent(ItemActivity.this, HomeActivity.class);
                    startActivity(intent);

                } else if (title.equals("Branch")) {
                    Intent intent= new Intent(ItemActivity.this, BranchActivity.class);
                    startActivity(intent);

                } else if (title.equals("Log Out")) {
                    Intent intent= new Intent(ItemActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                return true;
            }
        });

        popupMenu.show();
    }
}