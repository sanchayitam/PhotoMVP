package com.softpian.photomvp.util;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softpian.photomvp.R;

public class GlideImageView extends AppCompatImageView {

    public GlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void loadImage(String url) {
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_photo_placeholder)
                            .centerCrop())
                .into(this);
    }
}
