package com.example.customgraph.makeq2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllQuestions extends AppCompatActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_questions);
        home=(Button) findViewById(R.id.home);
        Intent i=getIntent();
        ListView list=(ListView)findViewById(R.id.txt);
        final String response=i.getStringExtra("response");
        ArrayList<QuestionData> questions=new ArrayList<>();
        try {
            JSONArray jsonArray=new JSONArray(response);
            for(int j=0;j<jsonArray.length();j++){
                JSONObject jsonObject=jsonArray.getJSONObject(j);
                String question=jsonObject.getString("question");
                String date=jsonObject.getString("date");
                String id=jsonObject.getString("question_id");
                questions.add(new QuestionData(question,date,id));
            }
            QuestionAdapter questionAdapter=new QuestionAdapter(AllQuestions.this,questions);
            list.setAdapter(questionAdapter);
            Log.d("",""+jsonArray);
        } catch (JSONException e) {
            Log.d("",""+e);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QuestionData current=(QuestionData) adapterView.getItemAtPosition(i);
                String temp_id=current.getid();
                String temp_que=current.getQuestion();
                String temp_date=current.getmDate();
                Intent j=new Intent(AllQuestions.this,SingleQuestion.class);
                j.putExtra("id",temp_id);
                j.putExtra("question",temp_que);
                j.putExtra("date",temp_date);
                j.putExtra("response",response);
                startActivity(j);
            }
            });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AllQuestions.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
