package com.raywenderlich.galacticon;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * Created by t20149841 on 10/9/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoHolder>{

    private ArrayList<Photo> mPhotos;

    public static class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mItemImage;
        private TextView mItemDate;
        private TextView mItemDescription;
        private Photo mPhoto;
        private static final String PHOTO_KEY = "PHOTO";

        public PhotoHolder(View v){
            super(v);
            mItemImage = (ImageView)v.findViewById(R.id.item_image);
            mItemDate = (TextView)v.findViewById(R.id.item_date);
            mItemDescription = (TextView)v.findViewById(R.id.item_description);

            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            Log.d("Recycler", "Click!");
            //New intent that passes info to the photo activity class
            Context context = itemView.getContext();
            Intent showPhotoIntent = new Intent(context, PhotoActivity.class);
            showPhotoIntent.putExtra(PHOTO_KEY, mPhoto);
            context.startActivity(showPhotoIntent);
        }

        //Use the Picasso lib again
        public void bindPhoto(Photo photo) {
            mPhoto = photo;
            Picasso.with(mItemImage.getContext()).load(photo.getUrl()).into(mItemImage);
            mItemDate.setText(photo.getHumanDate());
            mItemDescription.setText(photo.getExplanation());
        }
    }

    public  RecyclerAdapter(ArrayList<Photo> photos){
        mPhotos = photos;
    }

    @Override
    public RecyclerAdapter.PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //This is where the inflate statement goes
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);

        return new PhotoHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.PhotoHolder holder, int position) {

        Photo itemPhoto = mPhotos.get(position);
        holder.bindPhoto(itemPhoto);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
