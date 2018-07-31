package com.example.customgraph.makeq2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleQuestion extends AppCompatActivity {

    ProgressBar load3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        load3=(ProgressBar) findViewById(R.id.load3);
        load3.setVisibility(View.VISIBLE);
        Intent i=getIntent();
        final String id=i.getStringExtra("id");
        final String question=i.getStringExtra("question");
        final String date=i.getStringExtra("date");
        final String prev_response=i.getStringExtra("response");
        TextView hq=(TextView)findViewById(R.id.head);
        TextView hd=(TextView)findViewById(R.id.head_date);
        final TextView count=(TextView)findViewById(R.id.count);
        hq.setText(question);
        hd.setText(date);
        Response.Listener<String> listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    boolean success=jsonObject.getBoolean("success");
                    if(success){
                        final Integer yes=jsonObject.getInt("yes");
                        final Integer no=jsonObject.getInt("no");
                        String ans="Total YES="+yes+"\nTotal NO="+no+"";
                        load3.setVisibility(View.GONE);
                        count.setText(ans);
                        PieChart pieChart=(PieChart)findViewById(R.id.piechart);
                        ArrayList<PieEntry> data=new ArrayList<>();
                        data.add(new PieEntry((float)yes,0));
                        data.add(new PieEntry((float)no,1));
                        PieDataSet pieDataSet=new PieDataSet(data,"RESULT");
                         ArrayList<Integer> colors=new ArrayList<>();
                         colors.add(Color.GREEN);
                         colors.add(Color.RED);
                         pieDataSet.setColors(colors);
                         PieData pieData=new PieData(pieDataSet);
                         pieChart.setData(pieData);
                         pieChart.invalidate();

                    }
                    else{
                        load3.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    load3.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        };
        CountRequest countRequest=new CountRequest(id,listener);
        RequestQueue requestQueue= Volley.newRequestQueue(SingleQuestion.this);
        requestQueue.add(countRequest);

    }
}
