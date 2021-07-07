package x3033126.edu.gifu.u.ac.alarm_final;

//アラームの設定を保存するクラス
public class AlarmClass {
    private int hour;
    private int min;

    public AlarmClass(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
