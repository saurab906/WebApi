package com.example.webapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webapi.R;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAdd, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAdd=findViewById(R.id.btnAdd);
        btnView=findViewById(R.id.btnView);

        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if ((v.getId() == R.id.btnAdd)) {

            Intent intent = new Intent(DashboardActivity.this, AddTask.class);

            startActivity(intent);

        }

else{
            Intent intent = new Intent(DashboardActivity.this,ViewDetails.class);

            startActivity(intent);

        }
    }
}