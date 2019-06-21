package com.example.mydrive.fragmemt;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mydrive.R;
import com.example.mydrive.activity.ContentActivity;
import com.example.mydrive.adapter.XiaoDetailPagerAdapter;
import com.example.mydrive.bean.Infos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class XiaoshuoFragment extends Fragment {

    private FragmentActivity activity;
    private LayoutInflater minflater;
    private List<String> mTitleList = new ArrayList<>();
    private View view1, view2, view3;
    private List<View> mViewList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ArrayList<Infos> list;
    private ListView lv_dianji;
    private WebView webview;
    private ImageView img_fengmian;
    private TextView tv_xiaoshuoming;
    private TextView tv_jieshao;
    private TextView tv_xiaoshuoming1;
    private TextView tv_jieshao1;
    private TextView tv_xiaoshuoming2;
    private TextView tv_jieshao2;
    private ViewPager viewPager;
    private View view;
    private boolean isRunning = true;

    private final int[]viewpager_img={R.drawable.pic,R.drawable.pic,R.drawable.pic} ;
    private ArrayList<ImageView> viewpage_imageList;

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
        initListener();
        initShouye();
        initPaixin();
        return view;
    }

    private void initPaixin() {

    }

    private void initShouye() {
        img_fengmian = (ImageView) view1.findViewById(R.id.img_fengmian);
        tv_xiaoshuoming = (TextView) view1.findViewById(R.id.tv_xiaoshuoming);
        tv_jieshao = (TextView) view1.findViewById(R.id.tv_jieshao);
        tv_xiaoshuoming1 = (TextView) view1.findViewById(R.id.tv_xiaoshuoming1);
        tv_jieshao1 = (TextView) view1.findViewById(R.id.tv_jieshao1);
        tv_xiaoshuoming2 = (TextView) view1.findViewById(R.id.tv_xiaoshuoming2);
        tv_jieshao2 = (TextView) view1.findViewById(R.id.tv_jieshao2);
        viewPager = (ViewPager) view1.findViewById(R.id.viewPager);
        Glide.with(activity).load("http://pic.hengyan.com/files/recommend/2/5295/201410151444367137.jpg").into(img_fengmian);
        viewpage_imageList=new ArrayList<>();
        for (int i = 0; i <viewpage_imageList.size() ; i++) {
            ImageView imageView=new ImageView(activity);
            imageView.setBackgroundResource(i);
            viewpage_imageList.add(imageView);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://top.hengyan.com/").get();
                    Elements elements = document.select("div.txt").select("li#wenzi0");
                    final String a = elements.select("a").text();
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_xiaoshuoming.setText(a);

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void initListener() {
        lv_dianji = (ListView) view2.findViewById(R.id.lv_dianji);

        lv_dianji.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "你点击了"+position+"项", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, ContentActivity.class);
                intent.putExtra("url", list.get(position).getUrl() + "");
                intent.putExtra("name", list.get(position).getTitle());
                startActivity(intent);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {

                String url = "http://top.hengyan.com/";
                list = new ArrayList<Infos>();
                try {
                    Document document = Jsoup.connect(url).get();
                    Elements elements = document.select("div.item");
                    Elements elements1 = elements.select("li");
                    Elements a = elements1.select("a");
                    for (int i = 0; i < a.size(); i++) {
                        String title = a.get(i).text();
                        String url_dianji = a.get(i).attr("href");
                        Infos infos = new Infos();
                        infos.setTitle(title);
                        infos.setUrl(url_dianji);
                        list.add(infos);


                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            lv_dianji.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView tvnum;
                                    public TextView tvtitle;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;

                                        this.tvnum = (TextView) rootView.findViewById(R.id.tvnum);
                                        this.tvtitle = (TextView) rootView.findViewById(R.id.tvtitle);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return list.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return list.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(activity).inflate(R.layout.item_dianji, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tvtitle.setText(list.get(position).getTitle() + "");
                                    if (position <= 2) {
                                        viewHolder.tvnum.setText(position + 1 + "");
                                        viewHolder.tvnum.setTextColor(Color.WHITE);
                                        viewHolder.tvnum.setBackgroundColor(Color.rgb(255, 193, 37));
                                    } else {
                                        viewHolder.tvnum.setText(position + 1 + "");
                                    }


                                    return convertView;
                                }
                            });
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void init() {
        mTitleList.add("首页");
        mTitleList.add("排行榜");
        mTitleList.add("小说库");
        minflater = LayoutInflater.from(activity);
        view1 = minflater.inflate(R.layout.xiaoshuo_main_content, null);
        view2 = minflater.inflate(R.layout.xiaoshuo_main_paixin, null);
        view3 = minflater.inflate(R.layout.xiaoshuo_main_xiaoshuoku, null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        XiaoDetailPagerAdapter mAdapter = new XiaoDetailPagerAdapter(mViewList, mTitleList);
        viewpager.setAdapter(mAdapter);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(mTitleList.get(2)));
        tabLayout.setupWithViewPager(viewpager);


    }


    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);




    }
}
