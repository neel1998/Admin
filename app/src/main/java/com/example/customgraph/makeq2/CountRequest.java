package com.example.customgraph.makeq2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 04-06-2018.
 */

public class CountRequest extends StringRequest {
    private static final String REQUEST_URL="https://neeltrivedi.000webhostapp.com/count.php";
    private Map<String,String> params;
    public CountRequest(String id, Response.Listener<String> listener){
        super(Request.Method.POST,REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("id",id);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
