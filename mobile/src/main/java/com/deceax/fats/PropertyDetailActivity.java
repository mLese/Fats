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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildCourseList();
        final int i = getIntent().getExtras().getInt("course_index");

        View view = getLayoutInflater().inflate(R.layout.activity_property_detail, null);

        final TextView courseName = (TextView) view.findViewById(R.id.detail_course_name);
        final ImageView courseImage = (ImageView) view.findViewById(R.id.detail_property_image);
        courseImage.setImageDrawable(mProperties[i].mPropertyImage);

        Palette.generateAsync(BitmapFactory.decodeResource(this.getResources(), mImageIds[i]), new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                courseName.setText(mProperties[i].mPropertyName);
                courseName.setBackgroundColor(getColor(palette));
                   //courseName.setBackgroundColor(palette.getPallete().get(0).getRgb());
            }
        });

        setContentView(view);

        //Outline
        int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline outline = new Outline();
        outline.setOval(0, 0, size, size);
        final View fab = findViewById(R.id.fab);
        final View map = findViewById(R.id.detail_map_image);
        fab.setOutline(outline);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cx = (fab.getLeft() + fab.getRight()) / 2;
                int cy = (fab.getTop() + fab.getBottom()) / 2;
                float radius = Math.max(map.getWidth(), map.getHeight()) * 2.0f;

                if (map.getVisibility() == View.INVISIBLE) {
                    map.setVisibility(View.VISIBLE);
                    ViewAnimationUtils.createCircularReveal(map, cx, cy, 0, radius).start();
                } else {
                    ValueAnimator reveal = ViewAnimationUtils.createCircularReveal(map, cx, cy, radius, 0);
                    reveal.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            map.setVisibility(View.INVISIBLE);
                        }
                    });
                    reveal.start();
                }
            }
        });
    }

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
