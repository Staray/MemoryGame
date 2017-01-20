package io.github.staray.memorygame.main;

import java.util.List;

import io.github.staray.memorygame.BasePresenter;
import io.github.staray.memorygame.BaseView;

/**
 * Created by staray on 2017/1/19.
 * 程序主契约接口
 */

interface MainContract {

    interface View extends BaseView<Presenter> {
        void setBestTime(String time);

        void setTime(String time);

        void setTimeVisible(int visible);

        void setCountdown(String time);

        void setCountdownVisible(int visible);

        void replaceData(List<String> numList);

        void setGridRow(int row);

        void setRestartBtnClickable(boolean clickable);
    }

    interface Presenter extends BasePresenter {
        void onItemClick(int num);

        void onDestroy();
    }
}
