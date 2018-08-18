package com.example.tiago.mymovies.model;

import java.util.List;

public class Movie {

    private String titleEn;
    private String titlePtBr;
    private int id;
    private List<Category> categoryList;

    public Movie(int id, String titleEn, String titlePtBr, List<Category> categoryList) {
        this.id = id;
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
        this.categoryList = categoryList;
    }

    public Movie(String titleEn, String titlePtBr) {
        this.titleEn = titleEn;
        this.titlePtBr = titlePtBr;
    }

    public Movie(String titleEn, String titlePtBr, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.titleEn      = titleEn;
        this.titlePtBr    = titlePtBr;
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

    public List<Category> getCategoryList() {
        return this.categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
