package io.github.staray.memorygame.info;

import io.github.staray.memorygame.data.Data;

/**
 * Created by staray on 2017/2/6.
 */

public class InfoPresenter implements InfoContract.Presenter {
    private InfoContract.View view;
    private Data data;

    public InfoPresenter(Data data, InfoContract.View view) {
        this.data = data;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        String text = data.getInfo();
        if (null != text) {
            view.showInfo(text);
        }
    }
}
