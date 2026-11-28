package com.clone.byfahad.meezan;

public class ServiceModel {
    private final String title;
    private final String iconAssetPath;

    public ServiceModel(String title, String iconAssetPath) {
        this.title = title;
        this.iconAssetPath = iconAssetPath;
    }

    public String getTitle() {
        return title;
    }

    public String getIconAssetPath() {
        return iconAssetPath;
    }
}
