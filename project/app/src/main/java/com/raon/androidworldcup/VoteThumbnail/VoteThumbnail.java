package com.raon.androidworldcup.VoteThumbnail;

import com.raon.androidworldcup.Communication.voteDTO;

public class VoteThumbnail {
    private String title, date;
    private voteDTO dto;

    public VoteThumbnail(voteDTO dto){
        this.title = dto.getVote_title();
        this.date = dto.getVote_day();
        this.dto = dto;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public voteDTO getDto() {
        return dto;
    }
}
