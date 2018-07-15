package com.fyzer.mmj.ui.templet;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fyzer.mmj.R;

/**
 * Created by XANXUS on 2017/10/24.
 */

public class CategoryLabelView extends View {
    // 定义属性标签
    private int borderColor;
    private int borderWeight;
    private int borderRadius;
    private int backgroundColor;
    private int textSize;
    private String textString;
    private int textColor;

    // 控件绘制
    private Paint paint = new Paint();
    private TextPaint textPaint = new TextPaint();
    private RectF rect = new RectF();
    private static final int MIN_SIZE = 170;

    // 构造函数
    public CategoryLabelView(Context context) {
        super(context);
    }

    public CategoryLabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryLabelAttrs);// 获取配置属性

        borderColor = typedArray.getInt(R.styleable.CategoryLabelAttrs_borderColor, 0x00000000);
        borderRadius = typedArray.getDimensionPixelSize(R.styleable.CategoryLabelAttrs_borderRadius, 0);
        borderWeight = typedArray.getDimensionPixelSize(R.styleable.CategoryLabelAttrs_borderWeight, 1);

        backgroundColor = typedArray.getInt(R.styleable.CategoryLabelAttrs_backgroundColor, 0xffb6c1);

        textSize = typedArray.getDimensionPixelSize(R.styleable.CategoryLabelAttrs_textSize, 0);
        textColor = typedArray.getInt(R.styleable.CategoryLabelAttrs_textColor, 0xd3d3d3);
        textString = typedArray.getString(R.styleable.CategoryLabelAttrs_textString);

        // 资源回收 必需调用
        typedArray.recycle();
    }

    public CategoryLabelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        rect.left = getPaddingLeft();
        rect.right = getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = getPaddingBottom();

        paint.setAlpha(255);// 设置透明度 255为不透明 范围0-255
        if (textString != null){
            // 测量标签的宽高
            Paint.FontMetrics fm = paint.getFontMetrics();
            int textHeight = (int) Math.ceil(fm.descent - fm.ascent);
            int textWidth = (int) paint.measureText(textString);

            // 画圆角背景
            paint.setColor(backgroundColor);
            rect.set(0, 0, textWidth+(2*getPaddingLeft()), fm.bottom+Math.abs(fm.top)+(2*getPaddingLeft()));
            canvas.drawRoundRect(rect, borderRadius, borderRadius, paint);

            // 标签文字
            paint.reset();
            paint.setTextSize(textSize);
            paint.setColor(textColor);
            paint.setTextAlign(Paint.Align.LEFT);// 设置文本对齐方式 对齐的基准是baseLine的起点
            // String msg = TextUtils.ellipsize(textString, textPaint, textWidth, TextUtils.TruncateAt.END).toString();// 字符串截取
            String msg = textString;
            canvas.drawText(msg, getPaddingLeft(), textHeight-fm.descent+getPaddingLeft(), paint);

        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // xml中设置的宽高模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        // 定义实际设置的宽高
        int width;
        int height;

        // 宽模式判别
        if(widthMode == MeasureSpec.EXACTLY){
            // EXACTLY使用确定的值或是设置了match_parent
            width = widthSize;
        } else {
            // UNSPECIFIED子布局可以任意大，常出现在AdapterView中item的heightMode中
            int desired = getPaddingLeft() + getPaddingRight();
            // desired += (textSize*textString.length() > 0)? (textSize*textString.length()):0; // 使用paint.measureText()方法计算
            if (textString.length()>0){
                int textWidth = (int)paint.measureText(textString);
                desired += textWidth;
            }
            Log.i("------>desired = ", ""+desired);
            width = Math.max(MIN_SIZE, desired);

            if(widthMode == MeasureSpec.AT_MOST) {
                // AT_MOST限制在最大值内，设置了wrap_content
                width = Math.min(MIN_SIZE, desired);
            }
        }

        // 高模式判别
        if(heightMode == MeasureSpec.EXACTLY){
            // EXACTLY使用确定的值或是设置了match_parent
            height = heightSize;
        } else {
            // UNSPECIFIED子布局可以任意大，常出现在AdapterView中item的heightMode中
            int desired = getPaddingTop() + getPaddingBottom();
            if (textString.length()>0){
                paint.setTextSize(textSize);
                Paint.FontMetrics fm = paint.getFontMetrics();// 测量字体
                int textHeight = (int)Math.ceil(fm.descent - fm.ascent);// 向上取整
                desired += textHeight;
            }
            height = Math.max(MIN_SIZE, desired);

            if(heightMode == MeasureSpec.AT_MOST) {
                // AT_MOST限制在最大值内，设置了wrap_content
                height = Math.min(MIN_SIZE, desired);
            }
        }

        setMeasuredDimension(width, height);
    }
}
