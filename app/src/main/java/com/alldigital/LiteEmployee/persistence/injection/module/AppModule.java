package com.alldigital.LiteEmployee.persistence.injection.module;

import android.content.Context;

import com.alldigital.LiteEmployee.core.repository.EmployeeRepository;
import com.alldigital.LiteEmployee.persistence.localdb.LiteEmployeeDatabase;
import com.alldigital.LiteEmployee.persistence.repository.EmployeeRepositoryImpl;
import com.alldigital.LiteEmployee.persistence.utils.ImageSaver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ImageSaver provideImageSaver(Context context) {
        return new ImageSaver(context);
    }

    @Provides
    @Singleton
    LiteEmployeeDatabase provideLiteDatabase(Context context) {
        return LiteEmployeeDatabase.getDatabase(context);
    }

    @Singleton
    @Provides
    EmployeeRepository provideEmployeeRepository(LiteEmployeeDatabase database, ImageSaver imageSaver) {
        return new EmployeeRepositoryImpl(database, imageSaver);
    }

}
