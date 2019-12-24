package com.example.employeeapi.ui.SearchEmployee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.employeeapi.R;
import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private EditText etEmpNo;
    private TextView tvData;
    private Button btnSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.activity_search_employee, container, false);

        etEmpNo=root.findViewById(R.id.etEmpno);
        tvData=root.findViewById(R.id.tvData);
        btnSearch=root.findViewById(R.id.btnsearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }




        });


        return root;
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listcall = employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listcall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                String content="";
                content+="Id:"+response.body().getId()+"\n";
                content+="Name:"+response.body().getEmployee_name()+"\n";
                content+="Age:"+response.body().getEmployee_age()+"\n";
                content+="Salary:"+response.body().getEmployee_salary()+"\n";
                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();

            }
        });

    }


    }