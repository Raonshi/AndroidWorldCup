package com.raon.androidworldcup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.Client;
import com.raon.androidworldcup.Communication.voteDTO;

public class JoinVoteActivity extends AppCompatActivity {
    //UI 객체들
    ImageButton backBtn;
    Button redBtn, blueBtn, giveUpBtn;


    //기타 객체
    private Client client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_vote);
        getSupportActionBar().hide();


        //뒤로가기 버튼
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //빨간버튼 선택
        redBtn = findViewById(R.id.redBtn);
        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 투표의 dto
                voteDTO selected = AppData.Singleton().selectedVoteDTO;
                selected.setVote_item1(selected.getVote_item1() + 1);

                Client client = new Client();
                client.voteDTOCom(selected, "update");






                //DB갱신 완료되면 AppData의 투표DTO삭제
                AppData.Singleton().selectedVoteDTO = null;

                Intent intent = new Intent(getApplicationContext(), ResultVoteActivity.class);
                startActivity(intent);
            }
        });

        //파란 버튼 선택
        blueBtn = findViewById(R.id.blueBtn);
        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 투표의 dto
                voteDTO selected = AppData.Singleton().selectedVoteDTO;
                selected.setVote_item1(selected.getVote_item2() + 1);

                Client client = new Client();
                client.voteDTOCom(selected, "update");




                

                //DB갱신 완료되면 AppData의 투표DTO삭제
                AppData.Singleton().selectedVoteDTO = null;

                Intent intent = new Intent(getApplicationContext(), ResultVoteActivity.class);
                startActivity(intent);
            }
        });

        //기권 버튼 선택
        giveUpBtn = findViewById(R.id.giveUpBtn);
        giveUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //현재 투표의 dto
                voteDTO selected = AppData.Singleton().selectedVoteDTO;
                selected.setVote_item1(selected.getVote_item3() + 1);

                Client client = new Client();
                client.voteDTOCom(selected, "update");


                //DB갱신 완료되면 AppData의 투표DTO삭제
                AppData.Singleton().selectedVoteDTO = null;

                Intent intent = new Intent(getApplicationContext(), ResultVoteActivity.class);
                startActivity(intent);
            }
        });
    }





    private void Vote(int rId){

        switch(rId){
            case R.id.redBtn:

                break;
            case R.id.blueBtn:

                break;
            case R.id.giveUpBtn:

                break;
        }

    }
}
