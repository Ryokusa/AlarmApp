package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import x3033126.edu.gifu.u.ac.alarm_final.activities.AlarmActivity;
import x3033126.edu.gifu.u.ac.alarm_final.data.ObjectStorage;

//TODO: アンドロイド起動時からブロードキャストレシーバーを稼働させる

public class AlarmReceiver extends BroadcastReceiver {
    private final static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent){
        if (intent.getAction() != null) {
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Log.d(TAG, "boot_completed");
                //スマホ起動時
                setAllAlarm(context);
            }
        }else {
            //アラーム
            Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "received");

            UtilCommon.setAlarm(context, intent.getIntExtra("hour", 0),
                    intent.getIntExtra("min", 0),
                    intent.getIntExtra("id", 0));

            Intent i = new Intent(context, AlarmActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //新しいタスクとして起動

            //TODO: 上の動作をするためには設定から「他アプリの上乗せ許可」が必要なので、設定に誘導できるようにする
            context.startActivity(i);
        }
    }

    //アラーム全設定
    private void setAllAlarm(Context context){
        AlarmClass[] al = ObjectStorage.get("alarm_list", AlarmClass[].class);
        Integer[] rc = ObjectStorage.get("request_codes", Integer[].class);
        if (al != null && rc != null) {
            for (int i = 0; i < al.length; i++) {
                UtilCommon.setAlarm(context, al[i].getHour(), al[i].getMin(), rc[i]);
            }
        }
    }
}
