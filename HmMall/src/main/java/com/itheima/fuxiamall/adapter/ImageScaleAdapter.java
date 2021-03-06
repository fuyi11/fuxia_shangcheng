package com.itheima.fuxiamall.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.global.Url;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by lxj on 2016/9/5.
 */
public class ImageScaleAdapter extends PagerAdapter {

    private ArrayList<String> urlList;

    public ImageScaleAdapter(ArrayList<String> urlList) {
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        PhotoView imageView = new PhotoView(container.getContext());

        Glide.with(container.getContext())
             .load(Url.ImagePrefix+urlList.get(position))
             .centerCrop()
             .placeholder(R.drawable.default_image)
             .crossFade(500)
             .into(imageView);

        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
