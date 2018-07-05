package com.example.zjl.pickerviewdemo.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.zjl.pickerviewdemo.R;
import com.example.zjl.pickerviewdemo.bean.CardBean;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class WheelActivity extends BaseActivity {
    @BindView(R.id.wheelView)
    Button wheelView;
    @BindView(R.id.pickerView)
    Button pickerView;

    private OptionsPickerView pvCustomOptions;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private int num = 0;

    @Override
    public int getLayoutId() {
        return R.layout.act_wheel;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initListener();
        getCardData();
        initCustomOptionPicker();
    }

    private void initListener() {
        wheelView.setOnClickListener(v -> {
            if (pvCustomOptions != null) {
                pvCustomOptions.show();
            }
        });

        pickerView.setOnClickListener(v -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.myanim);

            pickerView.startAnimation(anim);
        });
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                wheelView.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                        tvAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCardData();
                                pvCustomOptions.setPicker(cardItem);
                            }
                        });

                    }
                })
                .isDialog(true)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据

    }

    private void getCardData() {
        for (int i = 0; i < 5; i++) {
            num = num + 1;
            cardItem.add(new CardBean(i, "Num." + num));
        }

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 6) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }
    }
}
