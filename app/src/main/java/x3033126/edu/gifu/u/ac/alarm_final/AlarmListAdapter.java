package x3033126.edu.gifu.u.ac.alarm_final;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class AlarmListAdapter extends RecyclerView.Adapter <AlarmListAdapter.ViewHolder> {
    //データ
    private final List<AlarmClass> localAlarmList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
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
        Log.d("test", localAlarmList.get(position).toString());

        AlarmClass alarm = localAlarmList.get(position);
        int hour = alarm.getHour();
        int min = alarm.getMin();
        viewHolder.getTextView().setText(String.format(Locale.JAPAN, "%2d:%02d", hour, min));
    }

    @Override
    public int getItemCount(){
        return localAlarmList.size();
    }

}
