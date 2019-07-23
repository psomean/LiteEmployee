package com.alldigital.LiteEmployee.persistence.repository;

import android.content.Context;
import android.os.AsyncTask;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;
import com.alldigital.LiteEmployee.core.repository.EmployeeRepository;
import com.alldigital.LiteEmployee.persistence.localdb.EmployeeDao;
import com.alldigital.LiteEmployee.persistence.localdb.LiteEmployeeDatabase;
import com.alldigital.LiteEmployee.persistence.utils.ImageSaver;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private ImageSaver mImageSaver;
    private EmployeeDao mEmployeeDao;
    private AsyncTask<String, Void, Void> mReadAsyncTask;
    private List<EmployeeEntity> mAllEmployees;

    public EmployeeRepositoryImpl(LiteEmployeeDatabase database, ImageSaver imageSaver) {
        mEmployeeDao = database.employeeDao();
        mAllEmployees = mEmployeeDao.getAllEmployees();
    }
    @Override
    public void getEmployeeList(@NotNull EmployeeListCallBack employeeListCallBack) {
        employeeListCallBack.onEmployeesLoaded(mAllEmployees);
    }

    @Override
    public void insert(EmployeeEntity employeeEntity) {
        new insertAsyncTask(mEmployeeDao).execute(employeeEntity);
    }

    @Override
    public void getEmployee(String id, EmployeeEntityCallBack employeeEntityCallBack) {
        mReadAsyncTask = new readAsyncTask(mEmployeeDao, employeeEntityCallBack).execute(id);
    }

    @Override
    public void cancel() {
        if(mReadAsyncTask != null) mReadAsyncTask.cancel(true);
    }

    private static class insertAsyncTask extends AsyncTask<EmployeeEntity, Void, Void> {
        private EmployeeDao mAsyncTaskDao;

        insertAsyncTask(EmployeeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EmployeeEntity... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class readAsyncTask extends AsyncTask<String, Void, Void> {
        private EmployeeDao mAsyncTaskDao;
        private EmployeeEntityCallBack mCallBack;

        private readAsyncTask(EmployeeDao dao, EmployeeEntityCallBack callBack) {
            mAsyncTaskDao = dao;
            mCallBack = callBack;
        }

        @Override
        protected Void doInBackground(final String... params) {
            EmployeeEntity employee = mAsyncTaskDao.getEmployee(params[0]);

            if (isCancelled())
                return null;

            mCallBack.onEmployeeLoaded(employee);
            return null;
        }
    }
}
