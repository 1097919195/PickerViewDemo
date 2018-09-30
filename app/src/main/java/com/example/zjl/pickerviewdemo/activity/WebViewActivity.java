package com.example.zjl.pickerviewdemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zjl.pickerviewdemo.R;
import com.jaydenxiao.common.base.BaseActivity;

/**
 * Created by Administrator on 2018/9/29 0029.
 */

public class WebViewActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.act_webview;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new MyWebChromeClient());//重定向
        mWebView.loadUrl("http://192.168.199.114:80");
    }

    public class MyWebChromeClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("mls://")) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(url));
                startActivity(intent);
                Log.v("tag_2", url);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

}
