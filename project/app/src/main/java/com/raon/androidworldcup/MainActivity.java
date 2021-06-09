/*
메인 액티비티
이 액티비티는 가장 먼저 실행되는 액티비티다.

필요기능
1. 현재 참여 가능한 투표 리스트를 보여줘야함.
2. 투표 리스트는 페이지 전환이 가능해야하며 하나를 클릭하면 해당 투표 페이지로 이동한다.

 */

package com.raon.androidworldcup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.raon.androidworldcup.Communication.voteDTO;
import com.raon.androidworldcup.VoteList.VoteThumbnail;
import com.raon.androidworldcup.VoteList.VoteThumbnailAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //위젯 선언
    ImageButton sideBtn;
    LinearLayout sideMenu, sideLogin, sideLogout, sideRegister, sideCreateVote, sideMyVote;
    Button sideBtnLogin, sideBtnLogout, sideBtnRegister, sideBtnCreateVote, sideBtnMyVote;

    //투표 리스트
    GridView voteGrid;
    ArrayList<voteDTO> voteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        InitWidget();

        Test();
        
        sideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sideMenu.getVisibility() == View.GONE){
                    sideMenu.setVisibility(View.VISIBLE);
                }
                else{
                    sideMenu.setVisibility(View.GONE);
                }
            }
        });

        //로그인 버튼 동작
        sideBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃 버튼 동작
        sideBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 해제 동작 수행
                AppController.Singleton().isLogin = false;

                WidgetVisible();
            }
        });

        //회원가입 버튼 동작
        sideBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //투표생성 버튼 동작
        sideBtnCreateVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppController.Singleton().isLogin){
                    Intent intent = new Intent(getApplicationContext(), CreateVoteActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder loginAlert = new AlertDialog.Builder(MainActivity.this);

                    loginAlert.setTitle("로그인 알림");
                    loginAlert.setMessage("로그인되어 있지 않습니다!\n로그인 후 이용 바랍니다.");
                    loginAlert.setPositiveButton("확인", null);
                    loginAlert.show();
                }
            }
        });

        //나의 투표 버튼 동작
        sideBtnMyVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppController.Singleton().isLogin){
                    Intent intent = new Intent(getApplicationContext(), MyVoteActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder loginAlert = new AlertDialog.Builder(MainActivity.this);

                    loginAlert.setTitle("로그인 알림");
                    loginAlert.setMessage("로그인되어 있지 않습니다!\n로그인 후 이용 바랍니다.");
                    loginAlert.setPositiveButton("확인", null);
                    loginAlert.show();
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        WidgetVisible();

        //투표 목록 갱신
        //adapter.notifyDataSetChanged();
    }

    /**
     * 현재 액티비티에서 사용할 위젯들을 선언한다.
     * onCreate함수에서 한번만 실행한다.
     */
    void InitWidget(){
        sideMenu = (LinearLayout)findViewById(R.id.sideMenu);
        sideBtn = (ImageButton)findViewById(R.id.sideBtn);
        sideBtnLogin = (Button)findViewById(R.id.sideBtnLogin);
        sideBtnLogout = (Button)findViewById(R.id.sideBtnLogout);
        sideBtnRegister = (Button)findViewById(R.id.sideBtnRegister);
        sideBtnCreateVote = (Button)findViewById(R.id.sideBtnCreateVote);
        sideBtnMyVote = (Button)findViewById(R.id.sideBtnMyVote);
        sideLogin = (LinearLayout)findViewById(R.id.sideLogin);
        sideLogout = (LinearLayout)findViewById(R.id.sideLogout);
        sideRegister = (LinearLayout)findViewById(R.id.sideRegister);
        sideCreateVote = (LinearLayout)findViewById(R.id.sideCreateVote);
        sideMyVote = (LinearLayout)findViewById(R.id.sideMyVote);
        //voteList = (RecyclerView)findViewById(R.id.voteList);

        WidgetVisible();
    }

    void WidgetVisible(){
        //위젯 visible 설정
        if(AppController.Singleton().isLogin){
            sideLogin.setVisibility(View.GONE);
            sideLogout.setVisibility(View.VISIBLE);
            sideRegister.setVisibility(View.GONE);
            sideCreateVote.setVisibility(View.VISIBLE);
            sideMyVote.setVisibility(View.VISIBLE);
        }
        else{
            sideLogin.setVisibility(View.VISIBLE);
            sideLogout.setVisibility(View.GONE);
            sideRegister.setVisibility(View.VISIBLE);
            sideCreateVote.setVisibility(View.VISIBLE);
            sideMyVote.setVisibility(View.GONE);
        }
    }



    void Test(){
        voteList = new ArrayList<voteDTO>();

        CreateVoteDTO(30);

        //ListAdapter adapter = new ArrayAdapter<voteDTO>(this, android.R.layout.simple_list_item_1, voteList);
        VoteThumbnailAdapter adapter = new VoteThumbnailAdapter(this);
        voteGrid = findViewById(R.id.mainVoteGrid);
        voteGrid.setAdapter(adapter);

        //테스트 투표정보 입력
        for(int i = 0; i < voteList.size(); i++){
            //DTO리스트에서 DTO객체를 하나씩 호출
            voteDTO dto = voteList.get(i);

            adapter.addItem(dto);

        }
    }

    void CreateVoteDTO(int count){
        for(int i = 0; i < count; i++){
            voteDTO dto = new voteDTO();
            dto.setVote_title("testing"+(i+1));
            dto.setUser_id("tester" + (i+1));
            dto.setVote_day("2021-06-11");
            dto.setVote_item1(20);
            dto.setVote_item2(30);
            dto.setVote_item3(10);

            voteList.add(dto);
        }
    }
}