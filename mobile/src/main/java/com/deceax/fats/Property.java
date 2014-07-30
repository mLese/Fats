package com.deceax.fats;

import android.graphics.drawable.Drawable;

/**
 * Created by deceax on 6/27/2014.
 */
public class Property {
    public Drawable mPropertyImage;
    public String mPropertyName;

    public Property(String name, Drawable drawable) {
        mPropertyName = name;
        mPropertyImage = drawable;
    }
}
