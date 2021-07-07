package x3033126.edu.gifu.u.ac.alarm_final;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class AlarmSettingActivity extends AppCompatActivity {
    private UtilCommon utilCommon;
    private int selIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_setting_layout);

        //グローバル
        utilCommon = (UtilCommon)getApplication();
        selIndex = getIntent().getIntExtra("INDEX", -1);
        init(); //初期化処理(index決めたあとじゃないと動かない）

        //タイトル
        setTitle(R.string.alarm_setting);

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

            //アラーム追加・編集
            alarmSetting(hour, min);
            finish();
        });
    }

    //初期化処理
    //タイムピッカー設定
    private void init(){
        if(selIndex != -1){  //編集時
            List<AlarmClass> alarmList = utilCommon.getAlarmClassList();
            AlarmClass alarm = alarmList.get(selIndex);

            TimePicker tp = (TimePicker)findViewById(R.id.time_picker);
            tp.setHour(alarm.getHour());
            tp.setMinute(alarm.getMin());
        }
    }

    //アラーム追加・編集
    private void alarmSetting(int hour, int min){
        List<AlarmClass> alarmList = utilCommon.getAlarmClassList();
        if (selIndex == -1){
            alarmList.add(new AlarmClass(hour, min));
        }else{
            AlarmClass alarm = alarmList.get(selIndex);
            alarm.setHour(hour);
            alarm.setMin(min);
        }
    }

}
