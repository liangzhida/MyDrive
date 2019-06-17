package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydrive.LoadingDialog;
import com.example.mydrive.R;
import com.example.mydrive.bean.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    private ListView lv_item;
    private String href;
    private String url="";
    private ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        href = intent.getStringExtra("href");
        url="http://www.hengyan.com"+href;
        initView();
        init();
        inityuedu();
    }

    private void inityuedu() {
        lv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ItemActivity.this, ChapterActivity.class);
                intent.putExtra("title",list.get(position).getItem()+"");
                intent.putExtra("url",list.get(position).getUrl()+"");
                intent.putExtra("position",position+"");
                startActivity(intent);
            }
        });
    }

    private void init() {
        LoadingDialog.showDialog(ItemActivity.this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    list= new ArrayList<Item>();
                    Document document = Jsoup.connect(url).get();
                    Elements select = document.select("div.chapter");
                    Elements elements = select.select("ul").select("li");

                    for (int i = 0; i < elements.size(); i++) {
                        String datatime = elements.get(i).select("span").text();
                        String  chapter= elements.get(i).select("a").text();
                        Elements a = elements.get(i).select("a");
                        String url_chapter = a.attr("href");
//                        Log.i("网址::::      ",url_chapter);
                        Item item1 = new Item();
                        item1.setItem(chapter);
                        item1.setDatatime(datatime);
                        item1.setUrl(url_chapter);
                        list.add(item1);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lv_item.setAdapter(new BaseAdapter() {

                                class ViewHolder {
                                    public View rootView;
                                    public TextView tv_chapter;
                                    public TextView tv_datatime;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tv_chapter = (TextView) rootView.findViewById(R.id.tv_chapter);
                                        this.tv_datatime = (TextView) rootView.findViewById(R.id.tv_datatime);
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
                                    convertView = LayoutInflater.from(ItemActivity.this).inflate(R.layout.item_chapter, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tv_chapter.setText(list.get(position).getItem());
                                    viewHolder.tv_datatime.setText(list.get(position).getDatatime());
                                    return convertView;
                                }
                            });

                        }
                    });

                    LoadingDialog.disDialog();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void initView() {
        lv_item = (ListView) findViewById(R.id.lv_item);
    }
}
