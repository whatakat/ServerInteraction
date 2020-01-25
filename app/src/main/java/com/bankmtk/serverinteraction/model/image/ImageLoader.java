package com.bankmtk.serverinteraction.model.image;

import androidx.annotation.Nullable;

public interface ImageLoader<T> {
    void loadInto(@Nullable String url, T container);
}
