package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.Communication.VoteClient;
import com.raon.androidworldcup.Communication.voteDTO;
import com.raon.androidworldcup.VoteThumbnail.VoteThumbnail;
import com.raon.androidworldcup.VoteThumbnail.VoteThumbnailAdapter;

import java.util.ArrayList;

public class MyVoteActivity extends AppCompatActivity {
    ImageButton backBtn;
    GridView voteGrid;

    //투표 리스트
    ArrayList<voteDTO> voteList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vote);
        getSupportActionBar().hide();

        InitView();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

    }

    void InitView(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        voteGrid = findViewById(R.id.mainVoteGrid);
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
            voteGrid.setAdapter(adapter);

            //결과를 어댑터에 저장
            for(int i = 0; i < voteList.size(); i++){
                //DTO리스트에서 DTO객체를 하나씩 호출
                voteDTO dto = voteList.get(i);

                //DTO객체의 id값이 현재 로그인된 계정의 아이디라면
                if(dto.getUser_id().equals(AppData.Singleton().id)){
                    adapter.addItem(dto);
                }
            }
        }
    }
}

