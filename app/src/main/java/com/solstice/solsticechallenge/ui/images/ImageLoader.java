package com.solstice.solsticechallenge.ui.images;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface ImageLoader {

    public void load(String url, ImageView imageView);

    public void load(String url, ImageView imageView, @DrawableRes int placeholderImageId, @DrawableRes int errorImageId);
}
