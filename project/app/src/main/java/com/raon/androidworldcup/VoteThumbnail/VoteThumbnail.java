package com.raon.androidworldcup.VoteThumbnail;

import com.raon.androidworldcup.Communication.voteDTO;

public class VoteThumbnail {
    private String title, id, date;
    private int item1, item2, item3;
    private voteDTO dto;

    //voteDTO에 담긴 정보 저장
    public VoteThumbnail(voteDTO dto){
        this.title = dto.getVote_title();
        this.date = dto.getVote_day();
        this.id = dto.getUser_id();
        this.item1 = dto.getVote_item1();
        this.item2 = dto.getVote_item2();
        this.item3 = dto.getVote_item3();

        this.dto = dto;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public voteDTO getDTO() {
        return dto;
    }

}
