package com.boyue.tlh.inputview.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;


import com.boyue.tlh.inputview.R;
import com.boyue.tlh.inputview.keyboard.adapter.KeyBoardAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tianluhua on 2018\8\8 0008.
 */
public class VirtualKeyboardView extends FrameLayout implements AdapterView.OnItemClickListener {

    public static final String KEY = "key";

    public final int SELETE_POSITION = 11;
    public final int NUMBER_10_POSITION = 10;
    public final int NUMBER_9_POSITION = 9;
    public final int NUMBER_8_POSITION = 8;
    public final int NUMBER_7_POSITION = 7;
    public final int NUMBER_6_POSITION = 6;
    public final int NUMBER_5_POSITION = 5;
    public final int NUMBER_4_POSITION = 4;
    public final int NUMBER_3_POSITION = 3;
    public final int NUMBER_2_POSITION = 2;
    public final int NUMBER_1_POSITION = 1;
    public final int NUMBER_0_POSITION = 0;


    private int passWordLength = 6;
    private Context context;
    private View rootView;
    //用于缓存密码
    private StringBuilder pwdBuilder = new StringBuilder();
    //当前是输入的第几个密码
    private int cureentInputIndex = 0;


    private GridView gridView;    //用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能

    private ArrayList<Map<String, String>> valueList;    //有人可能有疑问，为何这里不用数组了？


    public VirtualKeyboardView(Context context) {
        this(context, null);
    }

    public VirtualKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.valueList = new ArrayList<>();
        this.rootView = View.inflate(context, R.layout.layout_virtual_keyboard, this);
        this.gridView = rootView.findViewById(R.id.gv_keybord);
        initValueList();
        setupView();
    }


    private void initValueList() {
        // 初始化按钮上应该显示的数字
        for (int i = 1; i < 13; i++) {
            Map<String, String> map = new HashMap<>();
            if (i < 10) {
                map.put(KEY, String.valueOf(i));
            } else if (i == 10) {
                //UI设计要求这按键没有功能
                map.put(KEY, "");
            } else if (i == 11) {
                map.put(KEY, String.valueOf(0));
            } else if (i == 12) {
                map.put(KEY, "");
            }
            valueList.add(map);
        }
    }

    public ArrayList<Map<String, String>> getValueList() {
        return valueList;
    }

    public GridView getGridView() {
        return gridView;
    }

    public void setPassWordLength(int passWordLength) {
        this.passWordLength = passWordLength;
    }

    private void setupView() {
        if (valueList == null)
            return;
        KeyBoardAdapter keyBoardAdapter = new KeyBoardAdapter(context, valueList);
        gridView.setAdapter(keyBoardAdapter);
        gridView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case NUMBER_0_POSITION:
            case NUMBER_1_POSITION:
            case NUMBER_2_POSITION:
            case NUMBER_3_POSITION:
            case NUMBER_4_POSITION:
            case NUMBER_5_POSITION:
            case NUMBER_6_POSITION:
            case NUMBER_7_POSITION:
            case NUMBER_8_POSITION:
            case NUMBER_9_POSITION:
            case NUMBER_10_POSITION:
                //如果要求密码的长度大于用户设置的长度，用户操作键盘无作用
                if (cureentInputIndex >= passWordLength) {
                    if (listener != null) {
                        listener.inputFinish(pwdBuilder.toString());
                        return;
                    }
                }

                //没有达到用户设定的密码长度，继续回调
                String inputString = valueList.get(position).get(KEY);
                pwdBuilder.append(inputString);
                if (listener != null) {
                    listener.inputNumber(cureentInputIndex, inputString);
                    listener.hasInputNumbers(pwdBuilder.toString());
                }
                cureentInputIndex++;

                //如果达到用户设置的密码长度，就回调全部密码到用户端
                if (cureentInputIndex == passWordLength)
                    listener.inputFinish(pwdBuilder.toString());

                break;
            case SELETE_POSITION:
                cureentInputIndex--;


                if (pwdBuilder.length() <= 0) {
                    if (listener != null) {
                        listener.hasEmpty();
                        cureentInputIndex = 0;
                    }
                    return;
                }

                //密码还没有清空
                String deletedPwd = pwdBuilder.substring(pwdBuilder.length() - 1, pwdBuilder.length());
                pwdBuilder.deleteCharAt(pwdBuilder.length() - 1);
                if (listener != null) {
                    listener.delete(cureentInputIndex, deletedPwd);
                    listener.hasInputNumbers(pwdBuilder.toString());
                }

                //密码清空了
                if (cureentInputIndex <= 0) {
                    listener.hasEmpty();
                }
                break;
        }

    }

    private KeyboardChangeListener listener;

    public void setKeyboardChangeListener(KeyboardChangeListener listener) {
        this.listener = listener;
    }

    public interface KeyboardChangeListener {

        /**
         * 回调当前输入的密码
         *
         * @param inputIndex 当前出入密码在pwdBuilder中的下标（从0开始）
         * @param number     当前输入的密码
         */
        void inputNumber(int inputIndex, String number);

        /**
         * 回调已经输入的密码
         *
         * @param pwd 缓存在pwdBuilder中的密码
         */
        void hasInputNumbers(String pwd);

        /**
         * 删除功能键的回调
         *
         * @param deleteIndex 下标从0开始计算
         * @param number      对应删除的密码
         */
        void delete(int deleteIndex, String number);

        /**
         * 密码为空的时候回调
         */
        void hasEmpty();

        /**
         * 完成密码的输入
         */
        void inputFinish(String pwd);
    }
}
