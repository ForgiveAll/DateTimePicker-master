package com.forgive.picker.samples;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.forgive.picker.HMPicker;
import com.forgive.picker.HMPickerDialog;
import com.forgive.picker.WheelView;

import java.util.ArrayList;
import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity {
    int hour;
    int minute;
  WheelView dateWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateWheelView = (WheelView) findViewById(R.id.date_wheelview);
        initDateWheelView();
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HMPickerDialog(TimePickerActivity.this,
                        new HMPickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(HMPicker view, int hourOfDay, int minuteOfHour) {
                                hour = hourOfDay;
                                minute = minuteOfHour;
                            }
                        },
                        hour, minute, false).show();
            }
        });
        HMPicker timePicker = (HMPicker) findViewById(R.id.timePicker);
        //        timePicker.setIs24HourView(false);
        timePicker.setCurrentHour(20);
        timePicker.setCurrentMinute(45);
        timePicker.setSelectionDivider(new ColorDrawable(0xffff0000));
        timePicker.setSelectionDividerHeight(2);
        timePicker.setEditTextClickable(false);

    }

    private void initDateWheelView() {
        ArrayList dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(i + " : " + i);
        }
        dateWheelView.setOffset(1);
        dateWheelView.setItemPadding(15);
        dateWheelView.setTextSize(80);
        dateWheelView.setItems(dates);
        dateWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
//                dateSelectedIndex = selectedIndex - 1;
//                date.setText(item);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
