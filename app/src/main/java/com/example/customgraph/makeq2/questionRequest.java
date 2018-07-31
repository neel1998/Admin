package com.example.customgraph.makeq2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 04-06-2018.
 */

public class questionRequest extends StringRequest {
    private static final String URL="https://neeltrivedi.000webhostapp.com/question.php";
    private Map<String,String> params;
    public questionRequest(String question, String date, Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        params=new HashMap<>();
        params.put("question",question);
        params.put("date",date);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
