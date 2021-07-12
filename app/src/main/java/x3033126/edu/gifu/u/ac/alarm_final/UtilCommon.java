package x3033126.edu.gifu.u.ac.alarm_final;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import x3033126.edu.gifu.u.ac.alarm_final.data.ObjectStorage;

//TODO:常駐化

//グローバル変数
public class UtilCommon extends Application {
    private static final String TAG = "UtilCommon";

    private static UtilCommon sInstance;

    private List<AlarmClass> alarmClassList = new ArrayList<>();
    private List<Integer> requestCodes = new ArrayList<>();

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    //参照型なのでgetオンリー
    public List<AlarmClass> getAlarmClassList() {
        return alarmClassList;
    }

    //アラームセット
    public void setAlarm(int hour, int min, int requestCode){
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(am != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, hour);   //時間
            calendar.set(Calendar.MINUTE, min);         //分
            calendar.set(Calendar.SECOND, 0);           //秒
            //過ぎている場合は次の日
            if(calendar.getTimeInMillis() <= System.currentTimeMillis()){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            Log.d(TAG, "" + calendar.getTimeInMillis() + " : " + System.currentTimeMillis());
            //TODO: 繰り返し登録
            //am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);  //セット
            am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5000, pending);  //五秒アラーム


            String text = String.format(Locale.JAPAN, "%d月%d日, %02d:%02dにアラーム設定",
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public void setAlarm(int index){
        int requestCode = requestCodes.get(index);
        removeAlarm(index);
        int hour = alarmClassList.get(index).getHour();
        int min = alarmClassList.get(index).getMin();

        setAlarm(hour, min, requestCode);
    }

    public void setAlarm(int hour, int min){
        setAlarm(hour, min, makeRequestCode());
    }

    //indexのアラーム削除
    public void removeAlarm(int index){
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(),
                requestCodes.get(index), intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        if(am != null){
            am.cancel(pending);
        }
        Log.d("tag", "requestCode="+requestCodes.get(index)+"を削除");
    }

    //アラーム全削除
    public void allRemoveAlarm(){
        for (int i = 0; i < requestCodes.size(); i++){
            removeAlarm(i);
        }
    }

    private int makeRequestCode(){
        //かぶらない乱数コードを生成・追加
        Random random = new Random();
        int value = random.nextInt(100);
        while(requestCodes.contains(value)){
            value = random.nextInt(100);
        }
        this.requestCodes.add(value);
        return value;
    }

    //保存
    public void saveAlarmData(){
        ObjectStorage.save(this.alarmClassList, "alarm_list");
        ObjectStorage.save(this.requestCodes, "request_codes");
    }

    //読み込み
    public Boolean loadAlarmData(){
        AlarmClass[] al = ObjectStorage.get("alarm_list", AlarmClass[].class);
        Integer[] rc = ObjectStorage.get("request_codes", Integer[].class);

        if(al != null && rc != null) {
            alarmClassList = new ArrayList<>(Arrays.asList(al));
            requestCodes = new ArrayList<>(Arrays.asList(rc));
            return true;
        }
        return false;
    }

    //アラームデータ全削除
    public void removeAllAlarmData(){
        ObjectStorage.remove("alarm_list");
        ObjectStorage.remove("request_codes");
    }

    //コンテキストをどこからでも取得できるように
    public static synchronized UtilCommon getInstance() {
        return sInstance;
    }

}
