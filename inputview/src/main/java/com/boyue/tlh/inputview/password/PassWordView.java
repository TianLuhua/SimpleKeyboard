package com.boyue.tlh.inputview.password;

/**
 * Created by Tianluhua on 2018\8\9 0009.
 */
public interface PassWordView {

    /**
     * PassWordView 的默认配置
     */
    class Config {
        public static int PASSWORD_LENGTH = 4;
    }

    /**
     * 根据密码的匹配情况设置状态
     *
     * @param colorResources
     */
    void updatePassWordStatus(int colorResources);


    interface OnPassWordChangeListner {

        /**
         * 用户输入发生变化
         *
         * @param isInput 输入/删除
         * @param index   密码的index
         * @param pwd     当前输入或者删除的密码
         */
        void onChanged(PassWordView mPassWordView, boolean isInput, int index, String pwd);

        /**
         * 用户输入密码完成
         *
         * @param pwd 用户输入的整串密码
         */
        void onfinshed(PassWordView mPassWordView, String pwd);


        /**
         * 当前输入的密码
         *
         * @param pwd
         */
        void hasInputNumbers(String pwd);


        /**
         * 当前密码为空的状态
         */
        void hasEmpty();

    }


}
