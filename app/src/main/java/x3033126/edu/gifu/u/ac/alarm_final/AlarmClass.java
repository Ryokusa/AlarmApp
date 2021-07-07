package x3033126.edu.gifu.u.ac.alarm_final;

//アラームの設定を保存するクラス
public class AlarmClass {
    private char hour;
    private char min;

    public AlarmClass(char hour, char min) {
        this.hour = hour;
        this.min = min;
    }

    public char getHour() {
        return hour;
    }

    public char getMin() {
        return min;
    }

    public void setHour(char hour) {
        this.hour = hour;
    }

    public void setMin(char min) {
        this.min = min;
    }
}
