package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Alarm Received", Toast.LENGTH_SHORT).show();
        Log.d("tag", "received");
    }
}
