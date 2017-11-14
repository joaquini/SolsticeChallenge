package com.solstice.solsticechallenge.dagger.module;

import android.content.Context;

import com.solstice.solsticechallenge.ui.images.ImageLoader;
import com.solstice.solsticechallenge.ui.images.PicassoImageLoader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageLoadingModule {

    @Provides
    @Singleton
    Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Picasso picasso) {
        return new PicassoImageLoader(picasso);
    }
}
