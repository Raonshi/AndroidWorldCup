/*
앱 컨트롤러

1. 로그인, 회원가입 등 액티비티에서 발생하는 모든 결과를 전달받아 서버와 통신한다.
 */

package com.raon.androidworldcup;

public class AppController {

    private static AppController instance = null;

    AppController(){
        System.out.printf("AppController Singleton instance has Created!!");
    }

    public static AppController Singleton(){
        if(instance == null){
            instance = new AppController();
        }
        return instance;
    }
}
