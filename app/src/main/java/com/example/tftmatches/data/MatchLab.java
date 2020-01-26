package com.example.tftmatches.data;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.example.tftmatches.model.Match;

import java.util.List;

public class MatchLab {

    @SuppressLint("StaticFieldLeak")
    private static MatchLab sMatchLab;

    private MatchDao mMatchDao;

    private MatchLab(Context context){
        Context appContext = context.getApplicationContext();
        MatchDatabase db = Room.databaseBuilder(appContext,MatchDatabase.class,"matches").allowMainThreadQueries().build();
        mMatchDao = db.getMatchDao();
    }

    public static MatchLab get(Context context){
        if(sMatchLab == null){
            sMatchLab = new MatchLab(context);
        }
        return sMatchLab;
    }

    public List<Match> getMatches(){
        return mMatchDao.getMatches();
    }

    public List<Match> getMatchesSearch(String word){
        word = "%"+word+"%";
        return mMatchDao.getMatchesSearch(word);
    }

    public void addMatch(Match match){
        mMatchDao.addMatch(match);
    }

    public void deleteMatch(Match match){
        mMatchDao.deleteMatch(match);
    }
}
