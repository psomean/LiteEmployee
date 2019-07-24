package com.alldigital.LiteEmployee.core.repository;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;


import java.util.List;

/**
 * Abstraction for employee data collection, search, insert
 */
public interface EmployeeRepository {
    void getEmployeeList(EmployeeListCallBack employeeListCallBack);

    void insert (EmployeeEntity employeeEntity);

    void getEmployee(String id, EmployeeEntityCallBack employeeEntityCallBack);

    void cancel();

    void getTopEmployee(EmployeeEntityCallBack callBack);

    interface EmployeeListCallBack {
        void onEmployeesLoaded(List<EmployeeEntity> employees);
    }

    interface  EmployeeEntityCallBack {
        void onEmployeeLoaded(EmployeeEntity employee);
    }
}
