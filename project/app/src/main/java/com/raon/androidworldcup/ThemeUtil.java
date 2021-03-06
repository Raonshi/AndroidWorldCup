package com.raon.androidworldcup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeUtil {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";
    public static final String DEFAULT_MODE = "default";

    /**
     * 테마 설정
     * @param themeColor 적용할 테마 색상
     */
    public static void applyTheme(String themeColor){
        switch(themeColor){
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;

            default:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
        }
    }

    /**
     * 적용한 테마를 저장한다.
     */
    public static void modSave(Context context, String select_mod){
        SharedPreferences sp;
        sp = context.getSharedPreferences("mod", context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mod", select_mod);
        editor.commit();
    }

    /**
     * 적용한 테마를 불러온다.
     * @param context 테마가 적용된 액티비티
     * @return 적용된 테마값의 String값을 return
     */
    public static String modLoad(Context context){
        SharedPreferences sp;
        sp = context.getSharedPreferences("mod", context.MODE_PRIVATE);

        //아무값도 없을 경우 라이트모드 적용
        String loadMod = sp.getString("mod", "light");
        return loadMod;
    }
}
