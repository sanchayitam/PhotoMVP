package com.softpian.photomvp.photolist.adapter;

import com.softpian.photomvp.data.photodata.PhotoItem;

import java.util.List;

public interface PhotoRecyclerViewModel {

    void addAllItem(List<PhotoItem> photoItems);

    PhotoItem getItem(int position);

    int getItemCount();

    void notifyDataSetChangedWrapper();

    void notifyItemRangeInsertedWrapper(int positionStart, int itemCount);
}
