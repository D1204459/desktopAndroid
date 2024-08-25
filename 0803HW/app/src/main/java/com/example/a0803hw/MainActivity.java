package com.example.a0803hw;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        // 餐廳資訊按鈕功能
        ImageButton restaurantButton = findViewById(R.id.imageButton);
        restaurantButton.setOnClickListener(v -> {
            String url = "https://www.thelin.com.tw/%E6%A3%AE%E6%9E%97%E7%99%BE%E5%8C%AF"; // 替換成你要的餐廳網址
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        // 訂位專線按鈕功能
        ImageButton phoneButton = findViewById(R.id.imageButton2);
        phoneButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0422555555")); // 替換成你的餐廳電話號碼
            startActivity(intent);
        });

        // 地圖搜尋按鈕功能
        ImageButton mapButton = findViewById(R.id.imageButton3);
        mapButton.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=407024台中市西屯區朝富路99號"); // 替換成你的餐廳地址
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }
}
