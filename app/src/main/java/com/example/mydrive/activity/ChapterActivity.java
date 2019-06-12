package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydrive.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ChapterActivity extends AppCompatActivity implements View.OnClickListener {
    private String url_chapter;
    private String text;
    private TextView tv_chapter_title;
    private TextView tv_chapter_zishu;
    private TextView tv_chapter_content;
    private String aNull;
    private Button btn_textsize_xiao;
    private Button btn_textsize_zhong;
    private Button btn_textsize_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        initView();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");


        url_chapter = "http://www.hengyan.com" + url;
        tv_chapter_title.setText(title);
        init();

    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document document = null;
                try {
                    document = Jsoup.connect(url_chapter).get();
                    Elements elements = document.select("div.contentitem").select("div.content").select("p");
                    Elements p = document.select("div.ch").select("p");
                    final String text = p.text();
                    for (int i = 0; i < elements.size(); i++) {
                        ChapterActivity.this.text += elements.get(i).text() + "\n";
                    }

                    if (ChapterActivity.this.text.contains("null")) {
                        aNull = ChapterActivity.this.text.replace("null", "");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv_chapter_content.setText(aNull);
                            tv_chapter_zishu.setText(text);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initView() {
        tv_chapter_title = (TextView) findViewById(R.id.tv_chapter_title);
        tv_chapter_zishu = (TextView) findViewById(R.id.tv_chapter_zishu);
        tv_chapter_content = (TextView) findViewById(R.id.tv_chapter_content);
        btn_textsize_xiao = (Button) findViewById(R.id.btn_textsize_xiao);
        btn_textsize_xiao.setOnClickListener(this);
        btn_textsize_zhong = (Button) findViewById(R.id.btn_textsize_zhong);
        btn_textsize_zhong.setOnClickListener(this);
        btn_textsize_big = (Button) findViewById(R.id.btn_textsize_big);
        btn_textsize_big.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_textsize_xiao:
                tv_chapter_content.setTextSize(12);
                break;
            case R.id.btn_textsize_zhong:
                tv_chapter_content.setTextSize(20);
                break;
            case R.id.btn_textsize_big:
                tv_chapter_content.setTextSize(28);
                break;
        }
    }
}
