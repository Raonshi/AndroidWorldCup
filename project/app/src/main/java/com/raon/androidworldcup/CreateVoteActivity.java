package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.Client;
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

        InitWidget();

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

                //DTO 객체 생성
                voteDTO dto = new voteDTO();
                dto.setVote_title(title);
                dto.setVote_day(date);
                dto.setUser_id(id);

                boolean isSuccess = new Client().voteDTOCom(dto, "insert");;

                if(isSuccess){
                    Toast.makeText(getApplicationContext(), "투표만들기 성공", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "투표만들기 실패", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    void InitWidget(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        inputVoteTitle = (EditText)findViewById(R.id.inputVoteTitle);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        completeBtn = (Button)findViewById(R.id.createVoteCompleteBtn);
    }


}
