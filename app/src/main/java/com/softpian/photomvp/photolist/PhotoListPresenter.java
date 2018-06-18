package com.softpian.photomvp.photolist;

import android.util.Log;

import com.softpian.photomvp.data.photodata.PhotoResponse;
import com.softpian.photomvp.data.source.PhotoRepository;
import com.softpian.photomvp.photolist.adapter.PhotoRecyclerViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoListPresenter implements PhotoListContract.Presenter {

    private final String TAG = PhotoListPresenter.class.getSimpleName();

    private final PhotoRepository mPhotoRepository;

    private final PhotoListContract.View mView;

    private PhotoRecyclerViewModel mPhotoRecyclerViewModel;

    public PhotoListPresenter(PhotoRepository photoRepository, PhotoListContract.View view) {

        mPhotoRepository = photoRepository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void setPhotoRecyclerViewModel(PhotoRecyclerViewModel photoRecyclerViewModel) {

        mPhotoRecyclerViewModel = photoRecyclerViewModel;
    }

    @Override
    public void start() {
        loadFlickrImage(1);
    }

    @Override
    public void loadFlickrImage(int page) {

        // searchFor is hard coded
        // : Change from BeautifulNewZealand to BeautifulNature in order to check an infinite scrolling feature
        String searchFor = "BeautifulNature";
        int perPage = 30;

        mView.showProgress();

        mPhotoRepository.getFlickrPhotosSearch(searchFor, page, perPage)
                .enqueue(new Callback<PhotoResponse>() {
                    @Override
                    public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {

                        if (response.isSuccessful()) {
                            PhotoResponse photoResponse = response.body();
                            if (photoResponse != null && "ok".equals(photoResponse.getStat())) {
                                int curSize = mPhotoRecyclerViewModel.getItemCount();
                                int newSize = photoResponse.getPhotos().getPhoto().size();
                                mPhotoRecyclerViewModel.addAllItem(photoResponse.getPhotos().getPhoto());
                                mPhotoRecyclerViewModel.notifyItemRangeInsertedWrapper(curSize, newSize);
                            } else {
                                if (photoResponse != null) {
                                    mView.notifyLoadingFailed(photoResponse.getHttpStatusCode(), photoResponse.getErrorMessage());
                                    Log.e(TAG, "No Response body or The status from Server is " + photoResponse.getStat());
                                    Log.e(TAG, "Flickr Response code: " + photoResponse.getHttpStatusCode() + ", message: " + photoResponse.getErrorMessage());
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
                    public void onFailure(Call<PhotoResponse> call, Throwable t) {

                        mView.hideProgress();
                        mView.notifyLoadingFailed(t);
                        Log.e(TAG, "Network exception occurred!, cause: " + t.getCause());
                        Log.e(TAG, "Network exception occurred!, message: " + t.getMessage());
                    }
                });
    }
}
