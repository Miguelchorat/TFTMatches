package com.example.tftmatches.model;

import java.io.Serializable;

public class Rank implements Serializable {

    private int wins;
    private int losses;
    private String tier;
    private String rank;
    private int lp;

    public Rank() {
        this.wins=0;
        this.losses=0;
        this.rank="Unranked";
        this.tier="I";
        this.lp=0;
    }

    public Rank(int wins, int losses, String rank, int lp,String tier) {
        this.wins = wins;
        this.losses = losses;
        this.rank = rank;
        this.lp = lp;
        this.tier=tier;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }
}
