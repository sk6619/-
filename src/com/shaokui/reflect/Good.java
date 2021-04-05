package com.shaokui.reflect;

import javax.swing.text.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Good {


    String name();

}
