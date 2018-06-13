package com.softpian.photomvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.softpian.photomvp.data.source.PhotoRepository;
import com.softpian.photomvp.photolist.PhotoListFragment;
import com.softpian.photomvp.photolist.PhotoListPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhotoListFragment photoListFragment =
                (PhotoListFragment) getSupportFragmentManager().findFragmentById(R.id.screen_container);

        if (photoListFragment == null) {
            photoListFragment = PhotoListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screen_container, photoListFragment)
                    .commit();
        }

        PhotoListPresenter photoListPresenter
                = new PhotoListPresenter(new PhotoRepository(), photoListFragment);

    }
}
