package com.example.customgraph.makeq2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 04-06-2018.
 */

public class FetchQuestionsRequest extends StringRequest {
    private static final String URL="https://neeltrivedi.000webhostapp.com/seeAll.php";
    private Map<String,String> params;
    public FetchQuestionsRequest (Response.Listener<String> listener){
        super(Method.POST,URL,listener,null);
        params=new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
