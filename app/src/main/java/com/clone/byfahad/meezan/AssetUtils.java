package com.clone.byfahad.meezan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {

    private static final String TAG = "AssetUtils";

    public static Drawable getDrawableFromAsset(Context context, String assetPath) {
        if (context == null || assetPath == null) return null;
        try (InputStream is = context.getAssets().open(assetPath)) {
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            if (bitmap != null) {
                return new BitmapDrawable(context.getResources(), bitmap);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading asset: " + assetPath, e);
        }
        return null;
    }

    public static void setAssetToImageView(ImageView imageView, String assetPath) {
        if (imageView == null || assetPath == null) return;
        Drawable drawable = getDrawableFromAsset(imageView.getContext(), assetPath);
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }
}
