package x3033126.edu.gifu.u.ac.alarm_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class AlarmSettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_setting_layout);

        Button returnButton = (Button)findViewById(R.id.return_button2);
        returnButton.setOnClickListener(v -> finish());

        Button saveButton = (Button) findViewById(R.id.save_Button);
        saveButton.setOnClickListener(v -> {
            // TimePickerインスタンスを取得
            TimePicker tp = (TimePicker) findViewById(R.id.time_picker);

            // 設定時刻の時間,分を取得
            int hour = tp.getHour();
            int min = tp.getMinute();

            TextView textView;
            //textViewを取得
            textView = findViewById(R.id.textView);
            //textViewに選択した時刻を表記
            String str = String.format(Locale.JAPAN, "%2d:%02d", hour, min);
            textView.setText(str);
        });
    }
}
