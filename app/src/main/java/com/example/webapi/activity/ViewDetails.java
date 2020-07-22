package com.example.webapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webapi.R;
import com.example.webapi.activity.api.TaskApi;
import com.example.webapi.activity.model.Task;

import java.util.ArrayList;
import java.util.List;

import URl.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewDetails extends AppCompatActivity {

    ListView listView;
    List<String> taskname = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        listView=findViewById(R.id.listView);
loadListView();

    }

    private void loadListView(){
        //Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create()).build();

        TaskApi taskApi=URL.getInstance().create(TaskApi.class);

        Call<List<Task>> call=taskApi.getALLTask(URL. token);
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ViewDetails.this, "code:"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Task> taskList=response.body();

                if(taskList!=null){
                    for(Task t:taskList){
                        taskname.add(t.getName());
                    }

                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(ViewDetails.this,
                            android.R.layout.simple_list_item_1, taskname);

                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {

                Toast.makeText(ViewDetails.this, "error"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


}
