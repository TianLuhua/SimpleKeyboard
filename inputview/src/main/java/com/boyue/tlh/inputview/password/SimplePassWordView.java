package com.boyue.tlh.inputview.password;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.boyue.tlh.inputview.AutoChangeAsteriskText.AutoChangePasswordTransformationTextView;
import com.boyue.tlh.inputview.R;
import com.boyue.tlh.inputview.keyboard.VirtualKeyboardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tianluhua on 2018\8\9 0009.
 */
public class SimplePassWordView extends FrameLayout implements PassWordView, VirtualKeyboardView.KeyboardChangeListener {

    private Context mContext;
    private View rootView;

    private VirtualKeyboardView keyBoard;
    private AutoChangePasswordTransformationTextView pwd_01, pwd_02, pwd_03, pwd_04;

    private List<OnPassWordChangeListner> listners = new ArrayList<>();


    public void addOnPassWordChangeListner(OnPassWordChangeListner passWordChangeListner) {
        listners.add(passWordChangeListner);
    }


    public SimplePassWordView(@NonNull Context context) {
        this(context, null);
    }

    public SimplePassWordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimplePassWordView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.rootView = View.inflate(context, R.layout.layout_password, this);
        initView();
    }

    private void initView() {
        keyBoard = rootView.findViewById(R.id.vi_keybord);
        keyBoard.setKeyboardChangeListener(this);
        //可以自己设置密码的长度
        keyBoard.setPassWordLength(Config.PASSWORD_LENGTH);
        pwd_01 = rootView.findViewById(R.id.pwd_01);
        pwd_02 = rootView.findViewById(R.id.pwd_02);
        pwd_03 = rootView.findViewById(R.id.pwd_03);
        pwd_04 = rootView.findViewById(R.id.pwd_04);
    }

    /**
     * 根据密码的匹配情况设置状态
     *
     * @param colorResources
     */
    @Override
    public void updatePassWordStatus(int colorResources) {
        pwd_01.setTextColor(getResources().getColor(colorResources));
        pwd_02.setTextColor(getResources().getColor(colorResources));
        pwd_03.setTextColor(getResources().getColor(colorResources));
        pwd_04.setTextColor(getResources().getColor(colorResources));
    }


    @Override
    public void inputNumber(int inputIndex, String number) {
        if (listners != null && listners.size() > 0)
            for (OnPassWordChangeListner listner : listners) {
                listner.onChanged(this, true, inputIndex, number);
            }
        switch (inputIndex) {
            case 0:
                pwd_01.setText(number);
                break;
            case 1:
                pwd_02.setText(number);
                break;
            case 2:
                pwd_03.setText(number);
                break;
            case 3:
                pwd_04.setText(number);
                break;
        }
    }

    @Override
    public void hasInputNumbers(String pwd) {
        if (listners != null && listners.size() > 0)
            for (OnPassWordChangeListner listner : listners) {
                listner.hasInputNumbers(pwd);
            }
    }


    @Override
    public void delete(int deleteIndex, String number) {
        if (listners != null && listners.size() > 0)
            for (OnPassWordChangeListner listner : listners) {
                listner.onChanged(this, false, deleteIndex, number);
            }

        switch (deleteIndex) {
            case 0:
                pwd_01.setText("");
                break;
            case 1:
                pwd_02.setText("");
                break;
            case 2:
                pwd_03.setText("");
                break;
            case 3:
                pwd_04.setText("");
                break;
        }
    }

    @Override
    public void hasEmpty() {
        if (listners != null && listners.size() > 0)
            for (OnPassWordChangeListner listner : listners) {
                listner.hasEmpty();
            }
    }

    @Override
    public void inputFinish(String pwd) {
        if (listners != null && listners.size() > 0)
            for (OnPassWordChangeListner listner : listners) {
                listner.onfinshed(this, pwd);
            }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        for (OnPassWordChangeListner listner : listners) {
            listner = null;
        }
        listners = null;
    }
}
