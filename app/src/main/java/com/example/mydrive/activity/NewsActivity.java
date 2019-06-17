package com.example.mydrive.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mydrive.LoadingDialog;
import com.example.mydrive.R;
import com.example.mydrive.bean.News;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_news);
        initView();

       init();



    }

    private void init() {
        LoadingDialog.showDialog(NewsActivity.this);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            //覆盖shouldOverrideUrlLoading 方法,防止利用系统自带的浏览器打开页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                showProgress("页面加载中");//开始加载动画
            }

            @Override
            public void onPageFinished(WebView view, String url1) {
                super.onPageFinished(view, url1);
                view.loadUrl("javascript:function setTop(){document.querySelector('.img-full').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.m-footer').style.display=\"none\";}setTop();");
                view.loadUrl("javascript:function setTop(){document.querySelector('.txt').style.display=\"none\";}setTop();");



            }
        });
        LoadingDialog.disDialog();


    }


    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
    }
}
