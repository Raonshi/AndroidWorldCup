package com.raon.androidworldcup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.VoteClient;

public class ResultVoteActivity extends AppCompatActivity {

    //UI 객체들
    ImageButton backBtn;
    Button homeBtn;
    ProgressBar redRate, blueRate, giveUpRate;
    TextView redRateText, blueRateText, giveUpRateText, resultVoteTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_vote);
        getSupportActionBar().hide();

        InitView();
        VoteRate();

        resultVoteTitle.setText(AppData.Singleton().selectedVoteDTO.getVote_title());

        //뒤로가기 버튼
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //메인메뉴 돌아가기
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                AppData.Singleton().Reset();

                startActivity(intent);
            }
        });
    }


    void InitView(){
        redRate = findViewById(R.id.redRate);
        redRateText = findViewById(R.id.redRateText);

        blueRate = findViewById(R.id.blueRate);
        blueRateText = findViewById(R.id.blueRateText);

        giveUpRate = findViewById(R.id.giveUpRate);
        giveUpRateText = findViewById(R.id.giveUpRateText);

        backBtn = (ImageButton)findViewById(R.id.backBtn);
        homeBtn = (Button)findViewById(R.id.homeBtn);

        resultVoteTitle = (TextView)findViewById(R.id.resultVoteTitle);
    }

    /**
     * 투표율 계산
     */
    void VoteRate(){
        //전체 투표 수

        int m = AppData.Singleton().selectedVoteDTO.getVote_item1() +
                AppData.Singleton().selectedVoteDTO.getVote_item2() +
                AppData.Singleton().selectedVoteDTO.getVote_item3();

        //각 투표별 득표율
        float red = 100.0f * ((float)AppData.Singleton().selectedVoteDTO.getVote_item1() / (float)m);
        float blue = 100.0f * (float)AppData.Singleton().selectedVoteDTO.getVote_item2() / (float)m;
        float giveUp = 100.0f * (float)AppData.Singleton().selectedVoteDTO.getVote_item3() / (float)m;

        //그래프그리기
        redRate.setProgress((int)red);
        blueRate.setProgress((int)blue);
        giveUpRate.setProgress((int)giveUp);

        //텍스트
        redRateText.setText((int)red + "%");
        blueRateText.setText((int)blue + "%");
        giveUpRateText.setText((int)giveUp + "%");
    }
}
