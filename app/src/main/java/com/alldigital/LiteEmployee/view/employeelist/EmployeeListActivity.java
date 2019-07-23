package com.alldigital.LiteEmployee.view.employeelist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alldigital.LiteEmployee.LiteEmployeeApplication;
import com.alldigital.LiteEmployee.R;
import com.alldigital.LiteEmployee.persistence.injection.component.DaggerEmployeeListComponent;
import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeListModule;
import com.alldigital.LiteEmployee.view.emplyeedetails.EmployeeListPresenter;

import javax.inject.Inject;

public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView {

    @Inject EmployeeListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerEmployeeListComponent.builder()
                .appComponent(LiteEmployeeApplication.getInstance().getAppComponent())
                .employeeListModule(new EmployeeListModule(this))
                .build()
                .inject(this);

    }
}
