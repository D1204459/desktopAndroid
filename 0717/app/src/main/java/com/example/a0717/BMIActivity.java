package com.example.a0717;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a0717.R;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void CalBMI(View view){
        EditText et_weight = (EditText) findViewById(R.id.et_weight);
        EditText et_height = (EditText) findViewById(R.id.et_height);

        double w = Double.parseDouble(et_weight.getText().toString());
        double h = Double.parseDouble(et_height.getText().toString());
        double bmi = w/(h*h);

        Intent intent = new Intent();
        intent.putExtra("BMI", bmi);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}