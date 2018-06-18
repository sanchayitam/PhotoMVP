package com.softpian.photomvp.photolist;

import com.softpian.photomvp.BasePresenter;
import com.softpian.photomvp.BaseView;
import com.softpian.photomvp.photolist.adapter.PhotoRecyclerViewModel;

public class PhotoListContract {

    interface View extends BaseView<Presenter> {

        void hideProgress();

        void showProgress();

        void notifyLoadingFailed();

        void notifyLoadingFailed(int code, String message);

        void notifyLoadingFailed(Throwable t);

        void showBottomSheetDialog(String photoId);
    }

    interface Presenter extends BasePresenter {

        void loadFlickrImage(int page);

        void setPhotoRecyclerViewModel(PhotoRecyclerViewModel photoRecyclerViewModel);
    }
}
