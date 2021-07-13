package x3033126.edu.gifu.u.ac.alarm_final.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import x3033126.edu.gifu.u.ac.alarm_final.R;

public class AlarmActivity extends AppCompatActivity {
    private static final String TAG = "AlarmActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);

        //時間取得
        Intent intent  = getIntent();
        int hour = intent.getIntExtra("hour", 0);
        int min = intent.getIntExtra("min", 0);

        //時間表示
        TextView timeTextView = findViewById(R.id.time_text);
        String timeText = hour + ":" + min;
        timeTextView.setText(timeText);

        //停止ボタン
        Button stopButton = findViewById(R.id.stop_alarm_button);
        stopButton.setOnClickListener((v) -> finish());

        //バイブレーション
        //動作確認はしていない
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vEffect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE);
        vibrator.vibrate(vEffect);

    }
}
