package com.raon.androidworldcup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.Client;
import com.raon.androidworldcup.Communication.userDTO;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    //UI 객체들
    EditText idInput, pwInput;
    ImageButton backBtn;
    Button loginBtn;

    //기타 객체
    private Client client;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        //정적 UI객체 일괄선언
        //StatelessWidgetInit();
        idInput = findViewById(R.id.inputId);
        pwInput = findViewById(R.id.inputPw);

        //뒤로가기 버튼
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
                finish();
                /*
                ArrayList<userDTO> user;
                boolean idCheck = true;
                String id = idInput.getText().toString();
                String pw = pwInput.getText().toString();

                client = new Client();

                //DB로 보낼 로그인 정보
                userDTO userDTO = new userDTO();
                userDTO.setUser_id(id);
                userDTO.setUser_pwd(pw);

                AppController.Singleton().isLogin = client.userDTOCom(userDTO, "select");

                //로그인 성공
                if(AppController.Singleton().isLogin){
                    user = client.getUserList();

                    for(int i = 0; i < user.size(); i++){
                        if(id.equals(user.get(i).getUser_id()) && pw.equals(user.get(i).getUser_pwd())){
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

                 */
            }
        });






        //1. 아이디, 비밀번호 값 받아와야함
        //2. 임시로 로그인 테스트용 아이디, 비밀번호 변수 만들어서 로그인 테스트
        //3. 로그인 성공하면 로그인 액티비티를 종료.






    }
}
