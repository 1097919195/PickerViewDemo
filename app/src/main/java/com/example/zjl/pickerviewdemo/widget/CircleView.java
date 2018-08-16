package com.example.zjl.pickerviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.zjl.pickerviewdemo.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;

/**
 * Created by Administrator on 2018/8/15 0015.
 */

public class CircleView extends View {
    private Context mContext;
    private int mColor;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        // 根据自定义类型设定颜色
        TypedArray circlrColor = mContext.obtainStyledAttributes(attrs,
                R.styleable.CircleView);
        mColor = circlrColor.getColor(R.styleable.CircleView_circle_color,
                Color.GREEN);
        init();
    }

    private void init() {
        paint.setColor(mColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);//宽的测量大小，模式
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //first 布局中看不到效果，需要程序运行才行
//        // 在wrap_content的情况下默认长度为200dp
//        int minSize = DisplayUtil.dip2px(200);
//        // wrap_content的specMode是AT_MOST模式，这种情况下宽/高等同于specSize
//        // 查表得这种情况下specSize等同于parentSize，也就是父容器当前剩余的大小
//        // 在wrap_content的情况下如果特殊处理，效果等同match_parent
//        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(minSize, minSize);
//        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(minSize, heightSpecSize);
//        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
//            setMeasuredDimension(widthSpecSize, minSize);
//        }

        //second
        int w = widthSpecSize;   //定义测量宽，高(不包含测量模式),并设置默认值，查看View#getDefaultSize可知
        int h = heightSpecSize;

        //处理wrap_content的几种特殊情况
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            w = 400;  //单位是px
            h = 200;
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            //只要宽度布局参数为wrap_content， 宽度给固定值200dp(处理方式不一，按照需求来)
            w = 400;
            //按照View处理的方法，查看View#getDefaultSize可知
            h = heightSpecSize;
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            w = widthSpecSize;
            h = 200;
        }
        //给两个字段设置值，完成最终测量
        setMeasuredDimension(w, h);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取控件circleView宽高
        int width = getWidth();
        int height = getHeight();

        //圆心坐标
        int cx = width / 2;
        int cy = height / 2;

        //取两者最小值作为圆的直径
        int minSize = Math.min(width, height);

        canvas.drawColor(Color.WHITE);//设置画布颜色
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setStyle(Paint.Style.STROKE);//设置圆为空心
        paint.setStrokeWidth(3.0f);//设置线宽

        //-----开始处理padding
        //获取padding的值
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //计算MyCircleView减去padding后的可用宽高
        int canUsedWidth = width - paddingLeft - paddingRight;
        int canUsedHeight = height - paddingTop - paddingBottom;

        //圆心坐标
        cx = canUsedWidth / 2 + paddingLeft;
        cy = canUsedHeight / 2 + paddingTop;
        //圆的直径
        minSize = Math.min(canUsedWidth, canUsedHeight);
        //cx,cy坐标点是相对于MyCircleView的左上角顶点坐标
        canvas.drawCircle(cx, cy, minSize / 2, paint);

    }

}
