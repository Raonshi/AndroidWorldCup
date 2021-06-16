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
import com.raon.androidworldcup.Communication.userDTO;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    //UI 객체들
    EditText idInput, pwInput;
    ImageButton backBtn;
    Button loginBtn;

    //통신 클라이언트 객체
    private UserClient client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        InitView();

        //뒤로가기 버튼
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //임시 로그인 기능
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<userDTO> user;

                boolean idCheck = true;

                AppData.Singleton().id = idInput.getText().toString();
                AppData.Singleton().pw = pwInput.getText().toString();

                client = new UserClient("login");
                client.start();

                //딜레이
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }

                //로그인 성공
                if(AppData.Singleton().isLogin){
                    user = client.getUserList();
                    for(int i = 0; i < user.size(); i++){
                        if(AppData.Singleton().id.equals(user.get(i).getUser_id()) && AppData.Singleton().pw.equals(user.get(i).getUser_pwd())){
                            Toast.makeText(getApplicationContext(), "로그인성공", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
                //로그인 실패
                else{
                    AlertDialog.Builder loginAlert = new AlertDialog.Builder(LoginActivity.this);

                    loginAlert.setTitle("로그인 알림");
                    loginAlert.setMessage("로그인에 실패했습니다.\n아이디와 비밀번호를 확인해주세요.");
                    //로그인 알림 확인 버튼 클릭 액션
                    loginAlert.setPositiveButton("확인", null);
                    loginAlert.show();
                }


            }
        });
    }


    void InitView(){
        idInput = findViewById(R.id.inputId);
        pwInput = findViewById(R.id.inputPw);
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        loginBtn = (Button)findViewById(R.id.loginBtn);
    }
}
