package com.example.tiago.mymovies.model;

public class Movie {

    private String titleEn;
    private String titlePtBr;
    private String comment;
    private int id;
    private Category category;
    private String releaseYear;
    private WatchedWhere watchedWhere;

    public Movie(int id, String titleEn, String titlePtBr, String comment, Category category, String releaseYear, WatchedWhere watchedWhere) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
        this.comment = comment;
        this.id = id;
        this.category = category;
        this.releaseYear = releaseYear;
        this.watchedWhere = watchedWhere;
    }

    public Movie(String titleEn, String titlePtBr) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
    }

    public Movie(String titleEn, String titlePtBr, Category category, String comment, String releaseYear, WatchedWhere watchedWhere) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
        this.comment = comment;
        this.id = id;
        this.category = category;
        this.releaseYear = releaseYear;
        this.watchedWhere = watchedWhere;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public WatchedWhere getWatchedWhere() {
        return watchedWhere;
    }

    public void setWatchedWhere(WatchedWhere watchedWhere) {
        this.watchedWhere = watchedWhere;
    }
}
