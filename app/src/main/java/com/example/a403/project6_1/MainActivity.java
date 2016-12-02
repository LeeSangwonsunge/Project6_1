package com.example.a403.project6_1;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chronometer cro1;
    Button btn1, btn2;
    RadioButton r1, r2;
    CalendarView cal1;
    TimePicker tim1;
    TextView tv1;
    int yea, mon, day, hou, min;
    int step=0;
    int calcheck=0;
    int timecheck=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cro1 = (Chronometer)findViewById(R.id.chronometer);
        btn1 = (Button)findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        cal1 = (CalendarView)findViewById(R.id.calendarView);
        tim1 = (TimePicker)findViewById(R.id.timePicker);
        tv1 = (TextView)findViewById(R.id.textView2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cro1.setBase(SystemClock.elapsedRealtime());
                cro1.start();
                cro1.setTextColor(Color.parseColor("#FF0000"));
                step=1;
                timecheck=0;
                calcheck=0;
            }
        });
        tim1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hou = hourOfDay;
                min = minute;
                timecheck=1;
            }
        });
        cal1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                yea = year;
                mon = month;
                day = dayOfMonth;
                calcheck=1;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(step==1){
                    if(calcheck!=1) {
                        Toast.makeText(MainActivity.this, "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else if(timecheck!=1) {
                        Toast.makeText(MainActivity.this, "시간을 선택해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        cro1.stop();
                        cro1.setTextColor(Color.parseColor("#0000FF"));
                        tv1.setText(yea + "년" + mon + "월" + day + "일" + hou + "시" + min + "분 예약됨");
                        step = 0;
                    }
                }else{
                    Toast.makeText(MainActivity.this, "예약시작버튼을 누르세요", Toast.LENGTH_SHORT).show();
                }
        }
        });

        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(r1.isChecked()){
                    cal1.setVisibility(View.VISIBLE);
                    tim1.setVisibility(View.INVISIBLE);
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(r2.isChecked()){
                    cal1.setVisibility(View.INVISIBLE);
                    tim1.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
