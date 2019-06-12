package com.example.mydrive.activity;

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

public class XunxuActivity extends AppCompatActivity {

    private TextView tv_title;
    private ImageView img_pic;
    private RadioButton rb_a;
    private RadioButton rb_b;
    private RadioButton rb_c;
    private RadioButton rb_d;
    private RadioGroup radioGroup;
    private int i = 0;
    private LinearLayout ll1;
    private TextView tvdaan;
    private TextView tvjiexi;
    private LinearLayout ll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xunxu);
        initView();
        init();

    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://apicloud.mob.com/tiku/kemu1/query?key=520520test&page=1&size=100").build();
                    try {
                        Response response = okHttpClient.newCall(request).execute();
                        String string = response.body().string();
                        final Subject subject = new Gson().fromJson(string, Subject.class);

                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                switch (checkedId) {
                                    case R.id.rb_a:
                                        if (subject.getResult().getList().get(i).getVal().contains("1")) {
//                                        Toast.makeText(XunxuActivity.this, "答案正确!", Toast.LENGTH_SHORT).show();
                                            i++;
                                            ll2.setVisibility(View.GONE);
                                            radioGroup.clearCheck();
                                        } else {
                                            ll2.setVisibility(View.VISIBLE);
                                        }
                                        break;
                                    case R.id.rb_b:
                                        if (subject.getResult().getList().get(i).getVal().contains("2")) {
//                                        Toast.makeText(XunxuActivity.this, "答案正确!", Toast.LENGTH_SHORT).show();
                                            ll2.setVisibility(View.GONE);
                                            radioGroup.clearCheck();
                                            i++;

                                        } else {
                                            ll2.setVisibility(View.VISIBLE);
                                        }
                                        break;
                                    case R.id.rb_c:
                                        if (subject.getResult().getList().get(i).getVal().contains("3")) {
//                                        Toast.makeText(XunxuActivity.this, "答案正确!", Toast.LENGTH_SHORT).show();
                                            radioGroup.clearCheck();
                                            ll2.setVisibility(View.GONE);
                                            i++;

                                        } else {
                                            ll2.setVisibility(View.VISIBLE);
                                        }
                                        break;
                                    case R.id.rb_d:
                                        if (subject.getResult().getList().get(i).getVal().contains("4")) {
//                                        Toast.makeText(XunxuActivity.this, "答案正确!", Toast.LENGTH_SHORT).show();
                                            radioGroup.clearCheck();
                                            i++;
                                            ll2.setVisibility(View.GONE);

                                        } else {

                                            ll2.setVisibility(View.VISIBLE);
                                        }
                                        break;
                                }
                            }
                        });

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_title.setText(i + 1 + "." + subject.getResult().getList().get(i).getTitle());
                                if (subject.getResult().getList().get(i).getTikuType().contains("select")) {
                                    rb_a.setText("A."+subject.getResult().getList().get(i).getA());
                                    rb_b.setText("B."+subject.getResult().getList().get(i).getB());
                                    rb_c.setText("C."+subject.getResult().getList().get(i).getC());
                                    rb_d.setText("D."+subject.getResult().getList().get(i).getD());
                                } else {
                                    rb_a.setVisibility(View.VISIBLE);
                                    rb_b.setVisibility(View.VISIBLE);
                                    rb_c.setVisibility(View.GONE);
                                    rb_d.setVisibility(View.GONE);
                                }

                                if (subject.getResult().getList().get(i).getVal().contains("1")){
                                    tvdaan.setText("答案:A");
                                }else if (subject.getResult().getList().get(i).getVal().contains("2")){
                                    tvdaan.setText("答案:B");
                                }else if (subject.getResult().getList().get(i).getVal().contains("3")){
                                    tvdaan.setText("答案:C");
                                }else if (subject.getResult().getList().get(i).getVal().contains("4")){
                                    tvdaan.setText("答案:D");
                                }
                                tvjiexi.setText(subject.getResult().getList().get(i).getExplainText());
                                if (subject.getResult().getList().get(i).getFile().length()>0){
                                    Glide.with(XunxuActivity.this).load(subject.getResult().getList().get(i).getFile()).into(img_pic);
                                }
                            }
                        });
                        Thread.sleep(2000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_pic = (ImageView) findViewById(R.id.img_pic);
        rb_a = (RadioButton) findViewById(R.id.rb_a);
        rb_b = (RadioButton) findViewById(R.id.rb_b);
        rb_c = (RadioButton) findViewById(R.id.rb_c);
        rb_d = (RadioButton) findViewById(R.id.rb_d);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        tvdaan = (TextView) findViewById(R.id.tvdaan);
        tvjiexi = (TextView) findViewById(R.id.tvjiexi);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
    }
}
