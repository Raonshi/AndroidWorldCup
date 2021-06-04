/*
메인 액티비티
이 액티비티는 가장 먼저 실행되는 액티비티다.

필요기능
1. 현재 참여 가능한 투표 리스트를 보여줘야함.
2. 투표 리스트는 페이지 전환이 가능해야하며 하나를 클릭하면 해당 투표 페이지로 이동한다.

 */

package com.raon.androidworldcup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.raon.androidworldcup.VoteList.Vote;
import com.raon.androidworldcup.VoteList.VoteListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //위젯 선언
    ImageButton sideBtn;
    LinearLayout sideMenu, sideLogin, sideLogout, sideRegister, sideCreateVote, sideMyVote;
    Button sideBtnLogin, sideBtnLogout, sideBtnRegister, sideBtnCreateVote, sideBtnMyVote;
    RecyclerView voteList;

    //메인화면 투표 목록
    VoteListAdapter adapter;
    ArrayList<Vote> list;

    //로그인 유무
    boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //위젯 Init
        InitWidget();

        //투표 목록 생성
        InitVoteList();

        sideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sideMenu.getVisibility() == View.GONE){
                    sideMenu.setVisibility(View.VISIBLE);
                    
                    if(isLogin == true){
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
                Intent intent = new Intent(getApplicationContext(), CreateVoteActivity.class);
                startActivity(intent);
            }
        });

        //나의 투표 버튼 동작
        sideBtnMyVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyVoteActivity.class);
                startActivity(intent);
            }
        });




        //투표 목록 갱신
        adapter.notifyDataSetChanged();
    }


    void InitWidget(){
        //사이드메뉴
        sideBtn = (ImageButton) findViewById(R.id.sideBtn);

        sideMenu = (LinearLayout)findViewById(R.id.sideMenu);
        sideLogin = (LinearLayout)findViewById(R.id.sideLogin);
        sideLogout = (LinearLayout)findViewById(R.id.sideLogout);
        sideRegister = (LinearLayout)findViewById(R.id.sideRegister);
        sideCreateVote = (LinearLayout)findViewById(R.id.sideCreateVote);
        sideMyVote = (LinearLayout)findViewById(R.id.sideMyVote);

        sideBtnLogin = (Button)findViewById(R.id.sideBtnLogin);
        sideBtnLogout = (Button)findViewById(R.id.sideBtnLogout);
        sideBtnRegister = (Button)findViewById(R.id.sideBtnRegister);
        sideBtnCreateVote = (Button)findViewById(R.id.sideBtnCreateVote);
        sideBtnMyVote = (Button)findViewById(R.id.sideBtnMyVote);

        //투표 목록
        voteList = (RecyclerView)findViewById(R.id.voteList);
    }

    void InitVoteList(){
        list = new ArrayList();
        adapter = new VoteListAdapter(list);

        voteList.setAdapter(adapter);
        voteList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        AddItem(ResourcesCompat.getDrawable(getResources(),R.drawable.baseline_account_circle_18, null), "테스트입니다.1");
        AddItem(ResourcesCompat.getDrawable(getResources(),R.drawable.baseline_account_circle_18, null), "테스트입니다.2");
        AddItem(ResourcesCompat.getDrawable(getResources(),R.drawable.baseline_account_circle_18, null), "테스트입니다.3");
        AddItem(ResourcesCompat.getDrawable(getResources(),R.drawable.baseline_account_circle_18, null), "테스트입니다.4");
    }

    void AddItem(Drawable icon, String title){
        Vote item = new Vote();

        item.setIcon(icon);
        item.setTitle(title);

        list.add(item);
    }
}