/*
앱 컨트롤러

- 앱 종료까지 유지되는 객체.
- 로그인 정보 등 앱이 실행되는 동안 유지되야하는 값을 가지고 있다.
 */

package com.raon.androidworldcup;

public class AppData {

    //로그인 유무
    public boolean isLogin = false;

    //현재 로그인된 아이디
    public String id;

    //싱글톤
    private static AppData instance = null;
    public static AppData Singleton(){
        if(instance == null){
            instance = new AppData();
            System.out.println("=====AppController Singleton instance has Created!!=====");
        }
        return instance;
    }
}
