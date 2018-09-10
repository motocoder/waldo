package com.waldo.waldointerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.waldo.waldointerview.data.Record;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    //this multiplies the data size to better test performance with large datasets.
    private static final int FACTOR = 1000;

    private List<Record> galleryList;

    GalleryAdapter(final List<Record> galleryList) {
        this.galleryList = galleryList;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder viewHolder, int i) {

        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);

        final Record record = galleryList.get(i % galleryList.size());

        //cancel the image loading of previous bind then load with the current data
        Picasso.get().cancelRequest(viewHolder.img);
        Picasso.get().load(record.getImageURL()).into(viewHolder.img);

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);

        //cancel the pending image load when recycled
        Picasso.get().cancelRequest(holder.img);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {

        //cancel the pending image load if failed to recycle
        Picasso.get().cancelRequest(holder.img);
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        //cancel the pending image load if detached
        Picasso.get().cancelRequest(holder.img);
    }

    @Override
    public int getItemCount() {
        return galleryList.size() * FACTOR;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;

        /**
         *
         * @param view - root view
         */
        private ViewHolder(View view) {
            super(view);

            img = view.findViewById(R.id.img);
        }
    }
}