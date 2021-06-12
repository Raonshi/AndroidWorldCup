package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.VoteClient;
import com.raon.androidworldcup.Communication.voteDTO;

public class CreateVoteActivity extends AppCompatActivity {
    //위젯 선언
    private ImageButton backBtn;
    private EditText inputVoteTitle;
    private Button completeBtn;
    private DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote);
        getSupportActionBar().hide();

        InitView();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //투표제목
                String title = inputVoteTitle.getText().toString();

                //투표 기한
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + month + "-" + day;

                //아이디 가져오기
                String id = AppData.Singleton().id;

                //생성할 투표의 DTO를 AppData에 저장
                //int recognize = 1;
                AppData.Singleton().createVoteDTO = new voteDTO(title, id, 0, 0, 0, date);

                //서버로 Request
                VoteClient client = new VoteClient("create");
                client.start();

                //딜레이
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                //결과 출력
                //투표 생성이 성공한 경우
                if(AppData.Singleton().isCreate){
                    Toast.makeText(getApplicationContext(), "투표 생성 성공", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    AlertDialog.Builder loginAlert = new AlertDialog.Builder(CreateVoteActivity.this);
                    loginAlert.setTitle("투표 생성 알림");
                    loginAlert.setMessage("오류 발생!\n개발자에게 연락해주세요.");
                    //로그인 알림 확인 버튼 클릭 액션
                    loginAlert.setPositiveButton("확인", null);
                    loginAlert.show();
                }
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    void InitView(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        inputVoteTitle = (EditText)findViewById(R.id.inputVoteTitle);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        completeBtn = (Button)findViewById(R.id.createVoteCompleteBtn);
    }


}
