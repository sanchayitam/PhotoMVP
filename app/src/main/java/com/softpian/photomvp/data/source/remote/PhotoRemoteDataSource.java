package com.softpian.photomvp.data.source.remote;

import com.softpian.photomvp.data.photodata.PhotoInfo;
import com.softpian.photomvp.data.photodata.PhotoResponse;
import com.softpian.photomvp.data.source.PhotoDataSource;
import com.softpian.photomvp.network.FlickrPhotoApi;
import com.softpian.photomvp.network.RestfulService;
import com.softpian.photomvp.util.Constant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class PhotoRemoteDataSource implements PhotoDataSource {

    private final FlickrPhotoApi mFlickrPhotoApi
            = RestfulService.getInstance().createRetrofit(FlickrPhotoApi.class, Constant.BASE_URL);

    @Override
    public Call<PhotoResponse> getFlickrPhotosSearch(String text, int page, int perPage) {

        Map<String, String> queryParameters = new HashMap<>();
        queryParameters.put("api_key", Constant.FLICKR_API_KEY);
        queryParameters.put("safe_search", Constant.SAFE_SEARCH);
        queryParameters.put("text", text);
        queryParameters.put("page", String.valueOf(page));
        queryParameters.put("perpage", String.valueOf(perPage));

        return mFlickrPhotoApi.getFlickrPhotosSearch(queryParameters);
    }

    @Override
    public Call<PhotoInfo> getFlickrPhotoInfo(String photoId) {
        return mFlickrPhotoApi.getFlickrPhotoInfo(Constant.FLICKR_API_KEY, photoId);
    }
}
