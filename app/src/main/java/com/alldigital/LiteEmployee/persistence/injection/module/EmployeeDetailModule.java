package com.alldigital.LiteEmployee.persistence.injection.module;

import com.alldigital.LiteEmployee.persistence.injection.scope.PerActivity;
import com.alldigital.LiteEmployee.view.emplyeedetails.EmployeeDetailView;

import dagger.Module;
import dagger.Provides;

@Module
public class EmployeeDetailModule {

    private final EmployeeDetailView view;

    public EmployeeDetailModule(EmployeeDetailView view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    EmployeeDetailView provideEmployeeDetailView() {
        return this.view;
    }

}
