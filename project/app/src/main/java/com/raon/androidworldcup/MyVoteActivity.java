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


        TestVote();



        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });



    }

    void TestVote(){

    }

    void CreateTestVote(){
        //myVoteList에 캐싱
        for(int i = 0; i < 10; i++){
            voteDTO dto = new voteDTO();
            dto.setVote_title("testVote" + (i+1));
            dto.setUser_id("test" + (i+1));
            dto.setVote_day("2021-06-10");
            dto.setVote_item1(10);
            dto.setVote_item2(20);
            dto.setVote_item3(15);

            myDTOList.add(i, dto);
        }
    }

    void LoadTestVote(ArrayList<voteDTO> myDTOList){
        //myVote에 있는 데이터 가져오기

    }
}

