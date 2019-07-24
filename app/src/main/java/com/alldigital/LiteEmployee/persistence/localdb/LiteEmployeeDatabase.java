package com.alldigital.LiteEmployee.persistence.localdb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;

@Database(entities = {EmployeeEntity.class}, version = 2, exportSchema = false)
public abstract class LiteEmployeeDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();

    private static volatile LiteEmployeeDatabase INSTANCE;

    public static LiteEmployeeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LiteEmployeeDatabase.class) {
                // Create database here
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LiteEmployeeDatabase.class, "Lite_employee_database").allowMainThreadQueries()
                        .build();
            }
        }
        return INSTANCE;
    }
}
