package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.UserClient;

public class RegisterActivity extends AppCompatActivity {
    EditText registerId, registerPw, registerPwRe;
    Button registerIdCheckBtn, registerBtn;
    ImageButton backBtn;

    //통신 클라이언트 객체
    private UserClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();


        //뷰 선언
        InitView();

        //버튼 액션
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //아이디 체크 버튼
        registerIdCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.Singleton().registerId = registerId.getText().toString();

                client = new UserClient("idCheck");
                client.start();

                //딜레이
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                //아이디 중복 안될 경우
                if(AppData.Singleton().isCheck){
                    Toast.makeText(getApplicationContext(), "아이디 사용 가능", Toast.LENGTH_SHORT).show();
                }
                //중복될 경우
                else{
                    AlertDialog.Builder loginAlert = new AlertDialog.Builder(RegisterActivity.this);
                    loginAlert.setTitle("로그인 알림");
                    loginAlert.setMessage("아이디가 중복됩니다.\n아이디를 다시 입력해주세요.");
                    //로그인 알림 확인 버튼 클릭 액션
                    loginAlert.setPositiveButton("확인", null);
                    loginAlert.show();
                }
            }
        });

        //회원가입 진행 버튼
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppData.Singleton().isCheck == false){
                    Toast.makeText(getApplicationContext(), "아이디 체크를 진행해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                AppData.Singleton().id = registerId.getText().toString();
                AppData.Singleton().pw = registerPw.getText().toString();

                client = new UserClient("register");
                client.start();

                //딜레이
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                //회원가입이 성공
                if(AppData.Singleton().isRegister){
                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }


    void InitView(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        registerId = findViewById(R.id.registerId);
        registerPw = findViewById(R.id.registerPw);
        registerPwRe = findViewById(R.id.registerPwRe);
        registerIdCheckBtn = findViewById(R.id.registerIdCheckBtn);
        registerBtn = findViewById(R.id.registerBtn);
    }
}
