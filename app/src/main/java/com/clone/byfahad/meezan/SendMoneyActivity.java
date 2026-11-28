package com.clone.byfahad.meezan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SendMoneyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        setupHeader();
        setupActions();
    }

    private void setupHeader() {
        ImageView ivBack = findViewById(R.id.iv_header_back);
        ImageView ivHome = findViewById(R.id.iv_header_home);
        ImageView ivPower = findViewById(R.id.iv_header_power);

        if (ivBack != null) {
            ivBack.setOnClickListener(v -> finish());
        }

        if (ivHome != null) {
            AssetUtils.setAssetToImageView(ivHome, IconRegistry.HEADER_HOME);
            ivHome.setColorFilter(Color.WHITE);
            ivHome.setOnClickListener(v -> navigateToHome());
        }

        if (ivPower != null) {
            AssetUtils.setAssetToImageView(ivPower, IconRegistry.HEADER_POWER_BUTTON);
            ivPower.setColorFilter(Color.WHITE);
            ivPower.setOnClickListener(v -> showLogoutDialog());
        }
    }

    private void setupActions() {
        // Both primary actions navigate to SelectBankActivity
        findViewById(R.id.btn_send_new_account).setOnClickListener(v -> openSelectBank());
        findViewById(R.id.btn_add_beneficiary).setOnClickListener(v -> openSelectBank());
    }

    private void openSelectBank() {
        Intent intent = new Intent(this, SelectBankActivity.class);
        startActivity(intent);
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out of Meezan Bank?")
                .setPositiveButton("Log Out", (dialog, which) -> {
                    Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    navigateToHome();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
