package com.example.amsdemo.core;

import android.os.Build;



public class OSVersion {

    /**
     * 低版本
     *
     * @return
     */
    public static boolean isAndroidOS_21_22_23_24_25() {
        return Build.VERSION.SDK_INT < 26;
    }

    public static boolean isAndroidOS_26_27_28() {
        int sdkInt = Build.VERSION.SDK_INT;
        return (sdkInt > 26 || sdkInt == 26) && (sdkInt < 28 || sdkInt == 28);
    }

    public static boolean isAndroidOS29() {
        return Build.VERSION.SDK_INT == 29;
    }



}
