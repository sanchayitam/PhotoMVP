package com.softpian.photomvp.photodetail;

import android.util.Log;

import com.softpian.photomvp.data.photodata.Photo;
import com.softpian.photomvp.data.photodata.PhotoInfo;
import com.softpian.photomvp.data.source.PhotoRepository;
import com.softpian.photomvp.util.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDetailPresenter implements PhotoDetailContract.Presenter {

    private final String TAG = PhotoDetailPresenter.class.getSimpleName();

    private final PhotoRepository mPhotoRepository;

    private final PhotoDetailContract.View mView;

    public PhotoDetailPresenter(PhotoRepository photoRepository, PhotoDetailContract.View view) {
        mPhotoRepository = photoRepository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {}

    @Override
    public void loadPhotoInfo(String photoId) {

        mView.showProgress();

        mPhotoRepository.getFlickrPhotoInfo(photoId)
                .enqueue(new Callback<PhotoInfo>() {
                    @Override
                    public void onResponse(Call<PhotoInfo> call, Response<PhotoInfo> response) {

                        if (response.isSuccessful()) {
                            PhotoInfo photoInfo = response.body();
                            if (photoInfo != null && "ok".equals(photoInfo.getStat())) {

                                Photo photo = photoInfo.getPhoto();

                                mView.showToolbarItem(photo.getOwner().getBuddyIcon(), photo.getOwner().getUsername());

                                String commentCountFormatted = CommonUtils.getNumberFormatted(photo.getComments().getContent());
                                String viewCountFormatted = CommonUtils.getNumberFormatted(photo.getViews());
                                String dateLastUpdated = CommonUtils.getDateFromUnixTimestamp(photo.getDates().getLastupdate());

                                mView.showEverythingWithoutToolbar(photo.getPhotoUrl(), photo.getTitle().getContent(), dateLastUpdated,
                                                                    commentCountFormatted, viewCountFormatted, photo.getDescription().getContent());
                            } else {
                                if (photoInfo != null) {
                                    mView.notifyLoadingFailed(photoInfo.getHttpStatusCode(), photoInfo.getErrorMessage());
                                    Log.e(TAG, "No Response body or The status from Server is " + photoInfo.getStat());
                                    Log.e(TAG, "Flickr Response code: " + photoInfo.getHttpStatusCode() + ", message: " + photoInfo.getErrorMessage());
                                } else {
                                    mView.notifyLoadingFailed();
                                    Log.e(TAG, "No Response body or The status from Server is not 'ok'");
                                    Log.e(TAG, "HTTP Response code: " + response.code() + ", message: " + response.message());
                                }
                            }
                        } else {
                            mView.notifyLoadingFailed(response.code(), response.message());
                            Log.e(TAG, "Requesting to server failed, HTTP Response code: " + response.code() + ", message: " + response.message());
                        }

                        mView.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<PhotoInfo> call, Throwable t) {

                        mView.hideProgress();
                        mView.notifyLoadingFailed(t);
                        Log.e(TAG, "Network exception occurred!, cause: " + t.getCause());
                        Log.e(TAG, "Network exception occurred!, message: " + t.getMessage());
                    }
                });
    }
}
