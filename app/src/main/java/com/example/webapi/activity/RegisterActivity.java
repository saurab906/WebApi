package com.example.webapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webapi.R;
import com.example.webapi.activity.api.UserApi;
import com.example.webapi.activity.model.User;
import com.example.webapi.activity.response.UserResponse;

import URl.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister;
    EditText edtFirstName, edtLastName, edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName=findViewById(R.id.edtFirstName);
        edtLastName=findViewById(R.id.edtLastName);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);

        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

       /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder().
                        baseUrl("http://localhost:3000/").
                        addConverterFactory(GsonConverterFactory.create()).build();// concerting json file to Gson

                UserApi userApi= retrofit.create(UserApi.class);

                User user = new User(edtFirstName.getText().toString(),edtLastName.getText().toString(),edtUsername.getText().toString(),edtPassword.getText().toString());
                Call<UserResponse> call= userApi.registerUser(user);

                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if ((!response.isSuccessful())) {
                            Toast.makeText(RegisterActivity.this,"code:"+response.code(), Toast.LENGTH_LONG).show();
                            Toast.makeText(RegisterActivity.this,"Succeed",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this,"Unsuccessful"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });*/
    }

    @Override
    public void onClick(View v) {
        //Retrofit retrofit = new Retrofit.Builder().
              //  baseUrl("http://10.0.2.2:3000/").
                //addConverterFactory(GsonConverterFactory.create()).build();// concerting json file to Gson

        UserApi userApi= URL.getInstance().create(UserApi.class);

        //User user = new User(edtFirstName.getText().toString(),edtLastName.getText().toString(),edtUsername.getText().toString(),edtPassword.getText().toString());
        Call<UserResponse> call= userApi.registerUser(new User(edtFirstName.getText().toString(),edtLastName.getText().toString(),edtUsername.getText().toString(),edtPassword.getText().toString()));

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if ((!response.isSuccessful())) {
                   Toast.makeText(RegisterActivity.this,"code:"+response.code(), Toast.LENGTH_LONG).show();
                    Toast.makeText(RegisterActivity.this,"Succeed",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Unsuccessful"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}

