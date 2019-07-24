package com.alldigital.LiteEmployee.view.employeelist;

import android.util.Log;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;
import com.alldigital.LiteEmployee.core.repository.EmployeeRepository;

import javax.inject.Inject;

public class EmployeeListPresenter {

    @Inject EmployeeRepository employeeRepository;
    @Inject EmployeeListView view;

    @Inject
    public EmployeeListPresenter(){

    }

    public void init() {
        employeeRepository.getEmployeeList(employeeEntityList -> {
            Log.d("ALL", employeeEntityList.toString());
            view.displayEmployee(employeeEntityList);
        });
    }

    public void saveNewEmployee(EmployeeEntity entity) {
        employeeRepository.insert(entity);
    }
}
