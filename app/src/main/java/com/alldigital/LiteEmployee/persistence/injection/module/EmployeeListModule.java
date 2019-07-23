package com.alldigital.LiteEmployee.persistence.injection.module;


import com.alldigital.LiteEmployee.persistence.injection.scope.PerActivity;
import com.alldigital.LiteEmployee.view.employeelist.EmployeeListView;

import dagger.Module;
import dagger.Provides;

@Module
public class EmployeeListModule {

    private final EmployeeListView view;

    public EmployeeListModule(EmployeeListView view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    EmployeeListView  provideEmployeeListView() {
        return this.view;
    }

}
