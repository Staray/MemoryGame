package io.github.staray.memorygame.info;

import io.github.staray.memorygame.BasePresenter;
import io.github.staray.memorygame.BaseView;

/**
 * Created by staray on 2017/2/6.
 */

public interface InfoContract {
    interface View extends BaseView<Presenter> {
        void showInfo(String text);
    }

    interface Presenter extends BasePresenter {
    }
}
