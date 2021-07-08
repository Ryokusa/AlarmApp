package x3033126.edu.gifu.u.ac.alarm_final;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

//グローバル変数
public class UtilCommon extends Application {
    private final List<AlarmClass> alarmClassList = new ArrayList<>();
    private List<Integer> requestCodes = new ArrayList<>();

    @Override
    public void onCreate(){
        super.onCreate();
    }

    //参照型なのでgetオンリー
    public List<AlarmClass> getAlarmClassList() {
        return alarmClassList;
    }

    //アラームセット
    public void setAlarm(int hour, int min){
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), makeRequestCode(), intent, 0);
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

            String text = String.format(Locale.JAPAN, "%d月%d日, %02d:%02dにアラーム設定",
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
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


}
