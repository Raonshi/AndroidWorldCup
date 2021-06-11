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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.raon.androidworldcup.Communication.VoteClient;
import com.raon.androidworldcup.Communication.voteDTO;
import com.raon.androidworldcup.VoteThumbnail.VoteThumbnailAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //위젯 선언
    ImageButton sideBtn;
    LinearLayout sideMenu, sideLogin, sideLogout, sideRegister, sideCreateVote, sideMyVote;
    Button sideBtnLogin, sideBtnLogout, sideBtnRegister, sideBtnCreateVote, sideBtnMyVote, themeBtn;

    //투표 리스트
    GridView voteGrid;
    ArrayList<voteDTO> voteList;

    //테마 컬러
    String themeColor;

    //리스트 어댑터
    VoteThumbnailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        AppData.Singleton().Init();

        InitWidget();

        //앱 실행 시 테마값 불러옴
        themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);
        themeBtn.setText("다크모드전환");

        //voteDTO를 통해 DB의 투표 값을 불러옴
        //Test();
        GetVoteList();
        
        
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
                AppData.Singleton().isLogin = false;

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
                if(AppData.Singleton().isLogin){
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
                if(AppData.Singleton().isLogin){
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

        //테마 변경 동작
        themeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(themeColor.equals("light")){
                    themeBtn.setText("라이트모드전환");
                    themeColor = ThemeUtil.DARK_MODE;
                }else if(themeColor.equals("dark")){
                    themeBtn.setText("다크모드전환");
                    themeColor = ThemeUtil.LIGHT_MODE;
                }else{
                    themeBtn.setText("라이트모드전환");
                    themeColor = ThemeUtil.DARK_MODE;
                }
                ThemeUtil.applyTheme(themeColor);
                ThemeUtil.modSave(getApplicationContext(), themeColor);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        WidgetVisible();
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
        themeBtn = (Button)findViewById(R.id.themeBtn);
        voteGrid = findViewById(R.id.mainVoteGrid);

        WidgetVisible();
    }

    /**
     * 사이드 메뉴 위젯 버튼의 Visible을 설정
     */
    void WidgetVisible(){
        //위젯 visible 설정
        if(AppData.Singleton().isLogin){
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

        //초기상태에는 사이드 메뉴 없어야함
        sideMenu.setVisibility(View.GONE);
    }


    /**
     * 테스트용 투표 리스트를 생성 후 화면에 출력한다.
     */
    void Test(){
        /**
         * 테스트용 투표DB연동 메서드
         */
        voteList = new ArrayList<voteDTO>();

        CreateVoteDTO(30);

        //ListAdapter adapter = new ArrayAdapter<voteDTO>(this, android.R.layout.simple_list_item_1, voteList);
        adapter = new VoteThumbnailAdapter(this);
        voteGrid.setAdapter(adapter);

        //테스트 투표정보 입력
        for(int i = 0; i < voteList.size(); i++){
            //DTO리스트에서 DTO객체를 하나씩 호출
            voteDTO dto = voteList.get(i);

            adapter.addItem(dto);
        }
    }


    /**
     * 서버로부터 투표 결과를 받아온 뒤 화면에 출력한다.
     */
    void GetVoteList(){
        //투표 목록 결과를 저장할 리스트
        voteList = new ArrayList<voteDTO>();

        //투표 목록 가지고오기
        VoteClient client = new VoteClient("main");
        client.start();

        //딜레이
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        //결과 값 저장
        if(AppData.Singleton().isMain){
            voteList = client.getVoteList();
            System.out.println("리스트 받아왔다.");
            
            //어댑터를 그리드뷰에 연결
            VoteThumbnailAdapter adapter = new VoteThumbnailAdapter(this);
            voteGrid = findViewById(R.id.mainVoteGrid);
            voteGrid.setAdapter(adapter);
            
            //결과를 어댑터에 저장
            for(int i = 0; i < voteList.size(); i++){
                //DTO리스트에서 DTO객체를 하나씩 호출
                voteDTO dto = voteList.get(i);
                System.out.println("====================>>>>>>>>>>>>>>>>>>>>" + dto.getVote_title());

                adapter.addItem(dto);
            }
        }
    }


    /**
     * 테스트용 dto를 생성하는 메서드
     * @param count 생성할 횟수
     */
    void CreateVoteDTO(int count){
        for(int i = 0; i < count; i++){
            String title = "testing"+(i+1);
            String id = "tester" + (i+1);
            String date = "2021-06-11";

            voteDTO dto = new voteDTO(title, id, date);

            dto.setVote_item1(20);
            dto.setVote_item2(30);
            dto.setVote_item3(10);

            voteList.add(dto);
        }
    }
}