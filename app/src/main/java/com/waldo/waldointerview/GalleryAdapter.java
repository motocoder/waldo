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

/**
 * Created by useruser on 9/10/18.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private static final int FACTOR = 1000;

    private List<Record> galleryList;
    private Context context;

    public GalleryAdapter(Context context, List<Record> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
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

        Picasso.get().cancelRequest(viewHolder.img);
        Picasso.get().load(record.getImageURL()).into(viewHolder.img);

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);

        Picasso.get().cancelRequest(holder.img);
    }

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {

        Picasso.get().cancelRequest(holder.img);
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

        Picasso.get().cancelRequest(holder.img);
    }

    @Override
    public int getItemCount() {
        return galleryList.size() * FACTOR;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public ViewHolder(View view) {
            super(view);

            img = (ImageView) view.findViewById(R.id.img);
        }
    }
}