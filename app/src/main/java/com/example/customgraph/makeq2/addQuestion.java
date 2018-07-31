package com.example.customgraph.makeq2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class addQuestion extends AppCompatActivity {

    ProgressBar load2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        load2=(ProgressBar)findViewById(R.id.load2);
        load2.setVisibility(View.GONE);
        final EditText eQue=(EditText)findViewById(R.id.que);
        final EditText ed=(EditText)findViewById(R.id.ed);
        final Button add=(Button)findViewById(R.id.abtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load2.setVisibility(View.VISIBLE);
                final String question=eQue.getText().toString();
                final String date=ed.getText().toString();
                final Response.Listener<String> listener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            boolean success=jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(addQuestion.this,"Question Added",Toast.LENGTH_LONG).show();
                                Intent i=new Intent(addQuestion.this,MainActivity.class);
                                startActivity(i);
                                load2.setVisibility(View.GONE);
                            }
                            else{
                                Toast.makeText(addQuestion.this,"Error occured",Toast.LENGTH_LONG).show();
                                load2.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(addQuestion.this,"Error occured in JSON",Toast.LENGTH_LONG).show();
                            Log.d("",""+e);
                            load2.setVisibility(View.GONE);
                        }
                    }
                };
                questionRequest qr=new questionRequest(question,date,listener);
                RequestQueue requestQueue= Volley.newRequestQueue(addQuestion.this);
                requestQueue.add(qr);
            }
        });
    }
}
