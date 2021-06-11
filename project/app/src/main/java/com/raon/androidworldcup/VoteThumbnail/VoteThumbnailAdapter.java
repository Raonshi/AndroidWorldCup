package com.raon.androidworldcup.VoteThumbnail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.raon.androidworldcup.AppData;
import com.raon.androidworldcup.Communication.voteDTO;
import com.raon.androidworldcup.JoinVoteActivity;
import com.raon.androidworldcup.LoginActivity;
import com.raon.androidworldcup.R;

import java.util.ArrayList;

public class VoteThumbnailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<VoteThumbnail> thumbnails;

    public VoteThumbnailAdapter(Context context){
        this.context = context;
        this.thumbnails = new ArrayList<VoteThumbnail>();
    }

    /**
     * voteDTO값을 통해 썸네일 정보를 저장
     * @param dto 썸네일 정보를 저장할 DTO 객체
     */
    public void addItem(voteDTO dto){
        VoteThumbnail thumbnail = new VoteThumbnail(dto);
        thumbnails.add(thumbnail);
    }


    @Override
    public int getCount() {
        return thumbnails.size();
    }

    @Override
    public Object getItem(int position) {
        return thumbnails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    //화면 배치할 때 호출되는 메서드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //레이아웃을 호출
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.vote_thumbnail, parent, false);

        //레이아웃에 있는 뷰를 연결
        TextView title = convertView.findViewById(R.id.voteTitle);
        TextView date = convertView.findViewById(R.id.voteDate);
        Button btn = convertView.findViewById(R.id.voteBtn);

        //투표 제목과 기한을 연결
        title.setText(thumbnails.get(position).getTitle());
        date.setText(thumbnails.get(position).getDate());

        //투표 썸네일 클릭시 동작 -> 투표 참가 화면으로 이동해야함.
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택한 투표의 dto를 가져와야함.
                AppData.Singleton().selectedVoteDTO = thumbnails.get(position).getDTO();

                //선택한 투표 참가화면으로 이동
                Intent intent = new Intent(context.getApplicationContext(), JoinVoteActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}