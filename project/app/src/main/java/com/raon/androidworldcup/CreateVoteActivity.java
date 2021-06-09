package com.raon.androidworldcup;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;

public class CreateVoteActivity extends AppCompatActivity {

    //위젯 선언
    private ImageButton backBtn, itemImage;
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tournament.setAdapter(adapter);

        createVoteLayout1.setVisibility(View.VISIBLE);
        createVoteLayout2.setVisibility(View.GONE);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        //다음 버튼 클릭 이벤트
        createVoteNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppController.Singleton().title = inputVoteTitle.getText().toString();
                AppController.Singleton().category = inputCategory.getText().toString();
                AppController.Singleton().decription = inputVoteDescription.getText().toString();
                AppController.Singleton().tounament = (int) tournament.getSelectedItemId();

                InitListView();
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
        itemImage = (ImageButton)findViewById(R.id.list_item_image);
        voteListView = (ListView)findViewById(R.id.voteListView);
    }

    void InitListView(){
        list = new ItemListAdapter();
        voteListView.setAdapter(list);

        for(int i = 0; i < (AppController.Singleton().tounament + 1) * 8; i++){
            list.addItem(itemImage, "이 곳에 설명을 작성해주세요.");
        }

        list.notifyDataSetChanged();
    }
}
