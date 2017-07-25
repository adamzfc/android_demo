package com.adamzfc.architecturecomponentsdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.adamzfc.architecturecomponentsdemo.di.AppInjector;
import com.squareup.leakcanary.LeakCanary;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by adamzfc on 2017/6/29.
 */
@ReportsCrashes(
        formUri = "http://10.9.254.119:55000/send",
//        formUri = "http://192.168.1.106:55000/send",
        reportType = HttpSender.Type.JSON,
        httpMethod = HttpSender.Method.POST,
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text
)
public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (!ACRA.isACRASenderServiceProcess()) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);

            AppInjector.init(this);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
