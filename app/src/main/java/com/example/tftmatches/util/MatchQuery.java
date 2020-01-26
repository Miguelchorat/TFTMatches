package com.example.tftmatches.util;

import android.text.TextUtils;
import android.util.Log;

import com.example.tftmatches.model.Match;
import com.example.tftmatches.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchQuery {

    public MatchQuery(){}

    public static List<String> extractFeatureFromJson(String matchJSON){
        List<String> matches = new ArrayList<>();

        if(TextUtils.isEmpty(matchJSON)){
            return new ArrayList<>();
        }

        try{
            JSONArray matchArray = new JSONArray(matchJSON);

            for (int i=0;i<matchArray.length();i++){
                String object = matchArray.getString(i);
                matches.add(object);
            }
            return matches;
        }catch (JSONException e){
            Log.e("MatchQuerry","Problem parsing the matches JSON results",e);
        }
        return matches;
    }

    public static Match extractFeatureFromJson(String matchJSON,String id){

        if(TextUtils.isEmpty(matchJSON)){
            return null;
        }

        Match match = null;
        int placement=0;
        int level=0;
        long date =0;
        List<String> traits = new ArrayList<>();
        List<Unit> units = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        try{
            JSONObject matchObject = new JSONObject(matchJSON);

            JSONObject info = matchObject.getJSONObject("info");
            date = info.getLong("game_datetime");
            JSONArray participants = info.getJSONArray("participants");

            for (int i=0;i<participants.length();i++){
                JSONObject participant = participants.getJSONObject(i);

                if (participant.getString("puuid").equals(id)){
                    placement = participant.getInt("placement");
                    level = participant.getInt("level");
                    JSONArray traitArray = participant.getJSONArray("traits");

                    for (int j=0;j<traitArray.length();j++){
                        JSONObject traitObject = traitArray.getJSONObject(j);
                        int tier = traitObject.getInt("tier_current");
                        if(tier!=0){
                            String nameTrait = traitObject.getString("name");
                            traits.add(nameTrait);
                        }
                    }

                    JSONArray unitArray = participant.getJSONArray("units");

                    for (int j=0;j<unitArray.length();j++){
                        JSONObject unitObject = unitArray.getJSONObject(j);
                        String idUnit = unitObject.getString("character_id");
                        int tierUnit = unitObject.getInt("tier");
                        String nameUnit = unitObject.getString("name");
                        if(idUnit.equals("TFT2_Amumu")){
                            nameUnit="Amumu";
                        }
                        if(idUnit.equals("TFT2_Senna")){
                            nameUnit="Senna";
                        }
                        if(idUnit.equals("TFT2_Karma")){
                            nameUnit="Karma";
                        }
                        if(idUnit.equals("TFT2_Leona")){
                            nameUnit="Leona";
                        }
                        List<Integer> items = new ArrayList<>();
                        JSONArray itemArray = unitObject.getJSONArray("items");

                        for (int z = 0;z<itemArray.length();z++){
                            int item = (Integer)itemArray.get(z);
                            items.add(item);
                        }

                        Unit unit = new Unit(idUnit,tierUnit,nameUnit,items);
                        units.add(unit);
                    }
                }
            }
            String dateText = formatter.format(new Date(date));
            System.out.println(dateText);
            match = new Match(placement,level,dateText,date,traits,units);
        }catch (JSONException e){
            Log.e("MatchQuerry","Problem parsing the match JSON results");
            return null;
        }

        return match;
    }
}
