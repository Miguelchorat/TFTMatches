package com.example.tftmatches.model;

import java.io.Serializable;

public class Summoner implements Serializable {

    private String id;
    private int icon;
    private int level;
    private String puuid;
    private String name;
    private String accountId;

    public Summoner() {
    }

    public Summoner(String id, int icon, int level, String puuid, String name, String accountId) {
        this.id = id;
        this.icon = icon;
        this.level = level;
        this.puuid = puuid;
        this.name = name;
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
