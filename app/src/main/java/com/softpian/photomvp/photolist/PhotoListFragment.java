package com.softpian.photomvp.photolist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.softpian.photomvp.R;
import com.softpian.photomvp.data.source.PhotoRepository;
import com.softpian.photomvp.photodetail.PhotoDetailFragment;
import com.softpian.photomvp.photodetail.PhotoDetailPresenter;
import com.softpian.photomvp.photolist.adapter.OnPhotoClickedListener;
import com.softpian.photomvp.photolist.adapter.PhotoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoListFragment extends Fragment implements PhotoListContract.View {

    private PhotoListContract.Presenter mPresenter;

    private Unbinder mUnbinder;

    @BindView(R.id.rvPhotoList) RecyclerView mPhotoListView;
    @BindView(R.id.pbIsLoading) ProgressBar mLoadingProgress;

    public static PhotoListFragment newInstance() {
        return new PhotoListFragment();
    }

    @Override
    public void setPresenter(PhotoListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    private final RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            // TODO: should write code to help user keep scrolling at the last item


        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OnPhotoClickedListener listener = item -> {
            showBottomSheetDialog(item.getId());
        };

        PhotoAdapter photoAdapter = new PhotoAdapter(listener);

        mPhotoListView.setAdapter(photoAdapter);
        mPhotoListView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        mPhotoListView.addOnScrollListener(mOnScrollListener);

        mPresenter.setPhotoRecyclerViewModel(photoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPhotoListView.removeOnScrollListener(mOnScrollListener);
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
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
            Toast.makeText(getContext(), "Loading photos failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showBottomSheetDialog(String photoId) {
        PhotoDetailFragment photoDetailFragment = PhotoDetailFragment.newInstance(photoId);
        photoDetailFragment.show(getActivity().getSupportFragmentManager(), "PhotoDetailFragment");

        PhotoDetailPresenter photoDetailPresenter
                = new PhotoDetailPresenter(new PhotoRepository(), photoDetailFragment);

    }
}
