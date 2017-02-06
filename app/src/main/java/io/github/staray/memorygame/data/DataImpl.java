package io.github.staray.memorygame.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by staray on 2017/1/19.
 * <p>
 * 数据接口实现
 */

public class DataImpl implements Data {
    @SuppressLint("StaticFieldLeak")
    private static DataImpl data;
    private SharedPreferences sharedPreferences;
    private Context context;
    private String INFO = "Info";

    public static DataImpl getInstance(Context context) {
        if (null == data) {
            data = new DataImpl(context);
        }
        return data;
    }

    private DataImpl(Context context) {
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        this.context = context;
    }

    @Override
    public int getBestTime() {
        return sharedPreferences.getInt("best_time" + getLevel(), 999999);
    }

    @Override
    public void setBestTime(int bestTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("best_time" + getLevel(), bestTime);
        editor.apply();
    }

    @Override
    public int getLevel() {
        return sharedPreferences.getInt("level", 2);
    }

    @Override
    public void setLevel(int level) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level", level);
        editor.apply();
    }

    @Override
    public String getInfo() {
        try {
            InputStream in = context.getResources().getAssets().open(INFO);
            int length = in.available();
            byte[] buffer = new byte[length];

            in.read(buffer);
            in.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("getInfo", e.getMessage());
            return null;
        }
    }
}
