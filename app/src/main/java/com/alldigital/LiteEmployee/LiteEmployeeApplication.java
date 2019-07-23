package com.alldigital.LiteEmployee;

import android.app.Application;

import com.alldigital.LiteEmployee.persistence.injection.component.AppComponent;
import com.alldigital.LiteEmployee.persistence.injection.component.DaggerAppComponent;
import com.alldigital.LiteEmployee.persistence.injection.module.AppModule;

public class LiteEmployeeApplication extends Application {
    private AppComponent mAppComponent;
    private static LiteEmployeeApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        mAppComponent.inject(this);
    }

    public static synchronized LiteEmployeeApplication getInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
