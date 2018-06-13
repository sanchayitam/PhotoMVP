package com.softpian.photomvp.data.source;

import com.softpian.photomvp.data.photodata.PhotoInfo;
import com.softpian.photomvp.data.photodata.PhotoResponse;

import retrofit2.Call;

public interface PhotoDataSource {

    Call<PhotoResponse> getFlickrPhotosSearch(String text, int page, int perPage);

    Call<PhotoInfo> getFlickrPhotoInfo(String photoId);
}
