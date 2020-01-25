package com.bankmtk.serverinteraction.model.image.android;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bankmtk.serverinteraction.model.image.ImageLoader;
import com.bankmtk.serverinteraction.ui.NetworkStatus;

import java.io.ByteArrayOutputStream;

import javax.sql.DataSource;

public class ImageLoaderGlide implements ImageLoader<ImageView> {
    @Override
    public void loadInto(@Nullable String url, ImageView container) {
        if (NetworkStatus.isOnline()) {
            GlideApp.with(container.getContext()).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    resource.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Paper.book("images").write(Utils.SHA1(url), stream.toByteArray());
                    return false;
                }
            }).into(container);
        } else {
            String sha1 = Utils.SHA1(url);
            if(Paper.book("images").contains(sha1)){
                byte[] bytes = Paper.book("images").read(sha1);
                GlideApp.with(container.getContext())
                        .load(bytes)
                        .into(container);
            }
        }
    }
}
