package com.raon.androidworldcup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.VoteClient;

public class JoinVoteActivity extends AppCompatActivity {
    //UI 객체들
    ImageButton backBtn;
    Button redBtn, blueBtn, giveUpBtn;
    TextView title;


    //투표 통신 클라이언트 객체
    private VoteClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_vote);
        getSupportActionBar().hide();

        InitView();

        //타이틀 제목 설정
        title.setText(AppData.Singleton().selectedVoteDTO.getVote_title());

        //뒤로가기 버튼
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.Singleton().selectedVoteDTO = null;
                finish();
            }
        });

        //빨간버튼 선택
        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빨간 버튼 클릭 수 1 증가
                int num = AppData.Singleton().selectedVoteDTO.getVote_item1() + 1;
                AppData.Singleton().selectedVoteDTO.setVote_item1(num);

                Vote();
            }
        });

        //파란 버튼 선택
        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //파란 버튼 카운트 1 증가
                int num = AppData.Singleton().selectedVoteDTO.getVote_item2() + 1;
                AppData.Singleton().selectedVoteDTO.setVote_item2(num);

                Vote();
            }
        });

        //기권 버튼 선택
        giveUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //기권표 카운트 1 증가
                int num = AppData.Singleton().selectedVoteDTO.getVote_item3() + 1;
                AppData.Singleton().selectedVoteDTO.setVote_item3(num);

                Vote();
            }
        });


        //만약 현재 투표가 내가만든 투표거나 로그인이 안된 상태라면 투표 불가 알림 출력
        if(AppData.Singleton().selectedVoteDTO.getUser_id().equals(AppData.Singleton().id)){
            AlertDialog.Builder loginAlert = new AlertDialog.Builder(JoinVoteActivity.this);

            loginAlert.setTitle("투표 알림");
            loginAlert.setMessage("본인이 만든 투표는 참가할 수 없습니다!");
            loginAlert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            loginAlert.show();
        }
        else if(!AppData.Singleton().isLogin){
            AlertDialog.Builder loginAlert = new AlertDialog.Builder(JoinVoteActivity.this);

            loginAlert.setTitle("투표 알림");
            loginAlert.setMessage("로그인 후 참가할 수 있습니다!");
            loginAlert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            loginAlert.show();
        }
    }


    void InitView(){
        title = findViewById(R.id.joinVoteTitle);
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        redBtn = findViewById(R.id.redBtn);
        blueBtn = findViewById(R.id.blueBtn);
        giveUpBtn = findViewById(R.id.giveUpBtn);
    }


    /**
     * 투표 실행 메서드
     */
    private void Vote(){
        //투표 실행
        VoteClient client = new VoteClient("join");
        client.start();

        //딜레이
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        if(AppData.Singleton().isJoin){
            Intent intent = new Intent(getApplicationContext(), ResultVoteActivity.class);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder loginAlert = new AlertDialog.Builder(JoinVoteActivity.this);

            loginAlert.setTitle("투표 알림");
            loginAlert.setMessage("투표 실패!\n관리자에게 연락주세요.");
            loginAlert.setPositiveButton("확인", null);
            loginAlert.show();
        }

    }
}
