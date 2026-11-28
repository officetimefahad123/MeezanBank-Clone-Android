package com.clone.byfahad.meezan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectBankActivity extends AppCompatActivity {

    private View cardPreferredBank;
    private TextView tvOtherBanksLabel;
    private BankAdapter bankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

        setupHeader();
        setupPreferredBank();
        setupBankList();
        setupSearch();
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

    private void setupPreferredBank() {
        cardPreferredBank = findViewById(R.id.card_preferred_bank);
        ImageView ivPreferredLogo = findViewById(R.id.iv_preferred_logo);

        if (ivPreferredLogo != null) {
            AssetUtils.setAssetToImageView(ivPreferredLogo, "ic_banks/ic_meezan.png");
        }

        if (cardPreferredBank != null) {
            cardPreferredBank.setOnClickListener(v ->
                    Toast.makeText(this, "Meezan Bank selected (Demo)", Toast.LENGTH_SHORT).show());
        }
    }

    private void setupBankList() {
        tvOtherBanksLabel = findViewById(R.id.tv_other_banks_label);
        RecyclerView rvOtherBanks = findViewById(R.id.rv_other_banks);

        rvOtherBanks.setLayoutManager(new LinearLayoutManager(this));

        List<BankModel> bankList = new ArrayList<>();

        // Reference Screenshot 2 Order
        bankList.add(new BankModel("Jazz Cash Wallet", "ic_banks/ic_jazzcash_wallet.png"));
        bankList.add(new BankModel("EasyPaisa-Telenor Bank", "ic_banks/ic_easypaisa.png"));
        bankList.add(new BankModel("HBL KONNECT", "ic_banks/ic_hbl_konnect.png"));
        bankList.add(new BankModel("Bank Al-Habib", "ic_banks/ic_halan_microfinance_bankltd.png"));
        bankList.add(new BankModel("UBL", "ic_banks/ic_ubl.png"));
        bankList.add(new BankModel("Bank Alfalah", "ic_banks/ic_bankalfalah.png"));
        bankList.add(new BankModel("Allied Bank", "ic_banks/ic_alliedbank.png"));
        bankList.add(new BankModel("MCB", "ic_banks/ic_mcb.png"));

        // Additional Discovered Banks from Assets
        bankList.add(new BankModel("National Bank of Pakistan (NBP)", "ic_banks/ic_nbp.png"));
        bankList.add(new BankModel("Askari Bank", "ic_banks/ic_askaribank.png"));
        bankList.add(new BankModel("Faysal Bank", "ic_banks/ic_faysalbank.png"));
        bankList.add(new BankModel("BankIslami", "ic_banks/ic_bankislami.png"));
        bankList.add(new BankModel("JS Bank", "ic_banks/ic_jsbank.png"));
        bankList.add(new BankModel("Standard Chartered", "ic_banks/ic_standardchartered.png"));
        bankList.add(new BankModel("SadaPay", "ic_banks/ic_sadapay.png"));
        bankList.add(new BankModel("NayaPay", "ic_banks/ic_nayapay.png"));
        bankList.add(new BankModel("Dubai Islamic Bank", "ic_banks/ic_dubaibank.png"));
        bankList.add(new BankModel("Soneri Bank", "ic_banks/ic_soneribank.png"));
        bankList.add(new BankModel("Silkbank", "ic_banks/ic_silkbank.png"));
        bankList.add(new BankModel("Sindh Bank", "ic_banks/ic_sindh_bank.png"));
        bankList.add(new BankModel("Samba Bank", "ic_banks/ic_sambabank.png"));
        bankList.add(new BankModel("MCB Islamic Bank", "ic_banks/ic_mcb_islamic.png"));
        bankList.add(new BankModel("Al Baraka Bank", "ic_banks/ic_albarakabank.png"));
        bankList.add(new BankModel("Bank of Punjab", "ic_banks/ic_bankofpunjab.png"));
        bankList.add(new BankModel("Bank of Khyber", "ic_banks/ic_bankofkhyber.png"));
        bankList.add(new BankModel("Bank of China", "ic_banks/ic_bankofchina.png"));
        bankList.add(new BankModel("Citibank", "ic_banks/ic_citibank.png"));
        bankList.add(new BankModel("ICBC Pakistan", "ic_banks/ic_icbcpakistan.png"));
        bankList.add(new BankModel("Burj Bank", "ic_banks/ic_burjbank.png"));
        bankList.add(new BankModel("UBank / UPaisa", "ic_banks/ic_ubank_upaisa.png"));
        bankList.add(new BankModel("Keenu", "ic_banks/ic_keenu.png"));
        bankList.add(new BankModel("OneZapp", "ic_banks/ic_onezapp.png"));
        bankList.add(new BankModel("PayMax", "ic_banks/ic_paymax.png"));
        bankList.add(new BankModel("PayVay", "ic_banks/ic_payvay.png"));
        bankList.add(new BankModel("Digitt+", "ic_banks/ic_digittplus.png"));
        bankList.add(new BankModel("First Women Bank", "ic_banks/ic_first_womenbank.png"));
        bankList.add(new BankModel("FirstPay (HBL MFB)", "ic_banks/ic_firstpay_hbl_mfb.png"));
        bankList.add(new BankModel("Khushhali Microfinance Bank", "ic_banks/ic_kushhali_microfinancebank.png"));
        bankList.add(new BankModel("LOLC Microfinance Bank", "ic_banks/ic_lolc_microfinance_bank.png"));
        bankList.add(new BankModel("Mashreq Bank Pakistan", "ic_banks/ic_mashreq_bankpakistan_limited.png"));
        bankList.add(new BankModel("NPNA / NRSP Microfinance", "ic_banks/ic_npna_microfinance.png"));
        bankList.add(new BankModel("Raqami Islamic Bank", "ic_banks/ic_raqami_islamic_bank.png"));
        bankList.add(new BankModel("YAP Pakistan", "ic_banks/ic_yap_pakistan_private_limited.png"));
        bankList.add(new BankModel("Zarai Taraqiati Bank (ZTBL)", "ic_banks/ic_zarai_taraqiati_bank.png"));
        bankList.add(new BankModel("Abhi Microfinance Bank", "ic_banks/ic_abhi_microfinance_bank_limited.png"));
        bankList.add(new BankModel("Bank Makramah Limited (BML)", "ic_banks/ic_bank_makramah_limited.png"));
        bankList.add(new BankModel("Central Depository Company", "ic_banks/ic_central_depository_company.png"));
        bankList.add(new BankModel("Halan Microfinance Bank", "ic_banks/ic_halan_microfinance_bankltd.png"));

        bankAdapter = new BankAdapter(bankList, bank ->
                Toast.makeText(this, bank.getName() + " selected (Demo)", Toast.LENGTH_SHORT).show());

        rvOtherBanks.setAdapter(bankAdapter);
    }

    private void setupSearch() {
        EditText etSearch = findViewById(R.id.et_search);
        if (etSearch == null) return;

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = (s != null) ? s.toString().trim() : "";

                // Filter Preferred Bank Card
                if (cardPreferredBank != null) {
                    if (query.isEmpty() || "meezan bank".contains(query.toLowerCase()) || "meezan".contains(query.toLowerCase())) {
                        cardPreferredBank.setVisibility(View.VISIBLE);
                    } else {
                        cardPreferredBank.setVisibility(View.GONE);
                    }
                }

                // Filter Other Banks List
                if (bankAdapter != null) {
                    bankAdapter.filter(query);

                    if (tvOtherBanksLabel != null) {
                        if (bankAdapter.getFilteredCount() > 0) {
                            tvOtherBanksLabel.setVisibility(View.VISIBLE);
                        } else {
                            tvOtherBanksLabel.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
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
