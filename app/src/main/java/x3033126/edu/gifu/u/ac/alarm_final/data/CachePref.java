package x3033126.edu.gifu.u.ac.alarm_final.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import x3033126.edu.gifu.u.ac.alarm_final.UtilCommon;

//SharedPreferenceを使いやすくしたもの
//Stringのみを保存する
//jsonは文字列Stringなのでコレを使う

public class CachePref {
    private final static String NAME = "cache";
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    //コンストラクタ
    //SharedPreferenceを生成しておく
    @SuppressLint("CommitPrefEdits")    //ワーニング避け(commitは下でしてるので)
    public CachePref() {
        Context context = UtilCommon.getInstance().getApplicationContext();
        pref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //取得
    public String get(String key, String defaultValue) {
        return pref.getString(key, defaultValue);
    }

    //格納
    public void put(String key, String value) {
        editor.putString(key, value).commit();
    }
    //削除
    public void remove(String key){
        editor.remove(key).commit();
    }
}