package com.example.a0717;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a0717.R;

public class MainActivity extends AppCompatActivity {
    private double bmi;
    private ActivityResultLauncher<Intent> intentActivityResultLanucher;
    private TextView value;
    private TextView description;

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

        intentActivityResultLanucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        // 這裡寫另一個Activity回傳後，獲取資料並處理的地方
                        if(o.getData() != null && o.getResultCode() == Activity.RESULT_OK){
                            bmi = o.getData().getDoubleExtra("BMI", -1);
                            updateUI();
                        }
                    }
                }
        );
    }

    public void GotoCalBMI(View view){
        Intent intent = new Intent(this, BMIActivity.class);

        if(value != null){
            value.setTextColor(Color.parseColor("#000000"));
            description.setTextColor(Color.parseColor("#000000"));
        }

        intentActivityResultLanucher.launch(intent);
    }

    public void updateUI(){
        TextView tv_result = (TextView) findViewById(R.id.Result);
        tv_result.setText(String.valueOf(bmi));

        if(bmi < 18.5){
            value = (TextView) findViewById(R.id.thin_value);
            description = (TextView) findViewById(R.id.thin_description);
        } else if (bmi < 24) {
            value = (TextView) findViewById(R.id.normal_value);
            description = (TextView) findViewById(R.id.normal_description);
        }else if (bmi < 27) {
            value = (TextView) findViewById(R.id.heavy_value);
            description = (TextView) findViewById(R.id.heavy_description);
        }else if (bmi < 30) {
            value = (TextView) findViewById(R.id.littlefat_value);
            description = (TextView) findViewById(R.id.littlefat_description);
        }else if (bmi < 35) {
            value = (TextView) findViewById(R.id.middlefat_value);
            description = (TextView) findViewById(R.id.middlefat_description);
        }else{
            value = (TextView) findViewById(R.id.toofat_value);
            description = (TextView) findViewById(R.id.toofat_description);
        }

        value.setTextColor(Color.parseColor("#CC0000"));
        description.setTextColor(Color.parseColor("#CC0000"));
    }
}