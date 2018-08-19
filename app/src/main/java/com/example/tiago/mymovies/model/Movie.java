package com.example.tiago.mymovies.model;

public class Movie {

    private String titleEn;
    private String titlePtBr;
    private String comment;
    private int id;
    private Category category;

    public Movie(int id, String titleEn, String titlePtBr, Category category, String comment) {
        this.id = id;
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
        this.category = category;
        this.comment = comment;
    }

    public Movie(String titleEn, String titlePtBr) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
    }

    public Movie(String titleEn, String titlePtBr, Category category, String comment) {
        this.category     = category;
        this.titleEn      = titleEn;
        this.titlePtBr    = titlePtBr;
        this.comment      = comment;
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
}
