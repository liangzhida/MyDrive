package com.example.mydrive.fragmemt;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydrive.MainActivity;
import com.example.mydrive.R;
import com.example.mydrive.adapter.MyFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoshuoFragment extends Fragment {

    private FragmentActivity activity;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private MyFragmentPagerAdapter adapter;
    private TabLayout.Tab index;
    private TabLayout.Tab paixin;
    private TabLayout.Tab xiaoshuoku;


    public XiaoshuoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xiaoshuo, container, false);
        activity = getActivity();
        initView(view);
        init();
        return view;
    }

    private void init() {

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab==tabLayout.getTabAt(0)){
                    viewpager.setCurrentItem(0);
                }else if(tab==tabLayout.getTabAt(1)){
                    viewpager.setCurrentItem(1);
                }else if(tab==tabLayout.getTabAt(2)){
                    viewpager.setCurrentItem(2);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        adapter = new MyFragmentPagerAdapter(getActivity());
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);


    }


    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
       tabLayout.setupWithViewPager(viewpager);
        index=tabLayout.getTabAt(0);
        paixin=tabLayout.getTabAt(1);
        xiaoshuoku=tabLayout.getTabAt(2);
        adapter=new MyFragmentPagerAdapter(getActivity()){
        };
        //將ViewPager與適配器綁定起來
        viewpager.setAdapter(adapter);
    }
}
