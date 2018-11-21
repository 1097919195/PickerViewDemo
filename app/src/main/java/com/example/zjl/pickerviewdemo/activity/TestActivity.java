package com.example.zjl.pickerviewdemo.activity;

import android.widget.TextView;

import com.example.zjl.pickerviewdemo.R;
import com.example.zjl.pickerviewdemo.widget.SideBar;
import com.jaydenxiao.common.base.BaseActivity;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class TestActivity extends BaseActivity {
    SideBar sideBar;
    TextView dialog;

    @Override
    public int getLayoutId() {
        return R.layout.act_test;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar = (SideBar) findViewById(R.id.sideBar);
        sideBar.setTextView(dialog);
        //设置右侧[A-Z]快速导航栏触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
//                int position = adapter.getPositionForSection(s.charAt(0));
//                if (position != -1) {
//                    mListView.setSelection(position);
//                }
            }
        });
    }
}
