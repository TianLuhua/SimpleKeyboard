package com.boyue.tlh.payment.utils.fieldview;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
public class ViewFind {

    /**
     * @param activity
     */
    public static void bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();

        Field[] declareFields = aClass.getDeclaredFields();

        if (declareFields != null && declareFields.length > 0) {

            for (Field field : declareFields) {
                Class<?> type = field.getType();
                if ( //是否是静态的
                        Modifier.isStatic(field.getModifiers())
                                //是否是final的
                                || Modifier.isFinal(field.getModifiers())
                                //是否是基本类型
                                || type.isPrimitive()
                                //是否是数组
                                || type.isArray()) {
                    continue;
                }

                FieldView annotation = field.getAnnotation(FieldView.class);
                if (annotation != null) {
                    View view = activity.findViewById(annotation.value());
                    if (view != null) {
                        field.setAccessible(true);
                        try {
                            field.set(activity, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }


    }

    /**
     * @param fragment
     */
    public static void bind(Fragment fragment) {

        Class<? extends Fragment> fClass = fragment.getClass();

        Field[] declareFields = fClass.getDeclaredFields();

        if (declareFields != null && declareFields.length > 0) {

            for (Field field : declareFields) {
                Class<?> type = field.getType();
                if ( //是否是静态的
                        Modifier.isStatic(field.getModifiers())
                                //是否是final的
                                || Modifier.isFinal(field.getModifiers())
                                //是否是基本类型
                                || type.isPrimitive()
                                //是否是数组
                                || type.isArray()) {
                    continue;
                }

                FieldView annotation = field.getAnnotation(FieldView.class);
                if (annotation != null) {
                    View view = fragment.getView().findViewById(annotation.value());
                    if (view != null) {
                        field.setAccessible(true);
                        try {
                            field.set(fragment, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }


        }


    }

    /**
     * 注解布局Acvitiy id
     */
    public static void displayActivityLayout(Activity activity) {
        Class<?> clazz = activity.getClass();
        if (clazz.getAnnotations() != null) {
            if (clazz.isAnnotationPresent(FieldLay.class)) {
               FieldLay inject = clazz.getAnnotation(FieldLay.class);
                int mLayoutId = inject.value();
                activity.setContentView(mLayoutId);
            }
        }

    }


}
