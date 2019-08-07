package com.github.edwincheng.floatbuttonball.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.github.edwincheng.floatbuttonball.utils.ScreenUtils;
import com.github.edwincheng.floatbuttonball.utils.ShellCommander;
import com.github.edwincheng.floatbuttonball.widget.FloatBall;

/**
 * Created by ZY on 2016/8/10.
 * 管理者，单例模式
 */
public class ViewManager {

    private FloatBall floatBall;

    private WindowManager windowManager;

    private static ViewManager manager;

    private LayoutParams floatBallParams;

    private ShellCommander shellCommander;

    private Context context;

    //私有化构造函数
    private ViewManager(Context context) {
        this.context = context;
        init();
    }

    //获取ViewManager实例
    public static ViewManager getInstance(Context context) {
        if (manager == null) {
            synchronized (ViewManager.class) {
                if (manager == null) {
                    manager = new ViewManager(context);
                }
            }
        }
        return manager;
    }

    private void init() {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        floatBall = new FloatBall(context);

        floatBall.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;
            float tempX;
            float tempY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getRawX();
                        startY = event.getRawY();
                        tempX = event.getRawX();
                        tempY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x = event.getRawX() - startX;
                        float y = event.getRawY() - startY;
                        //计算偏移量，刷新视图
                        floatBallParams.x += x;
                        floatBallParams.y += y;
                        windowManager.updateViewLayout(floatBall, floatBallParams);
                        startX = event.getRawX();
                        startY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        //判断松手时View的横坐标是靠近屏幕哪一侧，将View移动到依靠屏幕
                        float endX = event.getRawX();
                        float endY = event.getRawY();
                        if (endX < ScreenUtils.getScreenWidth(context) / 2) {
                            endX = 0;
                        } else {
                            endX = ScreenUtils.getScreenWidth(context) - floatBall.width;
                        }
                        floatBallParams.x = (int) endX;
                        windowManager.updateViewLayout(floatBall, floatBallParams);
                        //如果初始落点与松手落点的坐标差值超过6个像素，则拦截该点击事件
                        //否则继续传递，将事件交给OnClickListener函数处理
                        if (Math.abs(endX - tempX) > 6 && Math.abs(endY - tempY) > 6) {
                            return true;
                        }
                        break;
                }
                return false;
            }
        });

        floatBall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shellCommander == null){
                    shellCommander = new ShellCommander();
                }
                shellCommander.simulateKey(4);
            }
        });
    }

    //显示浮动小球
    public void addFloatBall() {
        if (floatBallParams == null) {
            floatBallParams = new LayoutParams();
            floatBallParams.width = floatBall.width;
            floatBallParams.height = floatBall.height - ScreenUtils.getStatusHeight(context);
            floatBallParams.gravity = Gravity.TOP | Gravity.START;
            floatBallParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCH_MODAL;
            floatBallParams.format = PixelFormat.RGBA_8888;
            floatBallParams.y = ScreenUtils.getScreenHeight(context) / 3;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                floatBallParams.type = LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                floatBallParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
            }
        }
        windowManager.addView(floatBall, floatBallParams);
    }

    public void showFloatBall() {
        if (windowManager != null && floatBallParams != null
                && floatBall != null && floatBall.getVisibility() == View.GONE){
            floatBall.setVisibility(View.VISIBLE);
        }
    }

    public void hideFloatBall() {
        if (windowManager != null && floatBallParams != null
                && floatBall != null && floatBall.getVisibility() == View.VISIBLE){
            floatBall.setVisibility(View.GONE);
        }
    }

}