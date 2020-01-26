package com.example.tftmatches.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tftmatches.model.Match;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM matches")
    List<Match> getMatches();

    @Query("SELECT * FROM matches WHERE traits LIKE :word OR units LIKE :word")
    List<Match> getMatchesSearch(String word);

    @Insert
    void addMatch(Match match);

    @Delete
    void deleteMatch(Match match);
}
