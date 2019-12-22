package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeDetails extends AppCompatActivity {

   private  EmployeeAdapter employeeAdapter;
   private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);




        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //create handler for retrofit instance interface

        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getALLEmployee();

//execute request asynchronously
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override

            //handle a successful response
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(EmployeeDetails.this,"Error" + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                LoadDataList(response.body());
            //    List<Employee>employeeList=response.body();

//                for(Employee employee:employeeList){
//                    String data="";
//                    data +="Employee name: "+ employee.getEmployee_name() +"\n";
//                    data +="Employee name: "+ employee.getEmployee_age() +"\n";
//                    data +="Employee name: "+ employee.getEmployee_salary() +"\n";
//                    data +="............." + "\n";
//                   // tvoutput.append(data);
//
//
//
//                }
//

            }

            @Override

            //handle exection on failure
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Log.d("Mero msg","onFailure:"+t.getLocalizedMessage());
               // tvoutput.setText("Error"+t.getMessage());
            }
        });


    }
//get refrence to the retrieved data as a list
    private void LoadDataList(List<Employee>employeeList){
        recyclerView=findViewById(R.id.recyclerview);
        employeeAdapter=new EmployeeAdapter(employeeList);


        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(EmployeeDetails.this);
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeeAdapter);
    }

}
