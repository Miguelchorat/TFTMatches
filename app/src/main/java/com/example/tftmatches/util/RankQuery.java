package com.example.tftmatches.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.tftmatches.model.Rank;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankQuery {

    public RankQuery(){}

    public static Rank extractFeatureFromJson(String rankJSON){

        if(TextUtils.isEmpty(rankJSON)){
            return null;
        }
        Rank rank = null;
        try{
            JSONArray rankArrays = new JSONArray(rankJSON);

            for(int i = 0 ; i<rankArrays.length();i++){
                JSONObject currentRank = rankArrays.getJSONObject(i);
                int wins = currentRank.getInt("wins");
                int losses = currentRank.getInt("losses");
                String tier = currentRank.getString("tier");
                String rankQ = currentRank.getString("rank");
                int lp = currentRank.getInt("leaguePoints");
                rank = new Rank(wins,losses,rankQ,lp,tier);
            }

        }catch (JSONException e){
            Log.e("SummonerQuery","Problem parsing the rank JSON results",e);
        }
        return rank;
    }
}
