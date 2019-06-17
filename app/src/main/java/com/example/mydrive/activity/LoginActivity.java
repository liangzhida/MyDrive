package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydrive.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_denglu;
    private TextView tv_message_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
    }

    private void init() {
        tv_message_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btn_denglu = (Button) findViewById(R.id.btn_denglu);
        tv_message_Login = (TextView) findViewById(R.id.tv_message_Login);

        btn_denglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_denglu:

                break;
        }
    }
}
