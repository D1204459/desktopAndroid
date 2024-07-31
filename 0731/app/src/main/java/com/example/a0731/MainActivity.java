package com.example.a0731;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {

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
    }

    @Override
    public void onClick(View view) {
        TextView tv_size = (TextView) findViewById(R.id.size);
        TextView tv = (TextView) findViewById(R.id.size);
        float size = tv.getTextSize() + 5;
        tv.setTextSize(size);
        tv_size.setText(String.valueOf(size));
    }

    @Override
    public boolean onLongClick(View view) {
        TextView tv_size = (TextView) findViewById(R.id.size);
        TextView tv = (TextView) findViewById(R.id.size);
        float size = 30;
        tv.setTextSize(size);
        tv_size.setText(String.valueOf(size));
        return false;
    }

}