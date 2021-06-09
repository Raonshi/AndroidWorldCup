package com.raon.androidworldcup.ItemList;

import android.widget.EditText;
import android.widget.ImageButton;

public class Item {

    private ImageButton icon;
    private String text;

    public void setIcon(ImageButton icon) {
        this.icon = icon;
    }

    public void setText(String text){ this.text = text; }

    public ImageButton getIcon(){
        return this.icon;
    }

    public String getText(){
        return this.text;
    }
}
