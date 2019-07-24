package com.alldigital.LiteEmployee.view.emplyeedetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.alldigital.LiteEmployee.LiteEmployeeApplication;
import com.alldigital.LiteEmployee.R;
import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;
import com.alldigital.LiteEmployee.persistence.injection.component.DaggerEmployeeDetailComponent;
import com.alldigital.LiteEmployee.persistence.injection.module.EmployeeDetailModule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.alldigital.LiteEmployee.view.employeelist.EmployeeListActivity.KEY_EMPLOYEE;

public class EmployeeDetailActivity extends AppCompatActivity implements EmployeeDetailView {
    Unbinder unbinder;

    private EmployeeEntity mEntity = null;

    private static final int RC_SELECT_IMAGE = 2;

    @Inject EmployeeDetailsPresenter presenter;

    @BindView(R.id.eEmployeeName) EditText eEmployeeName;
    @BindView(R.id.ePhoneNumber) EditText ePhoneNumber;
    @BindView(R.id.img_avatar) ImageView imageView;

    @OnClick(R.id.img_avatar) void onSectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"});
        }

        startActivityForResult(Intent.createChooser(intent, "Select image"), RC_SELECT_IMAGE);
    }

    @OnClick(R.id.btnSave) void onSaveButton() {
        if (eEmployeeName.getText().toString().isEmpty()) {
            eEmployeeName.setError("Name is empty!");
            return;
        }
        if (ePhoneNumber.getText().toString().isEmpty()) {
            ePhoneNumber.setError("Phone number is empty!");
            return;
        }

        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();

        if (drawable == null) return;

        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
        byte[] byteArray = stream.toByteArray();

        EmployeeEntity entity = new EmployeeEntity();
        entity.setAvatar(byteArray);
        entity.setFullName(eEmployeeName.getText().toString());
        entity.setPhoneNumber(ePhoneNumber.getText().toString());

        if (mEntity != null) entity.setId(mEntity.getId());

        presenter.saveEmployee(entity);

        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_EMPLOYEE, entity);
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        unbinder = ButterKnife.bind(this);

        DaggerEmployeeDetailComponent.builder()
                .appComponent(LiteEmployeeApplication.getInstance().getAppComponent())
                .employeeDetailModule(new EmployeeDetailModule(this))
                .build()
                .inject(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mEntity = (EmployeeEntity) bundle.getSerializable(KEY_EMPLOYEE);
            eEmployeeName.setText(mEntity.getFullName());
            ePhoneNumber.setText(mEntity.getPhoneNumber());

            Bitmap bmp = BitmapFactory.decodeByteArray(mEntity.getAvatar(),
                    0, mEntity.getAvatar().length);

            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                    140,
                    140, false));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SELECT_IMAGE && resultCode == Activity.RESULT_OK &&
                data != null && data.getData() != null) {
            Uri selectedImagePath = data.getData();

            Bitmap selectedImageBmp = null;
            try {
                selectedImageBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImagePath);

                imageView.setImageBitmap(Bitmap.createScaledBitmap(selectedImageBmp,
                        imageView.getWidth(),
                        imageView.getHeight(), false));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
