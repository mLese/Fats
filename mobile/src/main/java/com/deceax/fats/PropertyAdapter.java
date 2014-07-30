package com.deceax.fats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {
    private Property[] mDataset;
    private View.OnClickListener mOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mCardView;
        public TextView mPropertyName;
        public ImageView mPropertyImage;

        public ViewHolder(View v) {
            super(v);
            mCardView = v;
            mPropertyName = (TextView)mCardView.findViewById(R.id.property_name);
            mPropertyImage = (ImageView)mCardView.findViewById(R.id.property_image);
        }
    }

    public PropertyAdapter(Property[] dataset, View.OnClickListener onClickListener) {
        mDataset = dataset;
        mOnClickListener = onClickListener;
    }

    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_property_card, parent, false);
        v.setOnClickListener(mOnClickListener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mPropertyName.setText(mDataset[position].mPropertyName);
        holder.mPropertyImage.setImageDrawable(mDataset[position].mPropertyImage);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
