package com.alldigital.LiteEmployee.persistence.injection.component;

import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeListModule;
import com.alldigital.LiteEmployee.persistence.injection.scope.PerActivity;
import com.alldigital.LiteEmployee.view.employeelist.EmployeeListActivity;
import dagger.Component;

@PerActivity
@Component(modules = {EmployeeListModule.class}, dependencies = {AppComponent.class})
public interface EmployeeListComponent {
    void inject(EmployeeListActivity employeeListActivity);
}
