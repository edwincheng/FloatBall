package com.github.edwincheng.floatbuttonball.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.github.edwincheng.floatbuttonball.R;


/**
 * Created by ZY on 2016/8/10.
 * 悬浮球
 */
public class FloatBall extends View {

    public int width = 128;

    public int height = 128;
    //默认显示的文本
    private String text = "返回";
    /** 默认显示的图标 */
    private Bitmap bitmap;

    private Paint textPaint;

    private Paint iconPaint;

    public FloatBall(Context context) {
        this(context, null, 0);
    }

    public FloatBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        iconPaint = new Paint();
        /** 范围0～255 */
        iconPaint.setAlpha(180);
        iconPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(26);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.icon_floatball);
        //将图片裁剪到指定大小
        bitmap = Bitmap.createScaledBitmap(src, width, height, true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, iconPaint);
        float textWidth = textPaint.measureText(text);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float dy = -(fontMetrics.descent + fontMetrics.ascent) / 2;
        canvas.drawText(text, width / 2 - textWidth / 2, height / 2 + dy, textPaint);
    }
}
