package com.nikitagordia.shop.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nikitagordia.shop.Presenters.MainPresenter;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class ApplicationServiceHolder extends Service {

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        getApplication().onTerminate();
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
