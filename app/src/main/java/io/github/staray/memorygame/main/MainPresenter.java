package io.github.staray.memorygame.main;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.github.staray.memorygame.data.Data;

/**
 * Created by staray on 2017/1/19.
 * 程序主逻辑
 */
class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Data data;

    private Handler handler;
    private int currentTime = 0;

    private Timer timer;
    private TimerTask timerTask;
    private CountDownTimer countDownTimer;

    private int result = 1;

    private final static int MSG_PROCESSING = 0;
    private final static int MSG_FINISH = 1;
    private int row;

    MainPresenter(MainContract.View view, Data data) {
        this.view = view;
        this.data = data;
        view.setPresenter(this);
    }

    private void init() {
        initTimer();
        currentTime = 0;
        view.setTimeVisible(View.INVISIBLE);

        handler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                if (MSG_PROCESSING == msg.what) {
                    currentTime++;
                    String time = formatTime(currentTime);
                    view.setTime(time);
                } else if (MSG_FINISH == msg.what) {
                    if (currentTime < data.getBestTime()) {
                        data.setBestTime(currentTime);
                        showBest();
                    }
                }
            }
        };

        countDownTimer = new CountDownTimer(3100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                view.setCountdown(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                view.setCountdownVisible(View.GONE);
                genData();
                view.setTimeVisible(View.VISIBLE);
                startTiming();
                view.setRestartBtnClickable(true);
            }
        };
    }

    private void initTimer() {
        cancelTimer();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_PROCESSING);
            }
        };
    }

    private void cancelTimer() {
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
        if (null != timerTask) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    @Override
    public void start() {
        init();
        showBest();
        view.setCountdownVisible(View.VISIBLE);
        view.setRestartBtnClickable(false);
        countDownTimer.start();
    }

    private void showBest() {
        int bestTime = data.getBestTime();
        view.setBestTime(formatTime(bestTime));
    }

    private void genData() {
        result = 1;
        row = data.getLevel() + 3;
        view.setGridRow(row);

        List<String> numList = getRandomId(row * row);
        view.replaceData(numList);
    }

    private void startTiming() {
        timer.schedule(timerTask, 0, 1);
    }

    @Override
    public void onItemClick(int num) {
        if (result == num) {
            if (result == row * row) {
                cancelTimer();
                handler.sendEmptyMessage(MSG_FINISH);
            }
            result++;
        } else {
            result = 1;
        }
    }

    /**
     * 生成 1-n的随机排列列表
     *
     * @param n 最大值
     * @return 随机排列列表
     */
    private List<String> getRandomId(int n) {
        Integer[] arrayRandom = new Integer[n];
        for (int i = 0; i < n; i++)
            arrayRandom[i] = i + 1;
        List<Integer> list = Arrays.asList(arrayRandom);
        Collections.shuffle(list);

        List<String> numList = new ArrayList<>();
        for (int num : list) {
            numList.add(String.valueOf(num));
        }

        return numList;
    }

    private String formatTime(int time) {
        int second = time / 1000;
        int millisecond = time % 1000;
        return String.format(Locale.getDefault(), "%02d", second) + ":" + String.format(Locale
                .getDefault(), "%03d", millisecond);
    }

    @Override
    public void onDestroy() {
        cancelTimer();
        countDownTimer.cancel();
    }
}
