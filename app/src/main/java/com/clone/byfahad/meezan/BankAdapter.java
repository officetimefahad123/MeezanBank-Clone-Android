package com.clone.byfahad.meezan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.BankViewHolder> {

    public interface OnBankClickListener {
        void onBankClick(BankModel bank);
    }

    private final List<BankModel> originalList;
    private final List<BankModel> filteredList;
    private final OnBankClickListener clickListener;

    public BankAdapter(List<BankModel> bankList, OnBankClickListener clickListener) {
        this.originalList = new ArrayList<>(bankList);
        this.filteredList = new ArrayList<>(bankList);
        this.clickListener = clickListener;
    }

    public void filter(String query) {
        filteredList.clear();
        if (query == null || query.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String lowerQuery = query.toLowerCase().trim();
            for (BankModel bank : originalList) {
                if (bank.getName().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(bank);
                }
            }
        }
        notifyDataSetChanged();
    }

    public int getFilteredCount() {
        return filteredList.size();
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bank_card, parent, false);
        return new BankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder holder, int position) {
        BankModel bank = filteredList.get(position);
        holder.tvBankName.setText(bank.getName());
        AssetUtils.setAssetToImageView(holder.ivBankLogo, bank.getIconAssetPath());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onBankClick(bank);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    static class BankViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBankLogo;
        TextView tvBankName;

        public BankViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBankLogo = itemView.findViewById(R.id.iv_bank_logo);
            tvBankName = itemView.findViewById(R.id.tv_bank_name);
        }
    }
}
