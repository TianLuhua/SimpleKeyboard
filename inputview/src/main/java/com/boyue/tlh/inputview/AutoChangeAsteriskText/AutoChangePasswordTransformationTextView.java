package com.boyue.tlh.inputview.AutoChangeAsteriskText;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;


/**
 * TextView内容发生变化，一段时间后变成*号
 * Created by Tianluhua on 2018\8\8 0008.
 */
public class AutoChangePasswordTransformationTextView extends AppCompatTextView implements TextWatcher {


    private long DEFAULT_TIME = 500;
    private Handler mHandler;

    public AutoChangePasswordTransformationTextView(Context context) {
        this(context, null);
    }

    public AutoChangePasswordTransformationTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoChangePasswordTransformationTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(this);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHandler = getHandler();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mHandler == null)
            return;
        if (count > 0) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setTransformationMethod(AsteriskPasswordTransformationMethod.getInstance());
                }
            }, DEFAULT_TIME);
        } else {

            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}
