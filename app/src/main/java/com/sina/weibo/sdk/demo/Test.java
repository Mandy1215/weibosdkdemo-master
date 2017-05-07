package com.sina.weibo.sdk.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Mandy on 2017/4/19.
 */

public class Test extends Activity {
//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);
        ImageView image = (ImageView) findViewById(R.id.myPicture);
        Glide.with(this).load(R.drawable.ic_logo).transform(new GlideCircleTransform(this)).into(image);
    }
}
