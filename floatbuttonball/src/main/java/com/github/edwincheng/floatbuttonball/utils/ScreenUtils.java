package com.github.edwincheng.floatbuttonball.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @ file name:    :
 * @ author        : edwincheng
 * @ e-mail        : zwp_edwincheng@163.com
 * @ date          : 19-7-29 17:07
 * @ description   :
 * @ modify author :
 * @ modify date   :
 */
public class ScreenUtils {

    /**
     * 获取手机屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取手机屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    //获取状态栏高度
    public static int getStatusHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object object = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = (Integer) field.get(object);
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }
}
