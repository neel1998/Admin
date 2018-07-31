package com.example.customgraph.makeq2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load=(ProgressBar)findViewById(R.id.load);
        load.setVisibility(View.GONE);
        Button add=(Button)findViewById(R.id.addbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                Intent i=new Intent(MainActivity.this,addQuestion.class);
                startActivity(i);
                load.setVisibility(View.GONE);
            }
        });
        Button sq=(Button)findViewById(R.id.sq);
        sq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.setVisibility(View.VISIBLE);
                final Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                                Intent i=new Intent(MainActivity.this,AllQuestions.class);
                                i.putExtra("response",response);
                                startActivity(i);
                                load.setVisibility(View.GONE);
                            }
                };
                FetchQuestionsRequest fetchQuestionsRequest=new FetchQuestionsRequest(listener);
                RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(fetchQuestionsRequest);
            }
        });
    }
}
