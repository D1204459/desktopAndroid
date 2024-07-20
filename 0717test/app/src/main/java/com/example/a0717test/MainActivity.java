package com.example.a0717test;

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

public class MainActivity extends AppCompatActivity {

    private double twd;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        messageTextView = findViewById(R.id.Success); // 正確獲取用於顯示操作結果的 TextView

        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                            twd = result.getData().getDoubleExtra("TWD", -1);
                            updateUI();
                        } else if (result.getData() != null && result.getResultCode() == Activity.RESULT_CANCELED) {
                            twd = 0;
                            updateUI();
                        }
                    }
                }
        );
    }

    public void GotoTWD(View view) {
        Intent intent = new Intent(this, TWDeposit.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void updateUI() {
        if (twd >= 0) {
            // 操作成功
            messageTextView.setText(String.format("操作成功: %s", twd));
        } else {
            // 操作失敗
            messageTextView.setText("操作失敗");
        }
    }
}