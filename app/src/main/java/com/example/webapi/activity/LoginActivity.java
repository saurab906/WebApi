package com.example.webapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webapi.R;
import com.example.webapi.activity.api.UserApi;
import com.example.webapi.activity.response.UserResponse;
import com.google.gson.Gson;

import URl.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;
TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);

        btnLogin=findViewById(R.id.btnLogin);

        tvSignUp=findViewById(R.id.tvSignUp);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });
    }

    private void login(){
        //Retrofit retrofit = URL.getInstance();
               // baseUrl("http://10.0.2.2:3000/").
                //addConverterFactory(GsonConverterFactory.create()).build();
       // Retrofit retrofit=new Retrofit().Builder().baseUrl("http://10.0.2.2:3000/").addCoventryFactory(GsonConverterFactory.create());


                UserApi userAPI=URL.getInstance().create(UserApi.class);
        Call<UserResponse> call=userAPI.checkUser(edtUsername.getText().toString().trim(),edtPassword.getText().toString().trim());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(LoginActivity.this,"incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }
                URL.token+=response.body().getToken();
                startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
Toast.makeText(LoginActivity.this,"error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}