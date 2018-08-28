package com.boyue.tlh.inputview.AutoChangeAsteriskText;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by Tianluhua on 2018\8\9 0009.
 */
public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private static AsteriskPasswordTransformationMethod sInstance;

    public static AsteriskPasswordTransformationMethod getInstance() {
        if (sInstance == null) {
            synchronized (AsteriskPasswordTransformationMethod.class) {
                if (sInstance == null) {
                    sInstance = new AsteriskPasswordTransformationMethod();
                }
            }
        }
        return sInstance;
    }

    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source; // Store char sequence
        }

        public char charAt(int index) {
            return '*'; // This is the important part
        }

        public int length() {
            return mSource.length(); // Return default
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
}
