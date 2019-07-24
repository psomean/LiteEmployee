package com.alldigital.LiteEmployee.view.employeelist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alldigital.LiteEmployee.R;
import com.alldigital.LiteEmployee.core.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int footerView = 1;
    private OnItemClickListener listener;
    private List<EmployeeEntity> mEmployeeList = new ArrayList<>();

    public EmployeeListAdapter(List<EmployeeEntity> employees) {
        this.mEmployeeList = employees;
    }

    public EmployeeListAdapter() {

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == footerView) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_employee_footer, parent, false);
            return new FooterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_list_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < mEmployeeList.size())
            ((ViewHolder)holder).bindContent(mEmployeeList.get(position));
        else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder)holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size() + 1;
    }

    @Override public int getItemViewType(int position) {
        if (position == mEmployeeList.size()) {
            // This is where we'll add footer.
            return footerView;
        }
        return super.getItemViewType(position);
    }

    public void setEmployeeList(List<EmployeeEntity> employeeList) {
        this.mEmployeeList.clear();
        this.mEmployeeList = employeeList;
        super.notifyDataSetChanged();
    }
    public void addEmployee(EmployeeEntity employeeEntity) {
        this.mEmployeeList.add(employeeEntity);
        super.notifyDataSetChanged();
    }

    public void clearEmployee() {
        if (!mEmployeeList.isEmpty())
            mEmployeeList.clear();
        super.notifyDataSetChanged();
    }

    public List<EmployeeEntity> getEmployeeList() {
        return mEmployeeList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_avatar) ImageView imageView;
        @BindView(R.id.ePhoneNumber) EditText phoneNumber;
        @BindView(R.id.eEmployeeName) EditText fullName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClick(mEmployeeList.get(getAdapterPosition())));
        }

        public void bindContent(EmployeeEntity employeeEntity) {
            fullName.setText(employeeEntity.getFullName());
            phoneNumber.setText(employeeEntity.getPhoneNumber());

            Bitmap bmp = BitmapFactory.decodeByteArray(employeeEntity.getAvatar(),
                    0, employeeEntity.getAvatar().length);

            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                    140,
                    140, false));
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind() {
            itemView.setOnClickListener(v -> {
                listener.onAddItemClick();
            });
        }
    }

    public static abstract class OnItemClickListener {
        void onItemClick(EmployeeEntity employeeEntity){}
        void onAddItemClick(){}
    }

}
