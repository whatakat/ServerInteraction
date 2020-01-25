package com.bankmtk.serverinteraction.model.image.android;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import com.bankmtk.serverinteraction.model.image.ImageLoader;

public class ImageLoaderPicasso implements ImageLoader<ImageView> {
    @Override
    public void loadInto(@Nullable String url, ImageView container)
    {
        Picasso.get().load(url).into(container);
    }
}
