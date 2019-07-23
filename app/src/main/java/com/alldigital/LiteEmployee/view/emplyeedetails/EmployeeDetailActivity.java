package com.alldigital.LiteEmployee.view.emplyeedetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.alldigital.LiteEmployee.LiteEmployeeApplication;
import com.alldigital.LiteEmployee.R;
import com.alldigital.LiteEmployee.persistence.injection.component.DaggerEmployeeDetailComponent;
import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeDetailModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmployeeDetailActivity extends AppCompatActivity implements EmployeeDetailView {

    @BindView(R.id.eEmployeeName) EditText eEmployeeName;
    @BindView(R.id.ePhoneNumber) EditText ePhoneNumber;

    Unbinder unbinder;

    @Inject EmployeeListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        unbinder = ButterKnife.bind(this);

        DaggerEmployeeDetailComponent.builder()
                .appComponent(LiteEmployeeApplication.getInstance().getAppComponent())
                .employeeDetailModule(new EmployeeDetailModule(this))
                .build()
                .inject(this);

    }
}
