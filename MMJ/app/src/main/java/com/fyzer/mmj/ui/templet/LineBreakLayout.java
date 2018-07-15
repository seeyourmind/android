package com.fyzer.mmj.ui.templet;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by XANXUS on 2017/10/26.
 */

public class LineBreakLayout extends ViewGroup {
    private final static String TAG = "LineBreakLayout";
    private int LEFT_RIGHT_SPACE = 5; //标签间距

    public LineBreakLayout(Context context) {
        super(context);
    }

    public LineBreakLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public LineBreakLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 重写此方法实现自动换行
        measureChildren(widthMeasureSpec, heightMeasureSpec);// 为所有标签childView计算宽高

        // 获取高的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 获取建议高度
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 获取建议宽度（match_parent或者size）如果为wrap_content也是取match_parent的效果
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height;
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            //如果高度模式为EXACTLY（match_perent或者size），则使用建议高度
            height = heightSize;
        } else {
            //其他情况下（AT_MOST、EXACTLY）需要计算计算高度
            int childCount = getChildCount();
            if (childCount <= 0) {
                height = 0;   //没有标签时，高度为0
            } else {
                int row = 1;  // 标签行数
                int widthSpace = width;// 当前行右侧剩余的宽度
                for (int i = 0; i < childCount; i++) {
                    View view = getChildAt(i);
                    //获取标签宽度
                    int childW = view.getMeasuredWidth();
                    Log.v(TAG, "标签宽度:" + childW + " 行数：" + row + "  剩余宽度：" + widthSpace);
                    if (widthSpace >= childW) {
                        //如果剩余的宽度大于此标签的宽度，那就将此标签放到本行
                        widthSpace -= childW;
                    } else {
                        row++;    //增加一行
                        //如果剩余的宽度不能摆放此标签，那就将此标签放入下一行
                        widthSpace = width - childW;
                    }
                    //减去标签左右间距
                    widthSpace -= LEFT_RIGHT_SPACE;
                }
                //由于每个标签的高度是相同的，所以直接获取第一个标签的高度即可
                int childH = getChildAt(0).getMeasuredHeight();
                //最终布局的高度=标签高度*行数+行距*(行数-1)
                height = (childH + LEFT_RIGHT_SPACE) * row;

                Log.i(TAG, "总高度:" + height + " 行数：" + row + "  标签高度：" + childH);
            }
        }
        //设置测量宽度和测量高度
         setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int row = 0;
        int right = 0;   // 标签相对于布局的右侧位置
        int bottom;       // 标签相对于布局的底部位置
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int childW = childView.getMeasuredWidth();
            int childH = childView.getMeasuredHeight();
            //右侧位置=本行已经占有的位置+当前标签的宽度
            right += (childW + LEFT_RIGHT_SPACE);
            //底部位置=已经摆放的行数*（标签高度+行距）+当前标签高度
            bottom = row * (childH+LEFT_RIGHT_SPACE) + childH;
            // 如果右侧位置已经超出布局右边缘，跳到下一行
            if (right > (r-childW)){
                row++;
                right = childW + LEFT_RIGHT_SPACE;
                bottom = row *(childH + LEFT_RIGHT_SPACE) + childH;
            }
            Log.d(TAG, "LEFT_RIGHT_SPACE = "+ LEFT_RIGHT_SPACE + "left = " + (right - childW) +" top = " + (bottom - childH)+
                    " right = " + right + " botom = " + bottom + " row "+row + "r"+r);
            childView.layout((right - childW), bottom - childH, right, bottom);

            right += LEFT_RIGHT_SPACE;
        }
    }
}
