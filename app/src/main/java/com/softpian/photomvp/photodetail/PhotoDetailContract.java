package com.softpian.photomvp.photodetail;

import com.softpian.photomvp.BasePresenter;
import com.softpian.photomvp.BaseView;

public class PhotoDetailContract {

    interface View extends BaseView<Presenter> {

        void hideProgress();

        void showProgress();

        void notifyLoadingFailed();

        void showToolbarItem(String buddyIconUrl, String ownerName);

        void showEverythingWithoutToolbar(String photoUrl, String title, String date, String commentCount, String viewCount, String description);
    }

    interface Presenter extends BasePresenter {

        void loadPhotoInfo(String photoId);
    }
}
