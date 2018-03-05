package com.forgive.picker.samples;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.forgive.picker.DatePicker;
import com.forgive.picker.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

public class DatePickerActivity extends AppCompatActivity {
    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;

    public Date changeDate(Date d, int dateType, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(dateType, num);
        return c.getTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatePicker datePicker = (DatePicker) findViewById(R.id.aa_datePicker);

        //        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        //        Date date = changeDate(new Date(), Calendar.DAY_OF_MONTH, +7);
        long min = System.currentTimeMillis() - 1 * 60 * 1000;
        long max = System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000;

        datePicker.setMinDate(min);
        datePicker.setMaxDate(max);
        datePicker.setSelectionDivider(new ColorDrawable(0xffff0000));
        datePicker.setSelectionDividerHeight(2);


        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DatePickerActivity.this,
                        mDateSetListener,
                        mYear, mMonth, mDay).show();
            }
        });

        mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
        updateDisplay();
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

    private void updateDisplay() {
        mDateDisplay.setText(new StringBuilder()
                .append(mYear).append(" ").append("-")
                // Month is 0 based so add 1
                .append(mMonth + 1).append("-")
                .append(mDay));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            updateDisplay();
        }
    };
}
