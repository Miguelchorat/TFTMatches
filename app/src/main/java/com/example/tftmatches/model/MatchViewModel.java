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
import com.example.tftmatches.util.MatchQuery;

import java.util.ArrayList;
import java.util.List;

public class MatchViewModel extends AndroidViewModel {

    private String puuid;
    private MutableLiveData<List<Match>> matches;
    private List<String> matchesList;
    private Application application;
    private static final String api_key="PLACEHOLDER    ";
    private static final String request_url_matches="https://europe.api.riotgames.com/tft/match/v1/matches/by-puuid/";
    private static final String request_url_match="https://europe.api.riotgames.com/tft/match/v1/matches/";

    public MatchViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Match>> getMatches(){
        if(matches==null){
            matchesList = new ArrayList<>();
            matches=new MutableLiveData<>();
            loadMatchesList();
        }
        return matches;
    }

    public void setPuuid(String id){
        puuid = "";
        this.puuid=id;
    }

    public void reset(MutableLiveData<List<Match>> matches){
        this.matches=matches;
    }

    private void loadMatchesList(){
        Uri baseUri = Uri.parse(request_url_matches);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendEncodedPath(puuid+"/ids");
        uriBuilder.appendQueryParameter("count","10");
        uriBuilder.appendQueryParameter("api_key",api_key);
        RequestQueue requestQueue= Volley.newRequestQueue(application);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,uriBuilder.toString(),new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                matchesList = MatchQuery.extractFeatureFromJson(response);
                loadMatches();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error volley",error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    public void loadMatches(){
        for (int i=0;i<matchesList.size();i++) {

            Uri baseUri = Uri.parse(request_url_match);
            Uri.Builder uriBuilder = baseUri.buildUpon();
            uriBuilder.appendEncodedPath(matchesList.get(i));
            uriBuilder.appendQueryParameter("api_key", api_key);
            RequestQueue requestQueue = Volley.newRequestQueue(application);

            StringRequest stringRequest = new StringRequest(Request.Method.GET,uriBuilder.toString(),new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    Match match = MatchQuery.extractFeatureFromJson(response,puuid);
                    List<Match> matchesAux = matches.getValue();
                    if(match!=null) {
                        if (matchesAux != null) {
                            matchesAux.add(match);
                            matches.setValue(matchesAux);
                        } else {
                            matchesAux = new ArrayList<>();
                            matchesAux.add(match);
                            matches.setValue(matchesAux);
                        }
                    }
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
}
