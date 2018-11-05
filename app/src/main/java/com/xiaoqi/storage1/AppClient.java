package com.xiaoqi.storage1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class AppClient extends Application {

    private static SharedPreferences sp;
    private static Toast toast;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = getSharedPreferences("gxt", 0);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        context = this;
    }

    public static void showToast(String text) {
        toast.setText(text);
        toast.show();
    }

    public static SharedPreferences getSp() {
        return sp;
    }

    public static Context getContext() {
        return context;
    }
}
