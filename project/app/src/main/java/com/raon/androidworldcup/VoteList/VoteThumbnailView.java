package com.raon.androidworldcup.VoteList;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.raon.androidworldcup.R;

public class VoteThumbnailView extends FrameLayout {

    Button btn;
    TextView title, date;

    //투표 뷰 객체 생성
    public VoteThumbnailView(Context context){
        super(context);
        Init(context);
    }

    /**
     * 투표 뷰 객체를 레이아웃에 연결
     * @param context 연결할 뷰의 정보
     */
    private void Init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.vote_thumbnail, this, true);

        btn = findViewById(R.id.voteBtn);
        title = findViewById(R.id.voteTitle);
        date = findViewById(R.id.voteDate);
    }

    public void setTitle(String str){
        title.setText(str);
    }

    public void setDate(String str){
        date.setText(str);
    }

}
