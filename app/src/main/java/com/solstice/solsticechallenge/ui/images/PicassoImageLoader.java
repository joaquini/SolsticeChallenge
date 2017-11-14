package com.solstice.solsticechallenge.ui.images;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    @Inject
    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public void load(String url, ImageView imageView) {
        picasso
                .load(url)
                .into(imageView);
    }

    @Override
    public void load(String url, ImageView imageView, int placeholderImageId, int errorImageId) {
        picasso
                .load(url)
                .placeholder(placeholderImageId)
                .error(errorImageId)
                .into(imageView);
    }
}
