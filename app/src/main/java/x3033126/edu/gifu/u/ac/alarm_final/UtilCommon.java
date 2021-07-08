package x3033126.edu.gifu.u.ac.alarm_final;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//グローバル変数
public class UtilCommon extends Application {
    private final List<AlarmClass> alarmClassList = new ArrayList<>();

    @Override
    public void onCreate(){
        super.onCreate();
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
            calendar.set(Calendar.HOUR_OF_DAY, hour);   //時間
            calendar.set(Calendar.MINUTE, min);         //分
            //過ぎている場合は次の日
            if(calendar.getTimeInMillis() <= System.currentTimeMillis()){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            //TODO: キャンセル動作を考慮
            //am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pending);  //セット

            //TODO: この意味考える
            @SuppressLint("DefaultLocale")
            String text = String.format("%d月%d日, %02d:%02dにアラーム設定",
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

}
