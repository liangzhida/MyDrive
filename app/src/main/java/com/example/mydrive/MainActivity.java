package com.example.mydrive;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mydrive.fragmemt.KaoshiFragment;
import com.example.mydrive.fragmemt.MeFragment;
import com.example.mydrive.fragmemt.XiaoshuoFragment;
import com.example.mydrive.fragmemt.ZixunFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvtitle;
    private LinearLayout linearLayout;
    private ViewPager viewpager;
    private LinearLayout linearLayout2;
    private RadioButton rb_kaoshi;
    private RadioButton rb_zixun;
    private RadioButton rb_xiaoshuo;
    private RadioButton rb_me;
    private List<Fragment> alFrafment = new ArrayList<Fragment>();
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initviewpager();
        initListener();
        pic();
    }

    private void pic() {
        Drawable drawable = getResources().getDrawable(R.drawable.test_selector);
        drawable.setBounds(0, 0, 100, 80);
        rb_kaoshi.setCompoundDrawables(null, drawable, null, null);

        Drawable drawable1 = getResources().getDrawable(R.drawable.me_selector);
        drawable1.setBounds(0, 0, 100, 80);
        rb_me.setCompoundDrawables(null, drawable1, null, null);

        Drawable drawable2 = getResources().getDrawable(R.drawable.xiaoshuo_selector);
        drawable2.setBounds(0, 0, 100, 80);
        rb_xiaoshuo.setCompoundDrawables(null, drawable2, null, null);

        Drawable drawable3 = getResources().getDrawable(R.drawable.zixun_selector);
        drawable3.setBounds(0, 0, 100, 80);
        rb_zixun.setCompoundDrawables(null, drawable3, null, null);
    }

    private void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        radioGroup.check(R.id.rb_kaoshi);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb_zixun);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb_xiaoshuo);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_kaoshi:
                        viewpager.setCurrentItem(0,false);
                        break;
                    case R.id.rb_zixun:
                        viewpager.setCurrentItem(1,false);
                        tvtitle.setText("资讯");
                        break;
                    case R.id.rb_xiaoshuo:
                        viewpager.setCurrentItem(2,false);
                        tvtitle.setText("小说");
                        break;
                    case R.id.rb_me:
                        viewpager.setCurrentItem(3,false);
                        tvtitle.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private void initviewpager() {
        alFrafment.add(new KaoshiFragment());
        alFrafment.add(new ZixunFragment());
        alFrafment.add(new XiaoshuoFragment());
        alFrafment.add(new MeFragment());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return alFrafment.get(i);
            }

            @Override
            public int getCount() {
                return alFrafment.size();
            }
        });
        viewpager.setCurrentItem(0);


    }

    private void initView() {
        tvtitle = (TextView) findViewById(R.id.tvtitle);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        rb_kaoshi = (RadioButton) findViewById(R.id.rb_kaoshi);
        rb_zixun = (RadioButton) findViewById(R.id.rb_zixun);
        rb_xiaoshuo = (RadioButton) findViewById(R.id.rb_xiaoshuo);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

    }
}
