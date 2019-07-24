package com.alldigital.LiteEmployee.view.emplyeedetails;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;
import com.alldigital.LiteEmployee.core.repository.EmployeeRepository;

import javax.inject.Inject;

public class EmployeeDetailsPresenter {
    @Inject EmployeeRepository repository;
    @Inject EmployeeDetailsPresenter() {}

    public void saveEmployee(EmployeeEntity employeeEntity) {
        repository.insert(employeeEntity);
    }
}
