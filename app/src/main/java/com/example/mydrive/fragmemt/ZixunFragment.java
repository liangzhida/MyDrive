package com.example.mydrive.fragmemt;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydrive.R;
import com.example.mydrive.activity.NewsActivity;
import com.example.mydrive.bean.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZixunFragment extends Fragment {


    private FragmentActivity activity;
    private ViewPager vp;
    private TextView tv_test_title;
    private LinearLayout dotLinear;
    private LinearLayout centerLinear;
    private FrameLayout frameLayout_slide;
    private TextView tv_title;
    private ListView lv_news;
    private ImageView img;
    private ArrayList<ImageView> images = new ArrayList<>();
    private int oldPosition = 0;//记录上一次点的位置
    private int currentItem;
    private ArrayList<News> list;
    private ScheduledExecutorService scheduledExecutorService;
    //图片标题
    private int imageIds[] = {
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };
    private String titles[] = new String[]{"图片1", "图片2", "图片3", "图片4", "图片5"};
    private ArrayList<View> dots = new ArrayList<View>();
    private View dot_0;
    private View dot_1;
    private View dot_2;
    private View dot_3;
    private View dot_4;


    public ZixunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zixun, container, false);
        activity = getActivity();
        init();
        initView(view);
        initdata();
        Slide();//实现滑动功能的函数
        initListener();
        lv_news.setFocusable(false);
        return view;
    }

    private void initListener() {

    }

    private void Slide() {
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(activity);
            imageView.setImageResource(imageIds[i]);
            images.add(imageView);
        }
        dots.add(dot_0);
        dots.add(dot_1);
        dots.add(dot_2);
        dots.add(dot_3);
        dots.add(dot_4);

        vp.setAdapter(new ViewPagerAdapter());
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tv_test_title.setText(titles[i]);
                oldPosition = i;
                currentItem = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //将试图移除试图组
            View v = images.get(position);
            container.removeView(v);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //将试图添加进试图组
            View v = images.get(position);
            container.addView(v);
            return v;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔三秒换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 3, 3, TimeUnit.SECONDS);//TimeUnit.SECONDS);

    }

    //实现一个碎片的接口
    class ViewPagerTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            //更新界面
            handler.obtainMessage().sendToTarget();
        }
    }

    //在handler进行碎片跳转
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(currentItem);
        }
    };

    private void initdata() {

    }
    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://news.baidu.com";
                try {
                    ArrayList<News> list=new ArrayList<News>();
                    list= new ArrayList<News>();
                    Document document = Jsoup.connect(url).get();
                    Elements elements2 = document.select("div#pane-news.mod-tab-pane.active");
                    Elements elements = elements2.select("a");

                    for (int i = 0; i < elements.size(); i++) {
                        String title = elements.get(i).text();
                        String url_href = elements2.select("a").get(i).attr("href");
                        //Log.i("网址",url_href);
                        News news = new News();
                        news.setTitle(title);
                        news.setUrl_news(url_href);
                        list.add(news);
                    }
                    final String title1 = elements.text();
                    final ArrayList<News> finalList1 = list;
                    lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(activity, NewsActivity.class);
                            intent.putExtra("url", finalList1.get(position).getUrl_news()+"");
                            startActivity(intent);

                        }
                    });
                    final ArrayList<News> finalList = list;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv_news.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView tv_title;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
                                    }

                                }

                                @Override
                                public int getCount() {
                                    return finalList.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return finalList.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    convertView = LayoutInflater.from(activity).inflate(R.layout.item_news, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tv_title.setText(finalList.get(position).getTitle()+"");
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


    private void initView(View view) {
        vp = (ViewPager) view.findViewById(R.id.vp);
        tv_test_title = (TextView) view.findViewById(R.id.tv_test_title);

        dotLinear = (LinearLayout) view.findViewById(R.id.dotLinear);
        centerLinear = (LinearLayout) view.findViewById(R.id.centerLinear);
        frameLayout_slide = (FrameLayout) view.findViewById(R.id.frameLayout_slide);
        lv_news = (ListView) view.findViewById(R.id.lv_news);
        dot_0 = (View) view.findViewById(R.id.dot_0);
        dot_1 = (View) view.findViewById(R.id.dot_1);
        dot_2 = (View) view.findViewById(R.id.dot_2);
        dot_3 = (View) view.findViewById(R.id.dot_3);
        dot_4 = (View) view.findViewById(R.id.dot_4);
    }
}
