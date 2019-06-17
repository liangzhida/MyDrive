package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydrive.R;
import com.example.mydrive.bean.Message;
import com.example.mydrive.fragmemt.MeFragment;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtnum;
    private EditText edtyanzhengma;
    private TextView tvhuoqu;
    private Button btn_message_denglu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
        init();
    }

    private void init() {
        tvhuoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String string = edtnum.getText().toString().trim();
                        if (string.length()>0){
                            String url="http://v.juhe.cn/sms/send?mobile="+string+"&tpl_id=165499&tpl_value=%23code%23%3D654654&key=d3266968ba8674b34194dc9580c2a79f";
                            OkHttpClient okHttpClient = new OkHttpClient();
                            Request request = new Request.Builder().url(url).build();
                            try {
                                Response response = okHttpClient.newCall(request).execute();
                                String string1 = response.body().string();
                                Message message = new Gson().fromJson(string1, Message.class);
                                int error_code = message.getError_code();
                                if (error_code!=0){
                                    Toast.makeText(MessageActivity.this, "错误的手机号码,请重新输入!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MessageActivity.this, message.getReason()+"!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(MessageActivity.this, "请输入正确的手机号码!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).start();
            }
        });
    }

    private void initView() {
        edtnum = (EditText) findViewById(R.id.edtnum);
        edtyanzhengma = (EditText) findViewById(R.id.edtyanzhengma);
        tvhuoqu = (TextView) findViewById(R.id.tvhuoqu);
        btn_message_denglu = (Button) findViewById(R.id.btn_message_denglu);

        btn_message_denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message_denglu:
                if (edtnum.getText().toString().length() > 0) {
                    /*if (edtyanzhengma.getText().toString().length() > 0) {
                        if (edtyanzhengma.getText().toString().equals("654654")) {
                            Intent intent = new Intent(MessageActivity.this, MeFragment.class);
                            intent.putExtra("name", edtnum.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(MessageActivity.this, "验证码错误!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MessageActivity.this, "验证码不能为空!", Toast.LENGTH_SHORT).show();
                    }*/
                    if (edtyanzhengma.getText().toString().length()>0){
                        if (edtyanzhengma.getText().toString().equals("654654")) {
                            Intent intent = new Intent(MessageActivity.this, MeFragment.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MessageActivity.this, "验证码错误!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MessageActivity.this, "请输入验证码!", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(MessageActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                }
        }


    }




}
