package x3033126.edu.gifu.u.ac.alarm_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import x3033126.edu.gifu.u.ac.alarm_final.R;
import x3033126.edu.gifu.u.ac.alarm_final.UtilCommon;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    UtilCommon utilCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilCommon  = (UtilCommon)getApplication();
        if (utilCommon.loadAlarmData()){ //アラームデータ読み込み
            Log.d(TAG, "アラームデータ読み込み完了");
        }else{
            Log.d(TAG, "アラームデータ読み込み失敗");
        }

        //コンポーネント
        FloatingActionButton nextButton = this.findViewById(R.id.alarmListButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmListActivity.class);
            startActivity(intent);
        });

    }

    //アプリを離れたときにも保存
    @Override
    protected void onPause() {
        super.onPause();
        utilCommon.saveAlarmData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utilCommon.saveAlarmData(); //アラームデータ保存
        //utilCommon.removeAllAlarmData();
        //utilCommon.allRemoveAlarm();
    }
}