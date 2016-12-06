package com.fire.custom.two;

import android.app.Activity;
import android.util.DisplayMetrics;

/*
 *  项目名：  CustomView 
 *  包名：    demo.custom.fire.com.customview
 *  文件名:   DeviceInfoUtils
 *  创建者:   lsy
 *  创建时间:  2016/12/2 11:54
 *  描述：    TODO
 */
public class DeviceInfoUtils {

    private volatile static DeviceInfoUtils instance;
    public static int screenWidth;
    public static int screenHeight;

    private DeviceInfoUtils() {

    }

    public static DeviceInfoUtils getInstance() {

        if (instance == null) {
            synchronized (DeviceInfoUtils.class) {
                if (instance == null) {
                    instance = new DeviceInfoUtils();
                }
            }
        }
        return instance;
    }

    public void initScreenInfo(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        if (metrics.widthPixels > metrics.heightPixels) {
            screenWidth = metrics.heightPixels;
            screenHeight = metrics.widthPixels;
        } else {
            screenWidth = metrics.widthPixels;
            screenHeight = metrics.heightPixels;
        }


    }

}
