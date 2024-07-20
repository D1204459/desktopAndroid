package com.example.a0717test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TWDeposit extends AppCompatActivity {

    private double deposit; // 假設這是要傳遞的數據

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_twdexchange_currency);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        EditText editText = findViewById(R.id.editTextText4);

        // 設置按鈕的監聽器來處理數據的提交
        View.OnClickListener handleDeposit = v -> {
            try {
                deposit = Double.parseDouble(editText.getText().toString()); // 嘗試從 editText 獲取數字
                Intent resultIntent = new Intent();
                resultIntent.putExtra("TWD", deposit);
                setResult(RESULT_OK, resultIntent);
            } catch (NumberFormatException e) {
                setResult(RESULT_CANCELED); // 如果輸入不是數字，設置結果為取消
            }
            finish(); // 結束當前 Activity，返回 MainActivity
        };

        button8.setOnClickListener(handleDeposit);
        button9.setOnClickListener(handleDeposit);
    }
}