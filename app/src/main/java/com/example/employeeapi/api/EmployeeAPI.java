package com.example.employeeapi.api;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employees")
   Call<List<Employee>>getALLEmployee();


    //register
    @POST("create")
    Call<Void>registerEmployee(@Body EmployeeCUD emp);




    //get employee on basis of EMpID
    @GET("employee/{empID}")
    Call<Employee>getEmployeeByID(@Path("empID") int empID);



}
