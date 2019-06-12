package com.example.mydrive.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydrive.MainActivity;
import com.example.mydrive.R;
import com.example.mydrive.bean.Infos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class XiaoshuoActivity extends AppCompatActivity {
    private ListView lv_dianji;
    private ArrayList<Infos> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiaoshuo);
        initView();
        initList();
        initdianji();
    }
    private void initdianji() {
        lv_dianji.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "你点击了"+position+"项", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(XiaoshuoActivity.this, ContentActivity.class);
                intent.putExtra("url", list.get(position).getUrl() + "");
                intent.putExtra("name", list.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    private void initList() {
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
                    runOnUiThread(new Runnable() {
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
                                    convertView = LayoutInflater.from(XiaoshuoActivity.this).inflate(R.layout.item_dianji, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    viewHolder.tvtitle.setText(list.get(position).getTitle() + "");
                                    if (position<=2){
                                        viewHolder.tvnum.setText(position+1+"");
                                        viewHolder.tvnum.setTextColor(Color.WHITE);
                                        viewHolder.tvnum.setBackgroundColor(Color.rgb(255, 193, 37));
                                    }else {
                                        viewHolder.tvnum.setText(position+1+"");
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

    private void initView() {
        lv_dianji = (ListView) findViewById(R.id.lv_dianji);
    }
}
