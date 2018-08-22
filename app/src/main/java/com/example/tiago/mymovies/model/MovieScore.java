package com.example.tiago.mymovies.model;

public class MovieScore {

    private int id;
    private Movie movie;
    private Float actorsScore;
    private Float musicScore;
    private Float durationScore;
    private Float finalStoryScore;
    private Float storyScore;

    public MovieScore(int id, Movie movie, Float actorsScore, Float musicScore, Float durationScore, Float finalStoryScore, Float storyScore) {
        this.id = id;
        this.movie = movie;
        this.actorsScore = actorsScore;
        this.musicScore = musicScore;
        this.durationScore = durationScore;
        this.finalStoryScore = finalStoryScore;
        this.storyScore = storyScore;
    }

    public MovieScore(Movie movie, Float actorsScore, Float musicScore, Float durationScore, Float finalStoryScore, Float storyScore) {
        this.movie = movie;
        this.actorsScore = actorsScore;
        this.musicScore = musicScore;
        this.durationScore = durationScore;
        this.finalStoryScore = finalStoryScore;
        this.storyScore = storyScore;
    }

    public Float getMusicScore() {
        return musicScore;
    }

    public void setMusicScore(Float musicScore) {
        this.musicScore = musicScore;
    }

    public Float getDurationScore() {
        return durationScore;
    }

    public void setDurationScore(Float durationScore) {
        this.durationScore = durationScore;
    }

    public Float getFinalStoryScore() {
        return finalStoryScore;
    }

    public void setFinalStoryScore(Float finalStoryScore) {
        this.finalStoryScore = finalStoryScore;
    }

    public Float getStoryScore() {
        return storyScore;
    }

    public void setStoryScore(Float storyScore) {
        this.storyScore = storyScore;
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Float getActorsScore() {
        return actorsScore;
    }

    public void setActorsScore(Float actorsScore) {
        this.actorsScore = actorsScore;
    }
}
