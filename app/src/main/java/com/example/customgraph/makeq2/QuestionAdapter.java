package com.example.customgraph.makeq2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 04-06-2018.
 */

public class QuestionAdapter extends ArrayAdapter<QuestionData> {
    public QuestionAdapter(Context context,List<QuestionData> questionData){
        super(context,0,questionData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView;
        listView=convertView;
        if (listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.question_layout,parent,false);
        }
        QuestionData current=getItem(position);
        String question=current.getQuestion();
        String date=current.getmDate();
        TextView q=(TextView)listView.findViewById(R.id.q);
        TextView d=(TextView)listView.findViewById(R.id.d);
        q.setText(question);
        d.setText(date);
        return listView;
    }
}
