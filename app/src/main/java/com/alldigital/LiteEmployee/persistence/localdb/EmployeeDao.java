package com.alldigital.LiteEmployee.persistence.localdb;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeeEntity employeeEntity);

    @Query("SELECT * from employee_table")
    List<EmployeeEntity> getAllEmployees();

    @Query("SELECT * FROM employee_table WHERE id = :id ORDER BY id ASC")
    EmployeeEntity getEmployee(String id);

    @Query("SELECT * FROM employee_table ORDER BY id DESC LIMIT 1")
    EmployeeEntity getTopEmployee();
}
