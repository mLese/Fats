package com.deceax.fats;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;


public class PropertyChooserActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View.OnClickListener mOnCardClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            transition(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildCourseList();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_chooser);

        mRecyclerView = (RecyclerView) findViewById(R.id.property_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PropertyAdapter(mProperties, mOnCardClick);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void transition(View view) {
        int i = mRecyclerView.getChildPosition(view);

        PropertyAdapter.ViewHolder viewHolder =
                (PropertyAdapter.ViewHolder) mRecyclerView.getChildViewHolder(view);

        View imageView = viewHolder.mPropertyImage;
        View textView = viewHolder.mPropertyName;

        Intent intent = new Intent(this, PropertyDetailActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this, Pair.create(imageView, "property_image"), Pair.create(textView, "property_name"));
        intent.putExtra("index", i);

        startActivity(intent, options.toBundle());
    }

    Property[] mProperties = new Property[6];

    private void buildCourseList() {
        mProperties[0] = new Property("Snowy", getResources().getDrawable(R.drawable.snowy));
        mProperties[1] = new Property("Tombstone", getResources().getDrawable(R.drawable.tombstone));
        mProperties[2] = new Property("Sunset", getResources().getDrawable(R.drawable.sunset));
        mProperties[3] = new Property("Basket", getResources().getDrawable(R.drawable.basket));
        mProperties[4] = new Property("Stormy", getResources().getDrawable(R.drawable.stormy));
        mProperties[5] = new Property("Bath", getResources().getDrawable(R.drawable.bath));
    }
}
