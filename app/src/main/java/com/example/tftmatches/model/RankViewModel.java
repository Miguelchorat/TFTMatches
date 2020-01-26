package com.example.tftmatches.model;


import android.app.Application;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tftmatches.util.RankQuery;

public class RankViewModel extends AndroidViewModel {

    private static MutableLiveData<Rank> rank;
    private Application application;
    private static final String api_key="PLACEHOLDER";
    private static final String request_url="https://euw1.api.riotgames.com/tft/league/v1/entries/by-summoner/";
    private String id;

    public RankViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<Rank> getRank(){
        if(rank==null){
            rank=new MutableLiveData<>();
            loadRank();
        }
        return rank;
    }

    public void reset(MutableLiveData<Rank> rank){
        this.rank=rank;
    }

    public void setIdSummoner(String id){
        this.id=id;
    }

    private void loadRank(){
        Uri baseUri = Uri.parse(request_url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendEncodedPath(id);
        uriBuilder.appendQueryParameter("api_key",api_key);
        RequestQueue requestQueue= Volley.newRequestQueue(application);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,uriBuilder.toString(),new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Rank r = RankQuery.extractFeatureFromJson(response);
                rank.setValue(r);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error volley",error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }
}
