package com.alldigital.LiteEmployee.view.employeelist;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeListView {
    void displayEmployee(List<EmployeeEntity> employeeList);
}
