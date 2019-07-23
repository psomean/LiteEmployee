package com.alldigital.LiteEmployee.persistence.injection.component;


import android.content.Context;

import com.alldigital.LiteEmployee.LiteEmployeeApplication;
import com.alldigital.LiteEmployee.core.repository.EmployeeRepository;
import com.alldigital.LiteEmployee.persistence.injection.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(LiteEmployeeApplication app);

    EmployeeRepository exposeEmployeeRepository();
    Context exposeAppContext();
}
