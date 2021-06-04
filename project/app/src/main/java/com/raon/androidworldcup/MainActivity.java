package com.raon.androidworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton sideBtn;
    LinearLayout sideMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sideBtn = findViewById(R.id.sideBtn);
        sideMenu = findViewById(R.id.sideMenu);

        sideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sideMenu.getVisibility() == View.GONE){
                    sideMenu.setVisibility(View.VISIBLE);
                }
                else{
                    sideMenu.setVisibility(View.GONE);
                }
            }
        });

    }
}