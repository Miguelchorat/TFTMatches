package com.example.tftmatches.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.tftmatches.model.Summoner;

import org.json.JSONException;
import org.json.JSONObject;

public class SummonerQuery {

    public SummonerQuery(){}

    public static Summoner extractFeatureFromJson(String summonerJSON){

        if(TextUtils.isEmpty(summonerJSON)){
            return null;
        }
        Summoner summoner = null;

        try{
            JSONObject currentSummoner = new JSONObject(summonerJSON);
            String id = currentSummoner.getString("id");
            int icon = currentSummoner.getInt("profileIconId");
            int level = currentSummoner.getInt("summonerLevel");
            String puuid = currentSummoner.getString("puuid");
            String name = currentSummoner.getString("name");
            String accountId = currentSummoner.getString("accountId");
            summoner = new Summoner(id,icon,level,puuid,name,accountId);
        }catch (JSONException e){
            Log.e("SummonerQuerry","Problem parsing the summoner JSON results",e);
            return summoner;
        }

        return summoner;
    }

}
