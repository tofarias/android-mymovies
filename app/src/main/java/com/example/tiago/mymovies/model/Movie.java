package com.example.tiago.mymovies.model;

public class Movie {

    private String titleEn;
    private String titlePtBr;
    private int id;

    public Movie(int id, String titleEn, String titlePtBr) {
        this.id = id;
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
    }

    public Movie(String titleEn, String titlePtBr) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
    }

    public int getId() {
        return id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitlePtBr() {
        return titlePtBr;
    }

    public void setTitlePtBr(String titlePtBr) {
        this.titlePtBr = titlePtBr;
    }
}
