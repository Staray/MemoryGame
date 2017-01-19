package io.github.staray.memorygame.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by staray on 2017/1/19.
 */

public class DataImpl implements Data {
    private Context context;
    private static DataImpl data;
    private SharedPreferences sharedPreferences;

    public static DataImpl getInstance(Context context) {
        if (null == data) {
            data = new DataImpl(context);
        }
        return data;
    }

    private DataImpl(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    @Override
    public int getBestTime() {
        return sharedPreferences.getInt("best_time", 999999);
    }

    @Override
    public void setBestTime(int bestTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("best_time", bestTime);
        editor.apply();
    }

    @Override
    public int getRow() {
        return sharedPreferences.getInt("row", 5);
    }

    @Override
    public void setRow(int row) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("row", row);
        editor.apply();
    }
}
