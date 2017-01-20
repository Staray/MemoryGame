package io.github.staray.memorygame.setting;

import io.github.staray.memorygame.BasePresenter;
import io.github.staray.memorygame.BaseView;

/**
 * Created by staray on 2017/1/20.
 * 设置契约类
 */

interface SettingContract {
    interface View extends BaseView<Presenter> {
        void setLevel(int level);

        void setSeekBarLevel(int level);
    }

    interface Presenter extends BasePresenter {
        void onSeekBarChanged(int progress);
    }
}
