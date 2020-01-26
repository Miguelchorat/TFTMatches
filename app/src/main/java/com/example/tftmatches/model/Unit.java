package com.example.tftmatches.model;

import java.io.Serializable;
import java.util.List;

public class Unit implements Serializable {

    private String id;
    private int tier;
    private String name;
    private List<Integer> items;

    public Unit() {
    }

    public Unit(String id, int tier, String name, List<Integer> items) {
        this.id = id;
        this.tier = tier;
        this.name = name;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public String toString(){
        return name;
    }
}
