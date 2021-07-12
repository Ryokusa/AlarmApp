package x3033126.edu.gifu.u.ac.alarm_final;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class AlarmListAdapter extends RecyclerView.Adapter <AlarmListAdapter.ViewHolder> {
    private static final String TAG = "AlarmListAdapter";

    //データ
    private final List<AlarmClass> localAlarmList;
    private AlarmListAdapter.OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        private final Switch aSwitch;
        private final LinearLayout linearLayout;

        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.textView);
            aSwitch = (Switch)v.findViewById(R.id.enable_switch);
            linearLayout = (LinearLayout)v.findViewById(R.id.item_layout);
        }

        public TextView getTextView() {
            return textView;
        }

        public Switch getSwitch() {
            return aSwitch;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }
    }

    public AlarmListAdapter(List<AlarmClass> alarmList){
        localAlarmList = alarmList;
    }

    //ViewHolder作成
    @Override
    public @NonNull ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.alarm_item_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        Log.d(TAG, localAlarmList.get(position).toString());

        AlarmClass alarm = localAlarmList.get(position);
        int hour = alarm.getHour();
        int min = alarm.getMin();

        //LinearLayout
        viewHolder.getLinearLayout().setOnClickListener(v -> listener.onItemClick(viewHolder.getLinearLayout(), position));

        //TextView
        viewHolder.getTextView().setText(String.format(Locale.JAPAN, "%2d:%02d", hour, min));

        //Switch
        viewHolder.getSwitch().setOnCheckedChangeListener((v, checked) -> localAlarmList.get(position).setEnable(checked));
        viewHolder.getSwitch().setChecked(alarm.getEnable());
    }

    @Override
    public int getItemCount(){
        return localAlarmList.size();
    }

    //専用のアイテムクリックリスナー
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    //セットイベントリスナー
    public void setOnItemClickListener(AlarmListAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
