/*
앱 컨트롤러

- 앱 종료까지 유지되는 객체.
- 로그인 정보 등 앱이 실행되는 동안 유지되야하는 값을 가지고 있다.
 */

package com.raon.androidworldcup;

import android.content.Context;

import com.raon.androidworldcup.Communication.voteDTO;

import java.util.ArrayList;

public class AppData {
    //서버 정보
    public String serverIP = "52.231.66.86";
    public int serverPort = 8000;


    //로그인 유무
    public boolean isLogin = false;
    //아이디체크
    public boolean isCheck = false;
    //회원가입 성공
    public boolean isRegister = false;
    //현재 로그인된 아이디
    public String id;
    public String pw;
    //회원가입
    public String registerId = null;

    //선택한 투표DTO
    public ArrayList<voteDTO> voteList;
    //선택한 투표DTO
    public voteDTO selectedVoteDTO;
    //생성할 투표DTO
    public voteDTO createVoteDTO;
    //메인화면 투표 출력
    public boolean isMain = false;
    //투표 생성 성공 여부
    public boolean isCreate = false;
    //투표 참가 성공 여부
    public boolean isJoin = false;

    //싱글톤
    private static AppData instance = null;
    public static AppData Singleton(){
        if(instance == null){
            instance = new AppData();
            System.out.println("=====AppData Singleton instance has Created!!=====");
        }
        return instance;
    }


    public void Init(){
        System.out.println("==================AppDataSingleton has Created=================");
    }


    public void Reset(){
        isLogin = true;
        isCheck = false;
        isRegister = false;
        selectedVoteDTO = null;
        createVoteDTO = null;
        isMain = false;
        isCreate = false;
        isJoin = false;
    }
}
