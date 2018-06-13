package com.softpian.photomvp.photolist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softpian.photomvp.R;
import com.softpian.photomvp.data.photodata.PhotoItem;
import com.softpian.photomvp.util.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> implements PhotoRecyclerViewModel{

    private final List<PhotoItem> mPhotoItems = new ArrayList<>();
    private OnPhotoClickedListener mListener;

    public PhotoAdapter(OnPhotoClickedListener listener) {
        mListener = listener;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bind(mPhotoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoItems.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mPhotoItems.get(position).getId());
    }

    @Override
    public void addAllItem(List<PhotoItem> photoItems) {
        mPhotoItems.addAll(photoItems);
    }

    @Override
    public PhotoItem getItem(int position) {
        return mPhotoItems.get(position);
    }

    @Override
    public void notifyDataSetChange() {
        notifyDataSetChanged();
    }

    static final class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.givPhoto) GlideImageView mPhotoView;

        private PhotoItem mPhotoItem;

        public PhotoViewHolder(View itemView, OnPhotoClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (mPhotoItem != null) {
                    listener.onPhotoClicked(mPhotoItem);
                }
            });
        }

        void bind(PhotoItem item) {
            mPhotoItem = item;
            mPhotoView.loadImage(item.getPhotoUrl());
        }
    }
}
