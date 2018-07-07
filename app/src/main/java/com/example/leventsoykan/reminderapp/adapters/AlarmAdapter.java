package com.example.leventsoykan.reminderapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.leventsoykan.reminderapp.R;
import com.example.leventsoykan.reminderapp.model.Alarm;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Alarm> mAlarms;

    public AlarmAdapter(Context context, List<Alarm> alarmList){
        mContext = context;
        mAlarms = alarmList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_item_recycler, viewGroup, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        AlarmViewHolder alarmViewHolder = (AlarmViewHolder) viewHolder;
        alarmViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        TextView mDescriptionText;
        TextView mAlarmDate;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            mDescriptionText = itemView.findViewById(R.id.description);
            mAlarmDate =itemView.findViewById(R.id.alarmDate);
        }
        public void bind (int position){
            Alarm alarm = mAlarms.get(position);
            mDescriptionText.setText(alarm.getDescription());
            mAlarmDate.setText(alarm.getAlarmDate());
        }
    }
}
