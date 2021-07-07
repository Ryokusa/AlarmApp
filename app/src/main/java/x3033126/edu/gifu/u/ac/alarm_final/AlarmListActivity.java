package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AlarmListActivity extends AppCompatActivity {
    //グローバル
    UtilCommon utilCommon;
    AlarmListAdapter alarmListAdapter; //更新用

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_layout);
        utilCommon = (UtilCommon) getApplication();

        //コンポーネント
       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.alarm_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmListAdapter = new AlarmListAdapter(utilCommon.getAlarmClassList());
        recyclerView.setAdapter(alarmListAdapter);

        //BACKボタン
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(v -> finish());

        //アラーム追加ボタン
        FloatingActionButton addButton = (FloatingActionButton)findViewById(R.id.addAlarmButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AlarmListActivity.this, AlarmSettingActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        alarmListAdapter.notifyDataSetChanged();
    }
}
