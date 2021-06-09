package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText registerId, registerPw, registerPwRe;
    Button registerIdCheckBtn, registerBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        //뷰 선언
        registerId = findViewById(R.id.registerId);
        registerPw = findViewById(R.id.registerPw);
        registerPwRe = findViewById(R.id.registerPwRe);

        //버튼 액션
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //아이디 체크 버튼
        registerIdCheckBtn = findViewById(R.id.registerIdCheckBtn);
        registerIdCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //회원가입 진행 버튼
        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
