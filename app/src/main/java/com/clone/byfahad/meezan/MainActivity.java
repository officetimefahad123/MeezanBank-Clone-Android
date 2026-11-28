package com.clone.byfahad.meezan;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View layoutBalanceVisible;
    private TextView btnShowBalance;
    private TextView btnHideBalance;
    private ImageView ivRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupIcons();
        setupBalanceToggle();
        setupServiceGrid();
        setupDrawerAndActions();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        layoutBalanceVisible = findViewById(R.id.layout_balance_visible);
        btnShowBalance = findViewById(R.id.btn_show_balance);
        btnHideBalance = findViewById(R.id.btn_hide_balance);
        ivRefresh = findViewById(R.id.iv_card_refresh);
    }

    private void setupIcons() {
        // Header Icons
        ImageView ivSidebar = findViewById(R.id.iv_header_sidebar);
        ImageView ivLogo = findViewById(R.id.iv_header_logo);
        ImageView ivPower = findViewById(R.id.iv_header_power);

        AssetUtils.setAssetToImageView(ivSidebar, IconRegistry.HEADER_SIDEBAR);
        AssetUtils.setAssetToImageView(ivLogo, IconRegistry.HEADER_MEEZAN_LOGO);
        AssetUtils.setAssetToImageView(ivPower, IconRegistry.HEADER_POWER_BUTTON);

        // Account Card Refresh Icon with subtle light-grey color tint
        AssetUtils.setAssetToImageView(ivRefresh, IconRegistry.CARD_REFRESH);
        if (ivRefresh != null) {
            ivRefresh.setColorFilter(Color.parseColor("#888888"));
        }

        ImageView ivShare = findViewById(R.id.iv_btn_share_icon);
        ImageView ivTransactions = findViewById(R.id.iv_btn_transactions_icon);

        AssetUtils.setAssetToImageView(ivShare, IconRegistry.CARD_SHARE);
        AssetUtils.setAssetToImageView(ivTransactions, IconRegistry.CARD_TRANSACTIONS);

        // Explicitly set pure white color filter for Share & Transactions icons
        if (ivShare != null) {
            ivShare.setColorFilter(ContextCompat.getColor(this, R.color.white));
        }
        if (ivTransactions != null) {
            ivTransactions.setColorFilter(ContextCompat.getColor(this, R.color.white));
        }

        // Navigation Drawer Icons
        ImageView ivDrawerLogo = findViewById(R.id.iv_drawer_meezan_logo);
        ImageView ivDrawerLogout = findViewById(R.id.iv_drawer_logout_icon);

        if (ivDrawerLogo != null) {
            AssetUtils.setAssetToImageView(ivDrawerLogo, IconRegistry.HEADER_MEEZAN_LOGO);
        }
        if (ivDrawerLogout != null) {
            AssetUtils.setAssetToImageView(ivDrawerLogout, IconRegistry.HEADER_POWER_BUTTON);
        }
    }

    private void setupBalanceToggle() {
        btnHideBalance.setOnClickListener(v -> setBalanceVisibility(false));
        btnShowBalance.setOnClickListener(v -> setBalanceVisibility(true));
    }

    private void setBalanceVisibility(boolean visible) {
        if (visible) {
            btnShowBalance.setVisibility(View.GONE);
            layoutBalanceVisible.setVisibility(View.VISIBLE);
        } else {
            layoutBalanceVisible.setVisibility(View.GONE);
            btnShowBalance.setVisibility(View.VISIBLE);
        }
    }

    private void setupServiceGrid() {
        RecyclerView rvGrid = findViewById(R.id.rv_service_grid);
        rvGrid.setLayoutManager(new GridLayoutManager(this, 2));

        List<ServiceModel> services = new ArrayList<>();
        services.add(new ServiceModel("Send\nMoney", IconRegistry.SERVICE_SEND_MONEY));
        services.add(new ServiceModel("Mobile\nTopup", IconRegistry.SERVICE_MOBILE_TOPUP));
        services.add(new ServiceModel("Raast\nPayment", IconRegistry.SERVICE_RAAST));
        services.add(new ServiceModel("Bill\nPayments", IconRegistry.SERVICE_BILL_PAYMENT));
        services.add(new ServiceModel("Card\nManagement", IconRegistry.SERVICE_CARD_MANAGEMENT));
        services.add(new ServiceModel("QR\nPayments", IconRegistry.SERVICE_QR_PAYMENTS));
        services.add(new ServiceModel("Zakat/Fitra\n& Sadqaat", IconRegistry.SERVICE_ZAKAT_FITRA));
        services.add(new ServiceModel("Al Meezan\nMutual\nFunds", IconRegistry.SERVICE_MUTUAL_FUNDS));
        services.add(new ServiceModel("Payoneer", IconRegistry.SERVICE_PAYONEER));
        services.add(new ServiceModel("Tax & Other\nCertificates", IconRegistry.SERVICE_TAX_CERTIFICATES));
        services.add(new ServiceModel("Limit\nManagement", IconRegistry.SERVICE_LIMIT_MANAGEMENT));
        services.add(new ServiceModel("Feedback", IconRegistry.SERVICE_FEEDBACK));

        ServiceAdapter adapter = new ServiceAdapter(services, service -> {
            String titleClean = service.getTitle().replace("\n", " ");
            if ("Send Money".equals(titleClean)) {
                Intent intent = new Intent(MainActivity.this, SendMoneyActivity.class);
                startActivity(intent);
            } else if ("Card Management".equals(titleClean)) {
                Intent intent = new Intent(MainActivity.this, CardManagementActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, titleClean + " selected (Demo)", Toast.LENGTH_SHORT).show();
            }
        });

        rvGrid.setAdapter(adapter);
    }

    private void setupDrawerAndActions() {
        // Sidebar / Drawer trigger
        findViewById(R.id.iv_header_sidebar).setOnClickListener(v -> {
            if (drawerLayout != null) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Power button / Logout
        findViewById(R.id.iv_header_power).setOnClickListener(v -> showLogoutDialog());

        // Refresh Card Button
        ivRefresh.setOnClickListener(v -> {
            ObjectAnimator animator = ObjectAnimator.ofFloat(ivRefresh, "rotation", 0f, 360f);
            animator.setDuration(600);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();
            Toast.makeText(this, "Account balance updated", Toast.LENGTH_SHORT).show();
        });

        // Share Button Action
        findViewById(R.id.btn_share).setOnClickListener(v -> showShareDialog());

        // Transactions Button Action
        findViewById(R.id.btn_transactions).setOnClickListener(v -> showTransactionsDialog());

        // Drawer Items
        setupDrawerMenu();
    }

    private void setupDrawerMenu() {
        View menuHome = findViewById(R.id.menu_home);
        View menuAccounts = findViewById(R.id.menu_accounts);
        View menuTransfers = findViewById(R.id.menu_transfers);
        View menuSettings = findViewById(R.id.menu_settings);
        View menuLogout = findViewById(R.id.menu_logout);

        if (menuHome != null) menuHome.setOnClickListener(v -> closeDrawerAndToast("Home"));
        if (menuAccounts != null) menuAccounts.setOnClickListener(v -> closeDrawerAndToast("Accounts"));
        if (menuTransfers != null) menuTransfers.setOnClickListener(v -> closeDrawerAndToast("Transfers & Payments"));
        if (menuSettings != null) menuSettings.setOnClickListener(v -> closeDrawerAndToast("Settings"));
        if (menuLogout != null) menuLogout.setOnClickListener(v -> {
            if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
            showLogoutDialog();
        });
    }

    private void closeDrawerAndToast(String menuName) {
        if (drawerLayout != null) drawerLayout.closeDrawer(GravityCompat.START);
        Toast.makeText(this, menuName + " selected", Toast.LENGTH_SHORT).show();
    }

    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out of Meezan Bank?")
                .setPositiveButton("Log Out", (dialog, which) ->
                        Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showShareDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_share_bottom_sheet, findViewById(android.R.id.content), false);

        view.findViewById(R.id.btn_copy_details).setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Meezan Account Details",
                    "Account Title: MUHAMMAD FAHAD\nSaving Account: 1042 0113203444\nBranch: GULSHAN E IQBAL 13D 2");
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Account details copied to clipboard", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });

        view.findViewById(R.id.btn_share_details).setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Meezan Bank Account Details:\nAccount Title: MUHAMMAD FAHAD\nSaving Account: 1042 0113203444\nBranch: GULSHAN E IQBAL 13D 2");
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, "Share Account Details via");
            startActivity(shareIntent);
            dialog.dismiss();
        });

        dialog.setContentView(view);
        dialog.show();
    }

    private void showTransactionsDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_transactions_bottom_sheet, findViewById(android.R.id.content), false);

        RecyclerView rvTrans = view.findViewById(R.id.rv_transactions);
        rvTrans.setLayoutManager(new LinearLayoutManager(this));

        List<TransactionItem> transactions = new ArrayList<>();
        transactions.add(new TransactionItem("Fund Transfer to Fahad", "12 Feb 2025, 02:15 PM", "- PKR 2,500.00"));
        transactions.add(new TransactionItem("Mobile Topup - Jazz", "10 Feb 2025, 11:30 AM", "- PKR 500.00"));
        transactions.add(new TransactionItem("Profit Deposit", "01 Feb 2025, 09:00 AM", "+ PKR 1,240.50"));
        transactions.add(new TransactionItem("K-Electric Bill Payment", "28 Jan 2025, 04:45 PM", "- PKR 4,200.00"));

        rvTrans.setAdapter(new TransactionsAdapter(transactions));

        dialog.setContentView(view);
        dialog.show();
    }

    private static class TransactionItem {
        String title, date, amount;
        TransactionItem(String title, String date, String amount) {
            this.title = title;
            this.date = date;
            this.amount = amount;
        }
    }

    private static class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.ViewHolder> {
        private final List<TransactionItem> list;
        TransactionsAdapter(List<TransactionItem> list) { this.list = list; }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_transaction, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TransactionItem item = list.get(position);
            holder.tvTitle.setText(item.title);
            holder.tvDate.setText(item.date);
            holder.tvAmount.setText(item.amount);
            if (item.amount.startsWith("+")) {
                holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.meezan_green));
            } else {
                holder.tvAmount.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.meezan_purple));
            }
        }

        @Override
        public int getItemCount() { return list.size(); }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDate, tvAmount;
            ViewHolder(View v) {
                super(v);
                tvTitle = v.findViewById(R.id.tv_trans_title);
                tvDate = v.findViewById(R.id.tv_trans_date);
                tvAmount = v.findViewById(R.id.tv_trans_amount);
            }
        }
    }
}
