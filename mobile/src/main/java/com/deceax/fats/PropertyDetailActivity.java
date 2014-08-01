package com.deceax.fats;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyDetailActivity extends Activity {

    Property[] mProperties = new Property[6];
    int[] mImageIds = new int[6];
    ImageView mFab;
    View mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildCourseList();
        final int i = getIntent().getExtras().getInt("index");

        View view = getLayoutInflater().inflate(R.layout.activity_property_detail, null);

        final TextView propertyName = (TextView) view.findViewById(R.id.detail_property_name);
        final ImageView propertyImage = (ImageView) view.findViewById(R.id.detail_property_image);
        propertyImage.setImageDrawable(mProperties[i].mPropertyImage);

        Palette.generateAsync(BitmapFactory.decodeResource(this.getResources(), mImageIds[i]), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                propertyName.setText(mProperties[i].mPropertyName);
                propertyName.setBackgroundColor(getColor(palette));
            }
        });

        setContentView(view);

        mMap = findViewById(R.id.detail_map_image);
        mFab = (ImageView)findViewById(R.id.fab);

        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);

        mFab.setOutline(outline);
        mFab.setOnClickListener(fabClickListener);
    }

    private View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int cx = (mFab.getLeft() + mFab.getRight()) / 2;
            int cy = (mFab.getTop() + mFab.getBottom()) / 2;
            float radius = Math.max(mMap.getWidth(), mMap.getHeight()) * 2.0f;

            if (mMap.getVisibility() == View.INVISIBLE) {
                mMap.setVisibility(View.VISIBLE);
                ViewAnimationUtils.createCircularReveal(mMap, cx, cy, 0, radius).start();
                mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_gallery));
            } else {
                ValueAnimator reveal = ViewAnimationUtils.createCircularReveal(mMap, cx, cy, radius, 0);
                reveal.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mMap.setVisibility(View.INVISIBLE);
                    }
                });
                reveal.start();
                mFab.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_mapmode));
            }
        }
    };

    private int getColor(Palette palette) {
        int ret;

        if (palette.getVibrantColor() != null) ret = palette.getVibrantColor().getRgb();
        else if (palette.getLightVibrantColor() != null) ret = palette.getLightVibrantColor().getRgb();
        else if (palette.getDarkVibrantColor() != null) ret = palette.getDarkVibrantColor().getRgb();
        else if (palette.getMutedColor() != null) ret = palette.getMutedColor().getRgb();
        else if (palette.getLightMutedColor() != null) ret = palette.getLightMutedColor().getRgb();
        else if (palette.getDarkMutedColor() != null) ret = palette.getDarkMutedColor().getRgb();
        else ret = getResources().getColor(R.color.white);
        return ret;
    }

    private void buildCourseList() {
        mImageIds[0] = R.drawable.snowy;
        mImageIds[1] = R.drawable.tombstone;
        mImageIds[2] = R.drawable.sunset;
        mImageIds[3] = R.drawable.basket;
        mImageIds[4] = R.drawable.stormy;
        mImageIds[5] = R.drawable.bath;

        mProperties[0] = new Property("Snowy", getResources().getDrawable(R.drawable.snowy));
        mProperties[1] = new Property("Tombstone", getResources().getDrawable(R.drawable.tombstone));
        mProperties[2] = new Property("Sunset", getResources().getDrawable(R.drawable.sunset));
        mProperties[3] = new Property("Basket", getResources().getDrawable(R.drawable.basket));
        mProperties[4] = new Property("Stormy", getResources().getDrawable(R.drawable.stormy));
        mProperties[5] = new Property("Bath", getResources().getDrawable(R.drawable.bath));
    }
}
