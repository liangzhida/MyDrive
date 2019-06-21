package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mydrive.R;
import com.example.mydrive.bean.Errors;
import com.example.mydrive.bean.Subject;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {


    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initView();
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                final int[] strings = new int[]{1, 2, 4};
                final ArrayList<Errors>list=new ArrayList<>();
                Errors errors = new Errors();
                errors.setNum(2);
                list.add(errors);
                Errors errors1 = new Errors();
                errors.setNum(7);
                list.add(errors);
                Errors errors2 = new Errors();
                errors.setNum(9);
                list.add(errors2);
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
                            lv.setAdapter(new BaseAdapter() {
                                class ViewHolder {
                                    public View rootView;
                                    public TextView tv_title;
                                    public ImageView img_timu;
                                    public RadioButton rb_1;
                                    public RadioButton rb_2;
                                    public RadioButton rb_3;
                                    public RadioButton rb_4;
                                    public RadioGroup radioGroup;
                                    public TextView tvanswer;
                                    public TextView tvjiexi;
                                    public LinearLayout ll2;

                                    public ViewHolder(View rootView) {
                                        this.rootView = rootView;
                                        this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
                                        this.img_timu = (ImageView) rootView.findViewById(R.id.img_timu);
                                        this.rb_1 = (RadioButton) rootView.findViewById(R.id.rb_1);
                                        this.rb_2 = (RadioButton) rootView.findViewById(R.id.rb_2);
                                        this.rb_3 = (RadioButton) rootView.findViewById(R.id.rb_3);
                                        this.rb_4 = (RadioButton) rootView.findViewById(R.id.rb_4);
                                        this.radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
                                        this.tvanswer = (TextView) rootView.findViewById(R.id.tvanswer);
                                        this.tvjiexi = (TextView) rootView.findViewById(R.id.tvjiexi);
                                        this.ll2 = (LinearLayout) rootView.findViewById(R.id.ll2);
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
                                    convertView = LayoutInflater.from(QuestionsActivity.this).inflate(R.layout.item, parent, false);
                                    ViewHolder viewHolder = new ViewHolder(convertView);
                                    int i = position + 1;
                                    viewHolder.tv_title.setText(i + "." + subject.getResult().get(position).getQuestion());
                                    viewHolder.rb_1.setText("A." + subject.getResult().get(position).getItem1());
                                    viewHolder.rb_2.setText("B" + subject.getResult().get(position).getItem2());
                                    viewHolder.rb_3.setText("C" + subject.getResult().get(position).getItem3());
                                    viewHolder.tvjiexi.setText(subject.getResult().get(position).getExplains());
                                    viewHolder.rb_4.setText("D" + subject.getResult().get(position).getItem4());
                                    int answer = Integer.valueOf(subject.getResult().get(Integer.valueOf(position)).getAnswer());

                                    switch (answer){
                                        case 1:
                                            viewHolder.tvanswer.setText("答案:A");
                                            break;
                                        case 2:
                                            viewHolder.tvanswer.setText("答案:B");
                                            break;
                                        case 3:
                                            viewHolder.tvanswer.setText("答案:C");
                                            break;
                                        case 4:
                                            viewHolder.tvanswer.setText("答案:D");
                                            break;
                                    }
                                    if (subject.getResult().get(position).getItem3().equals("")) {
                                        viewHolder.rb_3.setVisibility(View.GONE);
                                        viewHolder.rb_4.setVisibility(View.GONE);
                                    } else {

                                    }
                                    if (subject.getResult().get(position).getUrl().equals("")) {

                                    } else {
                                        Glide.with(QuestionsActivity.this).load(subject.getResult().get(position).getUrl()).into(viewHolder.img_timu);
                                    }
                                    return convertView;
                                }
                            });
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void initView() {

        lv = (ListView) findViewById(R.id.lv);
    }
}
