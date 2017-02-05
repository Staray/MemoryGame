package io.github.staray.memorygame.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import io.github.staray.memorygame.R;

/**
 * Created by staray on 2017/2/5.
 */

public class CustomButton extends FrameLayout {
    private ImageView btnIv;
    private Drawable resDrawable;

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        resDrawable = typedArray.getDrawable(R.styleable.CustomButton_imageSrc);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_btn_view, this);
        btnIv = (ImageView) view.findViewById(R.id.custom_btn_iv);

        if (null != resDrawable) {
            btnIv.setImageDrawable(resDrawable);
        }
    }

    public void setBtnIv(int resId) {
        btnIv.setImageResource(resId);
    }
}
