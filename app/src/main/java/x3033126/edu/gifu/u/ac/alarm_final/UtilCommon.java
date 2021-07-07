package x3033126.edu.gifu.u.ac.alarm_final;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class UtilCommon extends Application {
    private List<AlarmClass> alarmClassList = new ArrayList<AlarmClass>();

    //参照型なのでgetオンリー
    public List<AlarmClass> getAlarmClassList() {
        return alarmClassList;
    }
}
