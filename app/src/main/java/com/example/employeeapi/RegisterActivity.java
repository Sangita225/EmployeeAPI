package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.EmployeeCUD;
import com.example.employeeapi.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {


    private EditText etname,etSalary,etAge;
    private Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        etname=findViewById(R.id.etname);
//        etSalary=findViewById(R.id.etsalary);
//        etAge=findViewById(R.id.etage);
//        btnregister=findViewById(R.id.btnregister);
//
//        btnregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Register();
//            }
//        });

    }
private void Register(){
        String name=etname.getText().toString();
        Float salary=Float.parseFloat(etSalary.getText().toString());
        int age= Integer.parseInt(etAge.getText().toString());


    EmployeeCUD employeeCUD=new EmployeeCUD(name,salary,age);

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(URL.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
    Call<Void>voidCall=employeeAPI.registerEmployee(employeeCUD);

//    voidCall.enqueue(new Callback<Void>() {
//        @Override
//        public void onResponse(Call<Void> call, Response<Void> response) {
//
//            Toast.makeText(RegisterActivity.this,"you have been registered ",Toast.LENGTH_SHORT).show();
//
//        }
//
//        @Override
//        public void onFailure(Call<Void> call, Throwable t) {
//
//
//            Toast.makeText(RegisterActivity.this,"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
//
//        }
   // });


}


}
