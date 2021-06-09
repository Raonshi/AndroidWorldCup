package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    ImageButton backBtn;
    Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        backBtn = (ImageButton)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //임시 로그인 기능
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController.Singleton().isLogin = true;
                Toast.makeText(getApplicationContext(), "로그인성공", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //1. 아이디, 비밀번호 값 받아와야함
        //2. 임시로 로그인 테스트용 아이디, 비밀번호 변수 만들어서 로그인 테스트
        //3. 로그인 성공하면 로그인 액티비티를 종료.


    }
}
