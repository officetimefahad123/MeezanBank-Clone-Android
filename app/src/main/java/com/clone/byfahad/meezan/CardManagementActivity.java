package com.clone.byfahad.meezan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class CardManagementActivity extends AppCompatActivity {

    private ImageView ivDebitCard;
    private SwitchCompat switchTemporaryBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_management);

        setupHeader();
        setupCardDetails();
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

    private void setupCardDetails() {
        ivDebitCard = findViewById(R.id.iv_debit_card);
        switchTemporaryBlock = findViewById(R.id.switch_temporary_block);

        // Display the exact debit card asset from ic_icons/ic_card.png
        if (ivDebitCard != null) {
            AssetUtils.setAssetToImageView(ivDebitCard, IconRegistry.DEBIT_CARD_IMAGE);
        }

        // Set cardholder information dynamically if intent extras are provided
        TextView tvHolderName = findViewById(R.id.tv_cardholder_name);
        TextView tvAccountNumber = findViewById(R.id.tv_account_number);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("CARDHOLDER_NAME")) {
                String name = intent.getStringExtra("CARDHOLDER_NAME");
                if (tvHolderName != null && name != null && !name.isEmpty()) {
                    tvHolderName.setText(name);
                }
            }
            if (intent.hasExtra("ACCOUNT_NUMBER")) {
                String acc = intent.getStringExtra("ACCOUNT_NUMBER");
                if (tvAccountNumber != null && acc != null && !acc.isEmpty()) {
                    tvAccountNumber.setText(acc);
                }
            }
        }

        if (switchTemporaryBlock != null) {
            switchTemporaryBlock.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    Toast.makeText(this, "Temporary Block enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Temporary Block disabled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setupActions() {
        // Card Permissions
        findViewById(R.id.row_card_permissions).setOnClickListener(v ->
                Toast.makeText(this, "Card Permissions selected", Toast.LENGTH_SHORT).show());

        // Forget/Reset Card PIN
        findViewById(R.id.row_reset_pin).setOnClickListener(v ->
                Toast.makeText(this, "Forget/Reset Card PIN selected", Toast.LENGTH_SHORT).show());

        // Manage Card Limits
        findViewById(R.id.row_manage_limits).setOnClickListener(v ->
                Toast.makeText(this, "Manage Card Limits selected", Toast.LENGTH_SHORT).show());

        // Google Wallet
        findViewById(R.id.row_google_wallet).setOnClickListener(v ->
                Toast.makeText(this, "Add card to Google Wallet selected", Toast.LENGTH_SHORT).show());
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
