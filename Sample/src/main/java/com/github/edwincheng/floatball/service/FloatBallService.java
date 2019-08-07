package com.github.edwincheng.floatball.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.github.edwincheng.floatbuttonball.manager.ViewManager;

public class FloatBallService extends Service {

    public FloatBallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        ViewManager manager = ViewManager.getInstance(this);
        manager.addFloatBall();
        super.onCreate();
    }

}