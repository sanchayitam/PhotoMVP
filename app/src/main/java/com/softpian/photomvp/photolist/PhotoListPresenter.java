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

    private int mPage = 0;

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
        loadFlickrImage();
    }

    @Override
    public void loadFlickrImage() {

        String searchFor = "BeautifulNewZealand";   //hard coded
        int perPage = 10;

        mView.showProgress();

        mPhotoRepository.getFlickrPhotosSearch(searchFor, ++mPage, perPage)
                .enqueue(new Callback<PhotoResponse>() {
                    @Override
                    public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {

                        if (response.isSuccessful()) {
                            PhotoResponse photoResponse = response.body();
                            if (photoResponse != null && "ok".equals(photoResponse.getStat())) {
                                mPage = photoResponse.getPhotos().getPage();
                                mPhotoRecyclerViewModel.addAllItem(photoResponse.getPhotos().getPhoto());
                                mPhotoRecyclerViewModel.notifyDataSetChange();
                            } else {
                                Log.e(TAG, "No Response body or The status from Server is not 'ok'");
                                mView.notifyLoadingFailed();
                            }
                        } else {
                            mView.notifyLoadingFailed();
                            Log.e(TAG, "Requesting to server failed, HTTP Response code: " + response.code());
                        }

                        mView.hideProgress();
                    }

                    @Override
                    public void onFailure(Call<PhotoResponse> call, Throwable t) {

                        mView.hideProgress();
                        mView.notifyLoadingFailed();
                        Log.e(TAG, "Network exception occurred!, detailed exception information: " + t.getMessage());
                    }
                });
    }
}
