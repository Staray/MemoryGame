package io.github.staray.memorygame.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.staray.memorygame.R;
import io.github.staray.memorygame.data.DataImpl;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;
    private TextView bestTimeTv;
    private TextView timeTv;
    private TextView countdownTv;
    private GridView gridView;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        new MainPresenter(this, DataImpl.getInstance(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void init() {
        bestTimeTv = (TextView) findViewById(R.id.best_time_tv);
        timeTv = (TextView) findViewById(R.id.time_tv);
        countdownTv = (TextView) findViewById(R.id.countdown_tv);
        Button restartBtn = (Button) findViewById(R.id.restart_btn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.start();
            }
        });
        gridView = (GridView) findViewById(R.id.grid_view);
        List<String> numList = new ArrayList<>();
        gridAdapter = new GridAdapter(numList);
        gridView.setAdapter(gridAdapter);
    }

    @Override
    public void setGridRow(int row) {
        gridView.setNumColumns(row);
    }

    @Override
    public void setBestTime(String time) {
        bestTimeTv.setText(time);
    }

    @Override
    public void setTime(String time) {
        timeTv.setText(time);
    }

    @Override
    public void setCountdown(String time) {
        countdownTv.setText(time);
    }

    @Override
    public void setCountdownVisible(int visible) {
        countdownTv.setVisibility(visible);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void replaceData(List<String> numList) {
        gridAdapter.replaceData(numList);
    }

    @Override
    public void setTimeVisible(int visible) {
        timeTv.setVisibility(visible);
    }

    private class GridAdapter extends BaseAdapter {

        private List<String> numList;

        GridAdapter(List<String> numList) {
            this.numList = numList;
        }

        void replaceData(List<String> numList) {
            this.numList = numList;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return numList.size();
        }

        @Override
        public Object getItem(int position) {
            return numList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            }

            final TextView item = (TextView) convertView.findViewById(R.id.item_tv);
            item.setText((String) getItem(position));

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onItemClick(Integer.valueOf(item.getText().toString()));
                }
            });
            return convertView;
        }
    }
}
