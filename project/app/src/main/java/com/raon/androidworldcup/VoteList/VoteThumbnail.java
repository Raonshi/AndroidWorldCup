package com.raon.androidworldcup.VoteList;

public class VoteThumbnail {
    private String title, date;

    public VoteThumbnail(String title, String date){
        this.title = title;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }
}
