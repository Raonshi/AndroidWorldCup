/*
앱 컨트롤러

1. 로그인, 회원가입 등 액티비티에서 발생하는 모든 결과를 전달받아 서버와 통신한다.
 */

package com.raon.androidworldcup;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class AppController {

    //로그인 유무
    public boolean isLogin = false;

    //투표생성 관련 변수
    public String title = null;
    public String category = null;
    public String decription = null;
    public int tounament = 0;


    private static AppController instance = null;
    /*
    AppController(){
        System.out.printf("AppController Singleton instance has Created!!");
    }
    */
    public static AppController Singleton(){
        if(instance == null){
            instance = new AppController();
            System.out.println("AppController Singleton instance has Created!!");
        }
        return instance;
    }
}
