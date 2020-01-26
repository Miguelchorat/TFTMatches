package com.example.tftmatches.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.tftmatches.util.StringTypeConverter;
import com.example.tftmatches.util.UnitTypeConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "matches")

public class Match implements Serializable,Comparable{

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name="placement")
    private int placement;
    @ColumnInfo(name="level")
    private int level;
    @ColumnInfo(name="date")
    private String date;
    @ColumnInfo(name="traits")
    @TypeConverters(StringTypeConverter.class)
    private List<String> traits;
    @ColumnInfo(name="units")
    @TypeConverters(UnitTypeConverter.class)
    private List<Unit> units;
    @Ignore
    private long dateNum;

    public Match(long id, int placement, int level, String date, List<String> traits, List<Unit> units) {
        this.id = id;
        this.placement = placement;
        this.level = level;
        this.date = date;
        this.traits = traits;
        this.units = units;
    }

    @Ignore
    public Match(int placement, int level, String date,long dateNum, List<String> traits, List<Unit> units) {
        this.placement = placement;
        this.level = level;
        this.date = date;
        this.traits = traits;
        this.units = units;
        this.dateNum=dateNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPlacement() {
        return placement;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public List<String> getTraits() {
        return traits;
    }

    public void setTraits(List<String> traits) {
        this.traits = traits;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", placement=" + placement +
                ", level=" + level +
                ", date='" + date + '\'' +
                ", traits=" + traits +
                ", units=" + units +
                '}';
    }

    public long getDateNum() {
        return dateNum;
    }

    public void setDateNum(long dateNum) {
        this.dateNum = dateNum;
    }

    @Override
    public int compareTo(Object o) {
        return (int)((Match) o).getDateNum()-(int)this.dateNum;
    }
}
