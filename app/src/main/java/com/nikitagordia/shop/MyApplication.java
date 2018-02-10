package com.nikitagordia.shop;

import com.nikitagordia.shop.Presenters.MainPresenter;

import java.util.LinkedList;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class MyApplication extends android.app.Application {

    private LinkedList<Runnable> callbacks;

    public MyApplication() {
        callbacks = new LinkedList<>();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (Runnable runnable : callbacks)
            runnable.run();
    }

    public void registerLastCallback(Runnable callback) {
        callbacks.add(callback);
    }

}
