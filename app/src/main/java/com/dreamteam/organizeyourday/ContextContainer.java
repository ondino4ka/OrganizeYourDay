package com.dreamteam.organizeyourday;

import android.app.Application;

/**
 * Created by wot22 on 03.04.2016.
 */
public class ContextContainer extends Application {
    private static ContextContainer container;

    public ContextContainer() {
        container = this;
    }

    public static ContextContainer getContainer() {
        return container;
    }
}
