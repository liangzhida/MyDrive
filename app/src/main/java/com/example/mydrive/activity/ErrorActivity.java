package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mydrive.R;
import com.example.mydrive.bean.Subject;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class ErrorActivity extends AppCompatActivity {

    private TextView tv_title;
    private ImageView img_timu;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private RadioButton rb_4;
    private RadioGroup radioGroup;
    private TextView tvanswer;
    private TextView tvjiexi;
    private LinearLayout ll2;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        initView();
        Intent intent1=getIntent();
        position = intent1.getStringExtra("position");
        Toast.makeText(ErrorActivity.this, ""+position, Toast.LENGTH_SHORT).show();
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://v.juhe.cn/jztk/query?subject=1&model=c1&key=f5fa410f03e84fc4f3d491fb3a324aaf&testType=rand").get().build();
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    String string = response.body().string();
                    final Subject subject = new Gson().fromJson(string, Subject.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int i= Integer.valueOf(position) +1;
                            tv_title.setText(i+"."+subject.getResult().get(Integer.valueOf(position)).getQuestion()+"");
                            rb_1.setText("A."+subject.getResult().get(Integer.valueOf(position)).getItem1());
                            rb_2.setText("B."+subject.getResult().get(Integer.valueOf(position)).getItem2());
                            rb_3.setText("C."+subject.getResult().get(Integer.valueOf(position)).getItem3());
                            rb_4.setText("D."+subject.getResult().get(Integer.valueOf(position)).getItem4());
                            int answer = Integer.valueOf(subject.getResult().get(Integer.valueOf(position)).getAnswer());

                            switch (answer){
                                case 1:
                                    tvanswer.setText("答案:A");
                                    break;
                                case 2:
                                    tvanswer.setText("答案:B");
                                    break;
                                case 3:
                                    tvanswer.setText("答案:C");
                                    break;
                                case 4:
                                    tvanswer.setText("答案:D");
                                    break;
                            }

                            tvjiexi.setText(subject.getResult().get(Integer.valueOf(position)).getExplains());
                            if (subject.getResult().get(Integer.valueOf(position)).getItem3().equals("")){
                                rb_3.setVisibility(View.GONE);
                               rb_4.setVisibility(View.GONE);
                            }else {

                            }
                            if (subject.getResult().get(Integer.valueOf(position)).getUrl().equals("")){

                            }else {
                                Glide.with(ErrorActivity.this).load(subject.getResult().get(Integer.valueOf(position)).getUrl()).into(img_timu);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
       

    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_timu = (ImageView) findViewById(R.id.img_timu);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_4 = (RadioButton) findViewById(R.id.rb_4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        tvanswer = (TextView) findViewById(R.id.tvanswer);
        tvjiexi = (TextView) findViewById(R.id.tvjiexi);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
    }
}
