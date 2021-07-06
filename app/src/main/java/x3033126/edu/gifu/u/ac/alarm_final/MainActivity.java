package x3033126.edu.gifu.u.ac.alarm_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //コンポーネント
        FloatingActionButton nextButton = this.findViewById(R.id.alarmListButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmListActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 「押すとpage1へ戻る」ボタンが押されたときに、2ページ目から1ページ目を表示する。
     */
//    protected void setToPage2_1() {
//        Button buttonToPage2_1 = this.findViewById(R.id.return_button);
//        buttonToPage2_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.activity_main);
//                setToPage1_2();
//            }
//        });
//    }
//
//    /*
//     * 「押したらpage2へ」ボタンが押されたときに1ページ目から2ページ目を表示する。
//     */
//    protected void setToPage1_2() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.alarm_list_layout);
//                setToPage2_1();
//                setToPage2_3();
//            }
//
//        });
//    }
//
//    /*
//     * 「押すとpage2へ戻る」ボタンが押されたときに3ページ目から2ページ目を表示する。
//     */
//    protected void setToPage3_2() {
//        Button buttonToPage3_2 = this.findViewById(R.id.back_Button);
//        buttonToPage3_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.alarm_list_layout);
//                setToPage2_3();
//                setToPage2_1();
//            }
//
//        });
//    }
//
//    /*
//     * 「押したらpage3へ」ボタンが押されたときに、2ページ目から3ページ目を表示する。
//     */
//    protected void setToPage2_3() {
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setContentView(R.layout.alarm_setting_layout);
//                setToPage3_2();
//            }        });
//    }
}