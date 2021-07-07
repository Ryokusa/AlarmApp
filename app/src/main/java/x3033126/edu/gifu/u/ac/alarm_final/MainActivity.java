package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //コンポーネント
        FloatingActionButton nextButton = this.findViewById(R.id.alarmListButton);
        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AlarmListActivity.class);
            startActivity(intent);
        });

    }
}