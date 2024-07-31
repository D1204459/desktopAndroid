package com.example.a0731;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        button.setOnLongClickListener(this);

        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main);
        main.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv_size = (TextView) findViewById(R.id.textView);
        TextView tv = (TextView) findViewById(R.id.size);


        float currentSizeSp = tv.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        tv.setTextSize(currentSizeSp + 5);
        tv_size.setText(String.valueOf(currentSizeSp + 5));
    }

    @Override
    public boolean onLongClick(View view) {
        TextView tv_size = (TextView) findViewById(R.id.textView);
        TextView tv = (TextView) findViewById(R.id.size);

        float size = tv.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        tv.setTextSize(size - 5);       //設定文字大小
        tv_size.setText(String.valueOf(size - 5));

        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView tv_action = (TextView) findViewById(R.id.action);
        TextView tv_position = (TextView) findViewById(R.id.position);

        int act = motionEvent.getAction();
        switch (act) {
            case MotionEvent.ACTION_DOWN:
                tv_action.setText("ACTION_DOWN");
                //設定顏色
                tv_action.setTextColor(Color.parseColor("#992233"));
                break;
            case MotionEvent.ACTION_MOVE:
                tv_action.setText("ACTION_MOVE");
                //設定顏色
                tv_action.setTextColor(Color.parseColor("#229933"));
                break;
            case MotionEvent.ACTION_UP:
                tv_action.setText("ACTION_UP");
                //設定顏色
                tv_action.setTextColor(Color.parseColor("#223399"));
                break;
        }

        float x = motionEvent.getX();
        float y = motionEvent.getY();
        tv_position.setText("X: " + motionEvent.getX() + "\n" + " Y: " + motionEvent.getY());
        return true;        //false意思是事件沒有被消耗，還會繼續往下傳遞，true則是消耗事件
    }
}