package com.xiaoqi.storage1.utils;

import com.xiaoqi.storage1.AppClient;

public class SpTools {


    public static void putString(String key, String value) {
        AppClient.getSp().edit().putString(key, value).commit();
    }

    public static String getString(String key, String value) {
        return AppClient.getSp().getString(key, value);
    }

}
