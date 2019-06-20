package com.example.mydrive.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydrive.MainActivity;
import com.example.mydrive.R;
import com.example.mydrive.bean.DaoMaster;
import com.example.mydrive.bean.DaoSession;
import com.example.mydrive.bean.User;
import com.example.mydrive.bean.UserDao;

import java.util.Collections;
import java.util.List;

public class ZhuceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtnume;
    private EditText edtpassword;
    private EditText edtpassword2;
    private CheckBox checkbox;
    private Button btn_zhuce;
    private DaoMaster master;
    private DaoSession daoSession;
    private SQLiteDatabase db;
    private UserDao userDao;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
        db = new DaoMaster.DevOpenHelper(ZhuceActivity.this, "test.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        daoSession = master.newSession();
        userDao = daoSession.getUserDao();
        init();

    }

    private void init() {

    }

    private void initView() {
        edtnume = (EditText) findViewById(R.id.edtnume);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        edtpassword2 = (EditText) findViewById(R.id.edtpassword2);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        btn_zhuce = (Button) findViewById(R.id.btn_zhuce);

        btn_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_zhuce:
                submit();
                user=new User();
                user.setName(edtnume.getText().toString());
               /* User load = daoSession.load(User.class, user.getName());
                System.out.println(load.toString());*/
                List<User> users1 =  userDao.loadAll();
                int frequency = Collections.frequency(users1, user);
                // Toast.makeText(this, frequency+"", Toast.LENGTH_SHORT).show();
                if(frequency>=1){
                   Toast.makeText(this, "账户已存在!", Toast.LENGTH_SHORT).show();
                }else{
                    userDao.insert(user);
                    Toast.makeText(this, "添加成功!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void submit() {
        // validate
        String edtnumeString = edtnume.getText().toString().trim();
        if (TextUtils.isEmpty(edtnumeString)) {
            Toast.makeText(this, "用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String edtpasswordString = edtpassword.getText().toString().trim();
        if (TextUtils.isEmpty(edtpasswordString)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String edtpassword2String = edtpassword2.getText().toString().trim();
        if (TextUtils.isEmpty(edtpassword2String)) {
            Toast.makeText(this, "再次密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
