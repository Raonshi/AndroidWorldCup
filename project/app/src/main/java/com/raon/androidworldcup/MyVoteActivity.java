package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.voteDTO;

import java.util.ArrayList;

public class MyVoteActivity extends AppCompatActivity {
    ImageButton backBtn;
    ArrayList<voteDTO> myDTOList;
    GridView myVoteGrid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vote);
        getSupportActionBar().hide();

        //TestVote();

        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });



    }

    void CreateVoteDTO(int count){
        /**
         * 투표 DTO를 생성하는 메서드
         */
        for(int i = 0; i < count; i++){
            String title = "testing"+(i+1);
            String id = "tester" + (i+1);
            String date = "2021-06-11";

            voteDTO dto = new voteDTO(title, id, date);

            dto.setVote_item1(20);
            dto.setVote_item2(30);
            dto.setVote_item3(10);

            myDTOList.add(dto);
        }
    }

    void LoadTestVote(ArrayList<voteDTO> myDTOList){
        //myVote에 있는 데이터 가져오기

    }
}

