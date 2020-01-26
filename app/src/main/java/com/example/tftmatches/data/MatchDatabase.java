package com.example.tftmatches.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tftmatches.model.Match;

@Database(entities = {Match.class},version = 2,exportSchema = false)
public abstract class MatchDatabase extends RoomDatabase {
    public abstract MatchDao getMatchDao();
}
