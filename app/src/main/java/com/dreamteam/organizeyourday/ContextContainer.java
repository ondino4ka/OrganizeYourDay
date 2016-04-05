package com.dreamteam.organizeyourday;

import android.app.Application;


public class ContextContainer extends Application {
    private static ContextContainer container;

    public ContextContainer() {
        container = this;
    }

    public static ContextContainer getContext() {
        return container;
    }
}
