package io.github.staray.memorygame.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import io.github.staray.memorygame.R;
import io.github.staray.memorygame.data.DataImpl;

/**
 * Created by staray on 2017/1/20.
 */

public class SettingActivity extends Activity implements SettingContract.View {
    private TextView levelTv;
    private SeekBar seekBar;
    private SettingContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_view);
        init();

        new SettingPresenter(this, DataImpl.getInstance(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void init() {
        levelTv = (TextView) findViewById(R.id.setting_level_tv);
        seekBar = (SeekBar) findViewById(R.id.setting_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.onSeekBarChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        findViewById(R.id.setting_back_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLevel(int level) {
        level += 3;
        levelTv.setText("难度：" + String.valueOf(level));
    }

    @Override
    public void setSeekBarLevel(int level) {
        seekBar.setProgress(level);
    }
}
