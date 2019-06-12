package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mydrive.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ContentActivity extends AppCompatActivity {


    private String url;
    private String name;
    private ImageView img_pic;
    private TextView tvname;
    private TextView tvcontent;
    private TextView tvleixin;
    private TextView tvbiaoqian;
    private Button btn_mulu;
    private Button btn_yuedu;
    private String href;
    private String url_pager1;
    private TextView tv_piaoshu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initView();
        init();
        inittiaozhuan();


    }

    private void inittiaozhuan() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        name = intent.getStringExtra("name");
        tvname.setText(name);
        btn_mulu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ContentActivity.this, ItemActivity.class);
                intent1.putExtra("href", href);
                startActivity(intent1);
            }
        });
        btn_yuedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ContentActivity.this, ChapterActivity.class);
                intent2.putExtra("url", url_pager1);
                startActivity(intent2);
            }
        });

    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(url).get();
                    Elements elements1 = document.select("div.huobg");
                    Elements select = elements1.select("img");
                    final String url_xiaoshuo = select.attr("src");
                    Elements select1 = document.select("p.info");
                    final String text = select1.text();
                    Elements select2 = document.select("p.intro.ih1");
                    final String text1 = select2.text();


                    Elements select3 = document.select("p.biaoqian");
                    final String label = select3.text();

                    Elements select4 = document.select("p.go.gobg");
                    Elements goml = select4.select("a.goml");
                    href = goml.attr("href");
                    // Log.i("网址", href);
                    url_pager1 = select4.select("a").attr("href");



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvleixin.setText(text);
                            tvcontent.setText(text1);
                            Glide.with(ContentActivity.this).load(url_xiaoshuo).into(img_pic);
                            tvbiaoqian.setText(label);

                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initView() {
        img_pic = (ImageView) findViewById(R.id.img_pic);
        tvname = (TextView) findViewById(R.id.tvname);
        tvcontent = (TextView) findViewById(R.id.tvcontent);

        tvleixin = (TextView) findViewById(R.id.tvleixin);
        tvbiaoqian = (TextView) findViewById(R.id.tvbiaoqian);

        btn_mulu = (Button) findViewById(R.id.btn_mulu);
        btn_yuedu = (Button) findViewById(R.id.btn_yuedu);
        tv_piaoshu = (TextView) findViewById(R.id.tv_piaoshu);
    }


}
