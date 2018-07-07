package com.example.leventsoykan.reminderapp.model;

import android.app.AlarmManager;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Alarm {
    private String mDescription;
    private long mAlarmTime;

    public Alarm(String description, long alarmTime) {
        mDescription = description;
        mAlarmTime = alarmTime;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getAlarmTime() {
        return mAlarmTime;
    }

    public void setAlarmTime(long alarmTime) {
        mAlarmTime = alarmTime;
    }

    public String getAlarmDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY, kk:mm", Locale.getDefault());
        Date date = new Date(mAlarmTime);
        String dateString = formatter.format(date);
        return dateString;
    }
}
