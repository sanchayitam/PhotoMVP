package com.softpian.photomvp.photodetail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.softpian.photomvp.R;
import com.softpian.photomvp.util.GlideImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoDetailFragment extends BottomSheetDialogFragment implements PhotoDetailContract.View{

    private static final String PHOTO_ID_KEY = "photo_id";

    private PhotoDetailContract.Presenter mPresenter;

    private Unbinder mUnbinder;

    @BindView(R.id.app_bar) AppBarLayout mAppBarLayout;
    @BindView(R.id.ivClose) ImageView mCloseView;
    @BindView(R.id.givBuddyIcon) GlideImageView mBuddyIconGlideView;
    @BindView(R.id.tvOwnerName) TextView mOwnerName;
    @BindView(R.id.givPhoto) GlideImageView mPhotoGlideView;
    @BindView(R.id.detailedContentLayout) ConstraintLayout mDetailedContentLayout;
    @BindView(R.id.tvTitle) TextView mTitleView;
    @BindView(R.id.tvDate) TextView mDateView;
    @BindView(R.id.tvCommentCount) TextView mCommentCountView;
    @BindView(R.id.tvViewerCount) TextView mViewerCountView;
    @BindView(R.id.tvDescription) TextView mDescriptionView;
    @BindView(R.id.pbIsLoading) ProgressBar mLoadingProgress;

    public static PhotoDetailFragment newInstance(String photoId) {

        PhotoDetailFragment fragment = new PhotoDetailFragment();

        Bundle args = new Bundle();
        args.putString(PHOTO_ID_KEY, photoId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void setPresenter(PhotoDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog d = (BottomSheetDialog) dialogInterface;

            View bottomSheet = (View) d.findViewById(android.support.design.R.id.design_bottom_sheet);
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetBehavior.setPeekHeight(30);
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        if (!isRemoving()) {
                            dismiss();
                        }
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
            });
        });


        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPhotoGlideView.setOnClickListener(photoView -> {
            changeViewsVisibilityWitoutPhotoView();
        });

        mCloseView.setOnClickListener(closeView -> {
            dismiss();
        });

        String photoId = getArguments().getString(PHOTO_ID_KEY);

        mPresenter.loadPhotoInfo(photoId);
    }

    private void changeViewsVisibilityWitoutPhotoView() {
        if (mAppBarLayout.getVisibility() == View.VISIBLE) {
            mAppBarLayout.setVisibility(View.INVISIBLE);
            mDetailedContentLayout.setVisibility(View.INVISIBLE);
            getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        } else {
            mAppBarLayout.setVisibility(View.VISIBLE);
            mDetailedContentLayout.setVisibility(View.VISIBLE);
            getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showToolbarItem(String buddyIconUrl, String ownerName) {
        mBuddyIconGlideView.loadImage(buddyIconUrl);
        mOwnerName.setText(ownerName);
    }

    @Override
    public void showEverythingWithoutToolbar(String photoUrl, String title, String date, String commentCount, String viewCount, String description) {
        mPhotoGlideView.loadImage(photoUrl);
        mTitleView.setText(title);
        mDateView.setText(date);
        mCommentCountView.setText(commentCount);
        mViewerCountView.setText(viewCount);
        mDescriptionView.setText(description);
    }

    @Override
    public void hideProgress() {
        mLoadingProgress.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        mLoadingProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyLoadingFailed() {
        if (!isDetached()) {
            Toast.makeText(getContext(), "Loading photo failed", Toast.LENGTH_LONG).show();
        }
    }
}
