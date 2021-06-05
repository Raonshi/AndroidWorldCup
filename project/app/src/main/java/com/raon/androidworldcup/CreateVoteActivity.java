package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.raon.androidworldcup.ItemList.ItemListAdapter;

public class CreateVoteActivity extends AppCompatActivity {

    //위젯 선언
    private ImageButton backBtn;
    private LinearLayout createVoteLayout1, createVoteLayout2;
    private EditText inputVoteTitle, inputCategory, inputVoteDescription;
    private Spinner tournament;
    private Button createVoteNextBtn, createVoteCompleteBtn;
    private ListView voteListView;
    private ItemListAdapter list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote);
        getSupportActionBar().hide();

        InitWidget();
        InitListView();

        createVoteLayout1.setVisibility(View.VISIBLE);
        createVoteLayout2.setVisibility(View.GONE);

        //리스트뷰 테스트용 더미값
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 1 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 2 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 3 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 4 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 5 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 6 입니다.");
        list.addItem(R.drawable.baseline_account_circle_black_24, "항목 7 입니다.");
        list.notifyDataSetChanged();


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        createVoteNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createVoteLayout1.setVisibility(View.GONE);
                createVoteLayout2.setVisibility(View.VISIBLE);
            }
        });

        createVoteCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "투표 생성 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();


        //어댑터 변경사항 알림
        list.notifyDataSetChanged();
    }


    void InitWidget(){
        backBtn = (ImageButton)findViewById(R.id.backBtn);
        createVoteLayout1 = (LinearLayout)findViewById(R.id.createVoteLayout1);
        createVoteLayout2 = (LinearLayout)findViewById(R.id.createVoteLayout2);
        inputVoteTitle = (EditText)findViewById(R.id.inputVoteTitle);
        inputCategory = (EditText)findViewById(R.id.inputCategory);
        inputVoteDescription = (EditText)findViewById(R.id.inputVoteDescription);
        tournament = (Spinner)findViewById(R.id.tournament);
        createVoteNextBtn = (Button)findViewById(R.id.createVoteNextBtn);
        createVoteCompleteBtn = (Button)findViewById(R.id.createVoteCompleteBtn);

        voteListView = (ListView)findViewById(R.id.voteListView);
    }

    void InitListView(){
        list = new ItemListAdapter();
        voteListView.setAdapter(list);
    }
}
