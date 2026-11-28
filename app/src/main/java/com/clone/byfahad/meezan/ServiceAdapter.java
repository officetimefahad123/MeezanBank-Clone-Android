package com.clone.byfahad.meezan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    public interface OnServiceClickListener {
        void onServiceClick(ServiceModel service);
    }

    private final List<ServiceModel> serviceList;
    private final OnServiceClickListener clickListener;

    public ServiceAdapter(List<ServiceModel> serviceList, OnServiceClickListener clickListener) {
        this.serviceList = serviceList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service_tile, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceModel service = serviceList.get(position);
        holder.tvTitle.setText(service.getTitle());
        AssetUtils.setAssetToImageView(holder.ivIcon, service.getIconAssetPath());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onServiceClick(service);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_service_icon);
            tvTitle = itemView.findViewById(R.id.tv_service_title);
        }
    }
}
