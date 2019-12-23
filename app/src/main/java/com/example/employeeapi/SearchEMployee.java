package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEMployee extends AppCompatActivity {
    private EditText etEmpNo;
    private TextView tvData;
    private Button btnSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);


        etEmpNo=findViewById(R.id.etEmpno);
        tvData=findViewById(R.id.tvData);
        btnSearch=findViewById(R.id.btnsearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadData();
            }
        });

    }

    private void loadData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<Employee>listcall=employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText().toString()));

        listcall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {


                Toast.makeText(SearchEMployee.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                String content="";
                content+="Id:"+response.body().getId()+"\n";
                content+="Name:"+response.body().getId()+"\n";
                content+="Age:"+response.body().getId()+"\n";
                content+="Salary:"+response.body().getId()+"\n";
                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

                Toast.makeText(SearchEMployee.this,"error",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
