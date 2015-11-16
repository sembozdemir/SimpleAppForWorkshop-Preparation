package com.sembozdemir.simpleappforworkshop;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewDay1;
    private TextView textViewMonth1;
    private TextView textViewYear1;
    private TextView textViewDay2;
    private TextView textViewMonth2;
    private TextView textViewYear2;
    private LinearLayout layoutDate1;
    private LinearLayout layoutDate2;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog2;
    private TextView textViewResult;
    private DateTime startDate;
    private DateTime endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        startDate = new DateTime();
        endDate = new DateTime().plusDays(1);

        setDateView1(startDate);
        setDateView2(endDate);
        setResultDay(calculate(startDate, endDate));

        initDialogs();
    }

    private void setResultDay(int days) {
        textViewResult.setText(String.valueOf(days));
    }

    private int calculate(DateTime start, DateTime end) {
        return Math.abs(Days.daysBetween(start.toLocalDate(), end.toLocalDate()).getDays());
    }

    private void initDialogs() {
        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // todo: when date is setted
                startDate = new DateTime()
                        .withYear(year)
                        .withMonthOfYear(monthOfYear + 1)
                        .withDayOfMonth(dayOfMonth);
                setDateView1(startDate);
                setResultDay(calculate(startDate, endDate));
            }
        }, startDate.getYear(), startDate.getMonthOfYear() - 1, startDate.getDayOfMonth());

        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // todo: when date2 is setted
                endDate = new DateTime()
                        .withYear(year)
                        .withMonthOfYear(monthOfYear + 1)
                        .withDayOfMonth(dayOfMonth);
                setDateView2(endDate);
                setResultDay(calculate(startDate, endDate));
            }
        }, endDate.getYear(), endDate.getMonthOfYear() - 1, endDate.getDayOfMonth());
    }

    private void setDateView2(DateTime dateTime2) {
        textViewDay2.setText(dateTime2.dayOfMonth().getAsText());
        textViewMonth2.setText(dateTime2.monthOfYear().getAsText());
        textViewYear2.setText(dateTime2.year().getAsText());
    }

    private void setDateView1(DateTime dateTime1) {
        textViewDay1.setText(dateTime1.dayOfMonth().getAsText());
        textViewMonth1.setText(dateTime1.monthOfYear().getAsText());
        textViewYear1.setText(dateTime1.year().getAsText());
    }

    private void initViews() {
        layoutDate1 = (LinearLayout) findViewById(R.id.layout_date1);
        layoutDate1.setOnClickListener(this);
        layoutDate2 = (LinearLayout) findViewById(R.id.layout_date2);
        layoutDate2.setOnClickListener(this);

        textViewDay1 = (TextView) findViewById(R.id.text_view_day1);
        textViewMonth1 = (TextView) findViewById(R.id.text_view_month1);
        textViewYear1 = (TextView) findViewById(R.id.text_view_year1);
        textViewDay2 = (TextView) findViewById(R.id.text_view_day2);
        textViewMonth2 = (TextView) findViewById(R.id.text_view_month2);
        textViewYear2 = (TextView) findViewById(R.id.text_view_year2);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_date1:
                datePickerDialog1.show();
                break;
            case R.id.layout_date2:
                datePickerDialog2.show();
                break;
            default:
                break;
        }
    }
}
