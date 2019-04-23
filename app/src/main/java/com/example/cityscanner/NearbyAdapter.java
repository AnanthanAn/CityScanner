package com.example.cityscanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.NearbyViewHolder> {

    private ArrayList<NearbyItem> mNearbyItems;


    public static class NearbyViewHolder extends RecyclerView.ViewHolder {
        //public ImageView mImageView;
        public TextView mShopName;
        public TextView mShopAdd;
        public TextView mShopPhone;
        public NearbyViewHolder(@NonNull View itemView) {
            super(itemView);
            //mImageView = itemView.findViewById(R.id.nearby_imageview);
            mShopName = itemView.findViewById(R.id.nearby_shopName);
            mShopAdd = itemView.findViewById(R.id.nearby_shopAddress);
            mShopPhone = itemView.findViewById(R.id.nearby_phone);
        }
    }

    public NearbyAdapter(ArrayList<NearbyItem> nearbyItems) {
        mNearbyItems = nearbyItems;
    }

    @NonNull
    @Override
    public NearbyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nearby_item,viewGroup,false);
        NearbyViewHolder nearbyViewHolder = new NearbyViewHolder(v);
        return nearbyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyViewHolder nearbyViewHolder, int i) {
        NearbyItem currentItem = mNearbyItems.get(i);

        //nearbyViewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        nearbyViewHolder.mShopName.setText(currentItem.getmShopName());
        nearbyViewHolder.mShopAdd.setText(currentItem.getmShopAddress());
        nearbyViewHolder.mShopPhone.setText(currentItem.getmShopphone());

    }

    @Override
    public int getItemCount() {
        return mNearbyItems.size();
    }


}
