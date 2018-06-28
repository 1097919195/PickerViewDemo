package com.example.zjl.pickerviewdemo.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.example.zjl.pickerviewdemo.R;
import com.example.zjl.pickerviewdemo.bean.DemoBean;
import com.example.zjl.pickerviewdemo.widget.SlideDelete;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_FLING;
import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rcv)
    RecyclerView recyclerView;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.wheel)
    Button wheelBtn;
    CommonRecycleViewAdapter<DemoBean> adapter;
    DemoBean bean1 = new DemoBean();
    DemoBean bean2 = new DemoBean();
    DemoBean bean3 = new DemoBean();
    List<DemoBean> beanList = new ArrayList<>();
    List<SlideDelete> slideDeleteArrayList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        toolbar.inflateMenu(R.menu.menu_icon_text);//设置添加菜单选项

        initAdapter();
        initListener();
    }

    private void initListener() {
        btn.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("com.example.smyh006intent01.MY_ACTION");
//            intent.addCategory("com.MY_CATEGORY");
            startActivity(intent);
        });

        wheelBtn.setOnClickListener(v -> {
            ToastUtil.showShort("点击");
        });
    }

    private void initAdapter() {
        bean1.setTitel("德玛西亚");
        bean2.setTitel("嘉文四世");
        bean3.setTitel("暗夜猎手");
        beanList.add(bean1);
        beanList.add(bean2);
        beanList.add(bean3);
        adapter = new CommonRecycleViewAdapter<DemoBean>(mContext,R.layout.item_slide,beanList) {
            @Override
            public void convert(ViewHolderHelper helper, DemoBean demoBean) {
                TextView title = helper.getView(R.id.mTvContent);
                TextView delete = helper.getView(R.id.mTvDelete);
                SlideDelete slideDelete = helper.getView(R.id.slideDelete);

                title.setText(demoBean.getTitel());
                delete.setOnClickListener(v->{
                    ToastUtil.showShort(String.valueOf(helper.getLayoutPosition()));
                    beanList.remove(helper.getLayoutPosition());
                    adapter.notifyDataSetChanged();
                });

                slideDelete.setOnSlideDeleteListener(new SlideDelete.OnSlideDeleteListener(){
                    @Override
                    public void onOpen(SlideDelete slideDelete) {
                        closeOtherItem();
                        slideDeleteArrayList.add(slideDelete);
                        slideDelete.isShowDelete(true);
                        Log.d("Slide", "slideDeleteArrayList当前数量：" + slideDeleteArrayList.size());
                    }
                    @Override
                    public void onClose(SlideDelete slideDelete) {
                        slideDeleteArrayList.remove(slideDelete);
                        Log.d("Slide", "slideDeleteArrayList当前数量：" + slideDeleteArrayList.size());
                    }

                });
            }
        };
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
//        //展开时竖直方向滑动也隐藏删除键
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if(newState == SCROLL_STATE_TOUCH_SCROLL){
//                    closeOtherItem();
//                }
//            }
//        });
    }

    private void closeOtherItem(){
        // 采用Iterator的原因是for是线程不安全的，迭代器是线程安全的
        ListIterator<SlideDelete> slideDeleteListIterator = slideDeleteArrayList.listIterator();
        while(slideDeleteListIterator.hasNext()){
            SlideDelete slideDelete = slideDeleteListIterator.next();
            slideDelete.isShowDelete(false);
        }
        slideDeleteArrayList.clear();
    }

    //重写onCreateOptionMenu(Menu menu)方法，当菜单第一次被加载时调用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    //重写OptionsItemSelected(MenuItem item)来响应菜单项(MenuItem)的点击事件（根据id来区分是哪个item）
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.gameStart:
                Toast.makeText(this, "开始游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gameOver:
                Toast.makeText(this, "结束游戏", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 让菜单同时显示图标和文字(只能用菜单按钮的时候显示)
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
