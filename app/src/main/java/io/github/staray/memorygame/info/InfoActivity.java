package io.github.staray.memorygame.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.github.staray.memorygame.R;
import io.github.staray.memorygame.data.DataImpl;

/**
 * Created by staray on 2017/2/6.
 */

public class InfoActivity extends Activity implements InfoContract.View {
    private InfoContract.Presenter presenter;
    private TextView infoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_view);
        infoTv = (TextView) findViewById(R.id.info_tv);

        new InfoPresenter(DataImpl.getInstance(this), this);

        findViewById(R.id.info_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(InfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showInfo(String text) {
        infoTv.setText(text);
    }
}
