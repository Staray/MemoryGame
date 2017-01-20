package io.github.staray.memorygame.setting;

import io.github.staray.memorygame.data.Data;

/**
 * Created by staray on 2017/1/20.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private SettingContract.View view;
    private Data data;

    public SettingPresenter(SettingContract.View view, Data data) {
        this.view = view;
        this.data = data;
        view.setPresenter(this);
    }

    @Override
    public void onSeekBarChanged(int progress) {
        data.setLevel(progress);
        showLevel();
    }

    @Override
    public void start() {
        int progress = data.getLevel();
        view.setLevel(progress);
        view.setSeekBarLevel(progress);
    }

    private void showLevel() {
        view.setLevel(data.getLevel());
    }
}
