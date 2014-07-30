package com.deceax.fats;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class PropertyChooserActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View.OnClickListener mOnCardClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int i = mRecyclerView.getChildPosition(view);
            transition(i);
        }
    };

    private void transition(int i) {
        Intent intent = new Intent(this, PropertyDetailActivity.class);
        intent.putExtra("course_index", i);
        startActivity(intent);
    }

    Property[] mProperties = new Property[6];

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

    private void buildCourseList() {
        mProperties[0] = new Property("Snowy", getResources().getDrawable(R.drawable.snowy));
        mProperties[1] = new Property("Tombstone", getResources().getDrawable(R.drawable.tombstone));
        mProperties[2] = new Property("Sunset", getResources().getDrawable(R.drawable.sunset));
        mProperties[3] = new Property("Basket", getResources().getDrawable(R.drawable.basket));
        mProperties[4] = new Property("Stormy", getResources().getDrawable(R.drawable.stormy));
        mProperties[5] = new Property("Bath", getResources().getDrawable(R.drawable.bath));
    }
}
