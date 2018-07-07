package com.example.leventsoykan.reminderapp.helper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.leventsoykan.reminderapp.model.Alarm;
import com.example.leventsoykan.reminderapp.ui.MainActivity;

import java.util.Calendar;

public class SetNewAlarm {

    public static  int mYear, mMonth, mDay, mHour, mMinute;
    public static long mAlarmTime;
    public static String mDescription;

    public static void promptUser (final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText editText = new EditText(context);
        editText.setHint("Enter description");
        builder.setTitle("Alarm Description")
                .setView(editText);
        builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDescription = editText.getText().toString();

                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);


                        final TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                mHour  = i;
                                mMinute = i1;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(mYear, mMonth, mDay, mHour, mMinute);
                                mAlarmTime = calendar.getTimeInMillis();
                                Alarm alarm = new Alarm(mDescription, mAlarmTime);
                                MainActivity.ALARMLIST.add(alarm);
                            }
                        }, mHour, mMinute, true);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                mYear = i;
                                mMonth = i1;
                                mDay = i2;
                                timePickerDialog.show();
                            }
                        }, mYear, mMonth, mDay);

                        datePickerDialog.show();



                    }
                });
        builder.setNegativeButton("CANCEL", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
