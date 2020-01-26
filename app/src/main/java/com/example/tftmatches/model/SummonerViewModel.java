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
import com.example.tftmatches.util.SummonerQuery;

public class SummonerViewModel extends AndroidViewModel {

    private static MutableLiveData<Summoner> summoner;
    private Application application;
    private static final String api_key="PLACEHOLDER";
    private static final String request_url="https://euw1.api.riotgames.com/tft/summoner/v1/summoners/by-name/";
    private String summonerName;

    public SummonerViewModel(@NonNull Application application){
        super(application);
        this.application=application;
    }

    public LiveData<Summoner> getSummoner(){
        if(summoner==null){
            summoner=new MutableLiveData<>();
            loadSummoner();
        }
        return summoner;
    }

    public void setSummonerName(String summonerName){
        this.summonerName=summonerName;
    }

    public void reset(MutableLiveData<Summoner> summon){
        summoner=summon;
    }

    private void loadSummoner(){

        Uri baseUri = Uri.parse(request_url);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendEncodedPath(summonerName);
        uriBuilder.appendQueryParameter("api_key",api_key);
        RequestQueue requestQueue= Volley.newRequestQueue(application);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,uriBuilder.toString(),new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                Summoner sum = SummonerQuery.extractFeatureFromJson(response);
                summoner.setValue(sum);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error volley",error.toString());
                summoner.setValue(null);
            }
        });

        requestQueue.add(stringRequest);
    }
}
