package com.example.employeeapi.ui.EditEmployee;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeeapi.R;
import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;
import com.example.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeEdit extends Fragment {

    private EmployeeEditViewModel mViewModel;
    private EditText etid,etname,etsalary,etage;
    Button btnsearch,btnupdate,btndelete;
    Retrofit retrofit;

    EmployeeAPI employeeAPI;

    public static EmployeeEdit newInstance() {
        return new EmployeeEdit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_editemployee,container,false);

        etid=view.findViewById(R.id.etEmpno);
        etname=view.findViewById(R.id.etempname);
        etsalary=view.findViewById(R.id.etempsalary);
        etage=view.findViewById(R.id.etempage);
       btnsearch=view.findViewById(R.id.btnsearch);
       btndelete=view.findViewById(R.id.btndelete);
       btnupdate=view.findViewById(R.id.btnupdate);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });


        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });


        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EmployeeEditViewModel.class);
        // TODO: Use the ViewModel
    }
    private void createInstance(){

        retrofit = new Retrofit.Builder()

                .baseUrl(URL.base_url)

                .addConverterFactory(GsonConverterFactory.create())

                .build();



        employeeAPI= retrofit.create(EmployeeAPI.class);

    }


    private void loadData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Employee> listcall = employeeAPI.getEmployeeByID(Integer.parseInt(etid.getText().toString()));

      listcall.enqueue(new Callback<Employee>() {
          @Override
          public void onResponse(Call<Employee> call, Response<Employee> response) {

              etname.setText(response.body().getEmployee_name());
              etsalary.setText(Float.toString(response.body().getEmployee_salary()));
              etage.setText(Integer.toString(response.body().getEmployee_age()));

          }

          @Override
          public void onFailure(Call<Employee> call, Throwable t) {
              Toast.makeText(getContext(),"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

          }
      });
            }

    private  void updateEmployee() {

      createInstance();

        EmployeeCUD employee = new EmployeeCUD(

                etname.getText().toString(),

                Float.parseFloat(etsalary.getText().toString()),

                Integer.parseInt(etage.getText().toString())

        );

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etid.getText().toString()),employee);

        voidCall.enqueue(new Callback<Void>() {

            @Override

            public void onResponse(Call<Void> call, Response<Void> response) {

                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();

            }



            @Override

            public void onFailure(Call<Void> call, Throwable t) {

                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }


    private void deleteEmployee() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<Void> voidCall = employeeAPI.deleteEmployee(Integer.parseInt(etid.getText().toString()));
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(),"Successfully deleted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(),"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
