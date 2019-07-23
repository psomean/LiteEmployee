package com.alldigital.LiteEmployee.persistence.injection.component;

import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeDetailModule;
import com.alldigital.LiteEmployee.persistence.injection.scope.PerActivity;
import com.alldigital.LiteEmployee.view.emplyeedetails.EmployeeDetailActivity;

import dagger.Component;

@PerActivity
@Component(modules = {EmployeeDetailModule.class}, dependencies = {AppComponent.class})
public interface EmployeeDetailComponent {
    void inject(EmployeeDetailActivity employeeDetailActivity);
}
