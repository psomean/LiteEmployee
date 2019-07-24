package com.alldigital.LiteEmployee.view.employeelist;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alldigital.LiteEmployee.LiteEmployeeApplication;
import com.alldigital.LiteEmployee.R;
import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;
import com.alldigital.LiteEmployee.persistence.injection.component.DaggerEmployeeListComponent;
import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeListModule;
import com.alldigital.LiteEmployee.view.emplyeedetails.EmployeeDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView {

    @Inject
    EmployeeListPresenter mPresenter;
    @BindView(R.id.rcl_employee)
    RecyclerView recyclerView;

    private EmployeeListAdapter adapter = new EmployeeListAdapter();
    private Unbinder unbinder;

    public static final String KEY_EMPLOYEE = "KEY_EMPLOYEE";
    public static final int ADD_NEW_PROFILE_REQUEST = 1002;
    public static final int UPDATE_PROFILE_REQUEST = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        DaggerEmployeeListComponent.builder()
                .appComponent(LiteEmployeeApplication.getInstance().getAppComponent())
                .employeeListModule(new EmployeeListModule(this))
                .build()
                .inject(this);

        mPresenter.init();
    }

    @Override
    public void displayEmployee(List<EmployeeEntity> employeeList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setEmployeeList(employeeList);
        adapter.setOnItemClickListener(new EmployeeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(EmployeeEntity employeeEntity) {
                Intent empDetails = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_EMPLOYEE, employeeEntity);
                empDetails.putExtras(bundle);
                empDetails.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(empDetails, UPDATE_PROFILE_REQUEST);
            }

            @Override
            void onAddItemClick() {
                Intent pickContactIntent = new Intent(EmployeeListActivity.this, EmployeeDetailActivity.class);
                startActivityForResult(pickContactIntent, ADD_NEW_PROFILE_REQUEST);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NEW_PROFILE_REQUEST && resultCode == RESULT_OK) {
            // Make sure the request was successful
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                EmployeeEntity entity = (EmployeeEntity) bundle.getSerializable(KEY_EMPLOYEE);

                int maxId = 0;
                for (EmployeeEntity e : adapter.getEmployeeList()) {
                    if (e.getId() > maxId) {
                        maxId = e.getId();
                    }
                }
                entity.setId(maxId + 1);
                adapter.addEmployee(entity);
                mPresenter.saveNewEmployee(entity);
            }

        }  else {
            mPresenter.init();
        }
    }
}
