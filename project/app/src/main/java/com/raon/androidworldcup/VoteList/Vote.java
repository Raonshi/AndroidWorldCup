package com.raon.androidworldcup.VoteList;

import android.graphics.drawable.Drawable;

public class Vote {
    private Drawable icon;
    private String title;

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
