package com.boyue.tlh.payment.utils.fieldview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tianluhua on 2018\8\13 0013.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldView {
    /**
     * @return 返回View的ID
     */
    int value();
}
