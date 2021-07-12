package x3033126.edu.gifu.u.ac.alarm_final.data;

import com.google.gson.Gson;

//Gsonというオブジェクト→JSONライブラリを使用

public class ObjectStorage {

    //保存
    //Json化してSharedPreferenceに保存するだけ
    public static void save(Object src, String key) {
        String json = new Gson().toJson(src);
        new CachePref().put(key, json);
    }

    //取得
    //keyのオブジェを取得
    //存在しない場合はnull
    public static <T> T get(String key, Class<T> klazz) {
        String jsonStr = new CachePref().get(key, "");
        if (jsonStr.equals("")) {
            return null;
        }

        return new Gson().fromJson(jsonStr, klazz);
    }

    public static void remove(String key){
        new CachePref().remove(key);
    }

}