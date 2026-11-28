package com.clone.byfahad.meezan;

public class BankModel {
    private final String name;
    private final String iconAssetPath;
    private final boolean isPreferred;

    public BankModel(String name, String iconAssetPath) {
        this(name, iconAssetPath, false);
    }

    public BankModel(String name, String iconAssetPath, boolean isPreferred) {
        this.name = name;
        this.iconAssetPath = iconAssetPath;
        this.isPreferred = isPreferred;
    }

    public String getName() {
        return name;
    }

    public String getIconAssetPath() {
        return iconAssetPath;
    }

    public boolean isPreferred() {
        return isPreferred;
    }
}
