package com.example.mydrive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydrive.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_denglu;
    private TextView tv_message_Login;
    private TextView tv_zhuce;
    private TextView tv_forget;
    private CheckBox checkbox;
    private EditText edt_name;
    private EditText edt_password;

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
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                   edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void initView() {
        btn_denglu = (Button) findViewById(R.id.btn_denglu);
        tv_message_Login = (TextView) findViewById(R.id.tv_message_Login);

        btn_denglu.setOnClickListener(this);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_zhuce.setOnClickListener(this);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_name.setOnClickListener(this);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_denglu:

                break;
        }
    }

    private void submit() {
        // validate
        String name = edt_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "用户名.邮箱.或者手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = edt_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
