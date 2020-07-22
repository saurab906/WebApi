package com.example.webapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webapi.R;
import com.example.webapi.activity.api.TaskApi;

import URl.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTask extends AppCompatActivity {

    EditText edtTaskName;
    Button btnRegisterTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        edtTaskName=findViewById(R.id.edtTaskName);
        btnRegisterTask=findViewById(R.id.btnRegisterTask);

        btnRegisterTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create()).build();

                TaskApi taskApi=URL.getInstance().create(TaskApi.class);

                Call<Void> call=taskApi.register_Task(URL.token,edtTaskName.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful())
                        {
                            Toast.makeText(AddTask.this,"Error:"+response.code(),Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(AddTask.this,"Success",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        Toast.makeText(AddTask.this,"Error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}