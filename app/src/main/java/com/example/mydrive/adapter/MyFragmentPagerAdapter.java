package com.example.mydrive.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragmentPagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private String[] tabTitles = { "首页","排行榜","小说库"};
    private Context mContext;

    public MyFragmentPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
    @Override public Object instantiateItem(ViewGroup container, int position) {
        TextView view = new TextView(mContext);
        view.setText(tabTitles[position]);
        view.setTextColor(Color.BLACK);
        view.setTextSize(20);
        view.setGravity(Gravity.CENTER);
        container.addView(view);
        return view;
    }
    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((TextView) object);
    }
    @Override public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}

