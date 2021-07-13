package x3033126.edu.gifu.u.ac.alarm_final.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import x3033126.edu.gifu.u.ac.alarm_final.AlarmListAdapter;
import x3033126.edu.gifu.u.ac.alarm_final.R;
import x3033126.edu.gifu.u.ac.alarm_final.UtilCommon;

public class AlarmListActivity extends AppCompatActivity {
    //グローバル
    UtilCommon utilCommon;
    AlarmListAdapter alarmListAdapter; //更新用

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_layout);

        //グローバル
        utilCommon = (UtilCommon) getApplication();

        //タイトル
        setTitle(R.string.alarm_setting);

        //コンポーネント
        RecyclerView recyclerView = findViewById(R.id.alarm_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmListAdapter = new AlarmListAdapter(utilCommon.getAlarmClassList());
        recyclerView.setAdapter(alarmListAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        //編集モード
        alarmListAdapter.setOnItemClickListener((v, position) -> startAlarmSettingActivity(position));

        //有効ボタン切り替え
        alarmListAdapter.setOnCheckedChangeListener((v, position, checked) ->{
            utilCommon.getAlarmClassList().get(position).setEnable(checked);
            if(checked){
                utilCommon.setAlarm(position);
            }else{
                utilCommon.resetAlarm(position);
            }
        });

        //BACKボタン
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());

        //アラーム追加ボタン
        FloatingActionButton addButton = findViewById(R.id.addAlarmButton);
        addButton.setOnClickListener(v -> startAlarmSettingActivity());
    }

    //アクティビティ表示時
    @Override
    protected void onResume(){
        super.onResume();
        utilCommon.settingOverlay(this);
        alarmListAdapter.notifyDataSetChanged();    //更新
    }

    //アラーム設定アクティビティ起動
    private void startAlarmSettingActivity(int position){
        Intent intent = new Intent(AlarmListActivity.this, AlarmSettingActivity.class);
        intent.putExtra("INDEX", position);
        startActivity(intent);
    }
    private void startAlarmSettingActivity() {
        startAlarmSettingActivity(-1);
    }
}
