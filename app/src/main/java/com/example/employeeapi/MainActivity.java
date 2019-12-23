package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
   private Button btnviewdetails,btnregister,btnsearch,btnedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btnviewdetails=findViewById(R.id.btndetails);
       btnsearch=findViewById(R.id.btnsearch);
       btnregister=findViewById(R.id.btnregister);


      btnviewdetails.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,EmployeeDetails.class);
              startActivity(intent);
          }
      });

      btnsearch.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,SearchEMployee.class);
              startActivity(intent);
          }
      });

      btnregister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
              startActivity(intent);
          }
      });


    }
}
