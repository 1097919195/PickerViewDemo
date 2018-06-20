package com.example.zjl.pickerviewdemo.adapter;

import android.content.Context;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.example.zjl.pickerviewdemo.bean.DemoBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11 0011.
 */

public class DeleteAdapter extends CommonRecycleViewAdapter<DemoBean>{
    public DeleteAdapter(Context context, int layoutId, List<DemoBean> mDatass) {
        super(context, layoutId, mDatass);
    }

    public DeleteAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, DemoBean demoBean) {

    }
}
