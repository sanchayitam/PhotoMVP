package com.softpian.photomvp.data.source;

import com.softpian.photomvp.data.photodata.PhotoInfo;
import com.softpian.photomvp.data.photodata.PhotoResponse;
import com.softpian.photomvp.data.source.remote.PhotoRemoteDataSource;

import retrofit2.Call;

public class PhotoRepository implements PhotoDataSource {

    private final PhotoRemoteDataSource mPhotoRemoteDataSource = new PhotoRemoteDataSource();

    @Override
    public Call<PhotoResponse> getFlickrPhotosSearch(String text, int page, int perPage) {
        return mPhotoRemoteDataSource.getFlickrPhotosSearch(text, page, perPage);
    }

    @Override
    public Call<PhotoInfo> getFlickrPhotoInfo(String photoId) {
        return mPhotoRemoteDataSource.getFlickrPhotoInfo(photoId);
    }
}
