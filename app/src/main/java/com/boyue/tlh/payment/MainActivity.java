package com.boyue.tlh.payment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.boyue.tlh.inputview.password.PassWordView;
import com.boyue.tlh.inputview.password.SimplePassWordView;
import com.boyue.tlh.payment.utils.HideSystemUIUtils;
import com.boyue.tlh.payment.utils.fieldview.FieldLay;
import com.boyue.tlh.payment.utils.fieldview.FieldView;
import com.boyue.tlh.payment.utils.fieldview.ViewFind;

@FieldLay(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements PassWordView.OnPassWordChangeListner {

    @FieldView(R.id.inputview)
    private SimplePassWordView passWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideSystemUIUtils.hideSystemUI(this);
        ViewFind.displayActivityLayout(this);
        ViewFind.bind(this);
        passWordView.addOnPassWordChangeListner(this);
    }


    @Override
    public void onChanged(PassWordView mPassWordView, boolean isInput, int index, String pwd) {
        if (isInput)
            Log.e("tlh", "onChanged,输入的密码是：" + pwd);
        else
            Log.e("tlh", "onChanged,删除的密码是：" + pwd);
    }

    @Override
    public void onfinshed(PassWordView mPassWordView, String pwd) {
        Log.e("tlh", "onfinshed,密码是：" + pwd);
//        mPassWordView.updatePassWordStatus(R.color.colorAccent);

    }

    @Override
    public void hasInputNumbers(String pwd) {
        Log.e("tlh", "hasInputNumbers：" + pwd);
    }

    @Override
    public void hasEmpty() {
        Log.e("tlh", "没有密码缓存了！");
    }
}
