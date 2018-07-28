package com.example.zjl.pickerviewdemo.activity;

import android.content.Context;

/**
 * Created by Administrator on 2018/7/5 0005.
 */

public class BuilderTest {
    private int Color_Submit;//确定按钮颜色
    private int Color_Cancel;//取消按钮颜色
    private int Color_Title;//标题颜色




    //构造方法
    public BuilderTest(Builder builder) {

        this.Color_Submit = builder.Color_Submit;
        this.Color_Cancel = builder.Color_Cancel;
        this.Color_Title = builder.Color_Title;

    }


    //建造器
    public static class Builder {


        private int Color_Submit;//确定按钮颜色
        private int Color_Cancel;//取消按钮颜色
        private int Color_Title;//标题颜色
        private Context context;

        //Required
        public Builder(Context contextr) {
            this.context = contextr;

        }

        public Builder setSubmitColor(int Color_Submit) {
            this.Color_Submit = Color_Submit;
            return this;
        }

        public Builder setCancelColor(int Color_Cancel) {
            this.Color_Cancel = Color_Cancel;
            return this;
        }


        public Builder setTitleColor(int Color_Title) {
            this.Color_Title = Color_Title;
            return this;
        }


        //真正创建TimePickerView 对象的方法
        public BuilderTest build() {
            return new BuilderTest(this);
        }
    }
}
