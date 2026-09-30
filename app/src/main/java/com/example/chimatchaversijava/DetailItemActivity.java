package com.example.chimatchaversijava;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DetailItemActivity extends AppCompatActivity {
    private TextView btnBack;
    private AutoCompleteTextView dropdownIce, dropdownSugar;
    private EditText etQuantity, etNotes;
    private Button btnSubmit;
    private ImageButton btnHome, btnMarket, btnBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_item);

        ImageView Cover = findViewById(R.id.img);
        TextView tvName = findViewById(R.id.name);
        TextView tvDesc = findViewById(R.id.description);

        Intent intent = getIntent();
        if (intent != null) {
            String nama = intent.getStringExtra("MATCHA_NAME");
            String deskripsi = intent.getStringExtra("MATCHA_DESC");
            int gambarId = intent.getIntExtra("MATCHA_IMAGE", R.drawable.matcha1);

            tvName.setText(nama);
            tvDesc.setText(deskripsi);
            Cover.setImageResource(gambarId);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBack = findViewById(R.id.btnBack);
        dropdownIce = findViewById(R.id.dropdownIce);
        dropdownSugar = findViewById(R.id.dropdownSugar);
        etQuantity = findViewById(R.id.etQuantity);
        etNotes = findViewById(R.id.etNotes);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnHome = findViewById(R.id.btnHome);
        btnMarket = findViewById(R.id.btnMarket);
        btnBranch = findViewById(R.id.btnLocation);

        String[] iceLevels = {"Normal Ice", "Less Ice", "No Ice"};
        String[] sugarLevels = {"Normal Sugar", "Less Sugar", "No Sugar"};

        ArrayAdapter<String> iceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, iceLevels);
        ArrayAdapter<String> sugarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sugarLevels);

        dropdownIce.setAdapter(iceAdapter);
        dropdownSugar.setAdapter(sugarAdapter);

        dropdownIce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownIce.showDropDown();
            }
        });

        dropdownSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownSugar.showDropDown();
            }
        });

        btnMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailItemActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });

        btnBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailItemActivity.this, BranchActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailItemActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOrder();
            }
        });
    }

    private void processOrder() {
        String quantityStr = etQuantity.getText().toString().trim();
        if (TextUtils.isEmpty(quantityStr)) {
            showErrorDialog("Quantity cannot be empty. Please enter a valid number.");
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        if (quantity == 0) {
            showErrorDialog("Quantity must be more than 0.");
            return;
        }

        showSuccessDialog();
    }

    private void showErrorDialog(String message) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Invalid Input")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showSuccessDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Order Confirmed")
                .setMessage("A confirmation email has been sent to your email regarding this order.")
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(DetailItemActivity.this, ItemActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}