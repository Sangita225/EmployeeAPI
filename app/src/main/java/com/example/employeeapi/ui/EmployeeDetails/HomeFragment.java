package com.example.employeeapi.ui.EmployeeDetails;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeapi.EmployeeAdapter;
import com.example.employeeapi.R;
import com.example.employeeapi.api.EmployeeAPI;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private EmployeeAdapter employeeAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        recyclerView=root.findViewById(R.id.recyclerview);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //create handler for retrofit instance interface

        EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getALLEmployee();



        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(),"Error" + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                LoadDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Mero msg","onFailure:"+t.getLocalizedMessage());
                // tvoutput.setText("Error"+t.getMessage());
            }
        });{

        };



        return root;
    }


    //get refrence to the retrieved data as a list
    private void LoadDataList(List<Employee>employeeList){

        employeeAdapter=new EmployeeAdapter(employeeList);


        //using a linear layout manager
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter to the recyclerview
        recyclerView.setAdapter(employeeAdapter);
    }
}