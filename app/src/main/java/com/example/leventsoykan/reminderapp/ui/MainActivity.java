package com.example.leventsoykan.reminderapp.ui;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.AlarmClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.leventsoykan.reminderapp.R;
import com.example.leventsoykan.reminderapp.adapters.AlarmAdapter;
import com.example.leventsoykan.reminderapp.helper.AlarmReceiver;
import com.example.leventsoykan.reminderapp.helper.SetNewAlarm;
import com.example.leventsoykan.reminderapp.model.Alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "CHANNEL_ID";
    private Button mAlarmButton;
    private RecyclerView mRecyclerView;
    private AlarmAdapter mAdapter;
    public static List<Alarm> ALARMLIST = new ArrayList<Alarm>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        mAlarmButton = findViewById(R.id.alarmButton);
        mAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetNewAlarm.promptUser(MainActivity.this);
            }
        });

        mRecyclerView = findViewById(R.id.alarmRecycler);
        mAdapter = new AlarmAdapter(this, ALARMLIST);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(llm);


    }

    public void activateAlarm (Alarm alarm){





        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.notification)
                .setContentTitle("Alarm")
                .setContentText(alarm.getDescription())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        //alarmManager.setRepeating(AlarmManager.RTC, alarm.getAlarmTime(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "Notification Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

//    private void promptForTime() {
//
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//        mHour = c.get(Calendar.HOUR_OF_DAY);
//        mMinute = c.get(Calendar.MINUTE);
//
//        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int i, int i1) {
//                mHour = i;
//                mMinute = i1;
//            }
//        }, mHour, mMinute, true);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                mYear = i;
//                mMonth = i1 + 1;
//                mDay = i2;
//                timePickerDialog.show();
//            }
//        }, mYear, mMonth, mDay);
//
//        datePickerDialog.show();
//
//
//
//    }




}

// ** http://www.technotalkative.com/android-datepickerdialog-example/


//Alarm class
//Repeating selection
