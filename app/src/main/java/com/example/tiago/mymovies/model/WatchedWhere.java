package com.example.tiago.mymovies.model;

public class WatchedWhere {

    private int id;
    private String name;

    public WatchedWhere(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public WatchedWhere(int id) {
        this.id = id;
    }

    public WatchedWhere(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
