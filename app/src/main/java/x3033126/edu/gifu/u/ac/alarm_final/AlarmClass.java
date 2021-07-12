package x3033126.edu.gifu.u.ac.alarm_final;

//アラームの設定を保存するクラス
public class AlarmClass {
    private int hour;
    private int min;
    private Boolean enable;

    public AlarmClass(int hour, int min) {
        this.hour = hour;
        this.min = min;
        this.enable = true;
    }

    public AlarmClass(int hour, int min, Boolean enable){
        this(hour, min);
        this.enable = enable;
    }


    public int getHour() {
        return hour;
    }

    public int getMin() { return min; }

    public Boolean getEnable() {
        return enable;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
