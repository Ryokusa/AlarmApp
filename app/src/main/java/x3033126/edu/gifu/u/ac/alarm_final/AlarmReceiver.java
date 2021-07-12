package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private final static String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "received");

        Intent i = new Intent(context, AlarmActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK ); //新しいタスクとして起動
        //TODO: 上の動作をするためには設定から「他アプリの上乗せ許可」が必要なので、設定に誘導できるようにする
        //context.startActivity(i);
    }
}
