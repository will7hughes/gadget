package com.whughes.gadget.util;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServiceIdlingResource implements IdlingResource {
    private final String TAG = "USER_SERVICE";

    @Nullable private volatile ResourceCallback resourceCallback;

    private AtomicBoolean isIdleNow = new AtomicBoolean(true);

    private String id;

    public ServiceIdlingResource(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return this.isIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        resourceCallback = callback;
    }

    public void setIdleState(boolean isIdleNow) {
        this.isIdleNow.set(isIdleNow);
        if (isIdleNow && this.isIdleNow != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}
