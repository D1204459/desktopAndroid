package com.example.a0717test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class USDExchangeCurrency extends AppCompatActivity {
    private String coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usdexchange_currency);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null){
            coin = String.format(bundle.getString("COIN"));

            // set Name
            TextView title = (TextView) findViewById(R.id.title);
            Button tontdButton = (Button) findViewById(R.id.tontd);
            Button ntdtoButton = (Button) findViewById(R.id.ntdto);

            if(coin.equals("USD")){
                title.setText("美金換匯");
                tontdButton.setText("美金換新台幣");
                ntdtoButton.setText("新台幣換美金");
            }else{
                title.setText("日圓換匯");
                tontdButton.setText("日圓換新台幣");
                ntdtoButton.setText("新台幣換日元");
            }
        }
    }

    public void changeCoin(View view){
        EditText et_amount = (EditText) findViewById(R.id.et_amount);
        EditText et_rate = (EditText) findViewById(R.id.et_rate);

        Intent intent = new Intent();

        if (view.getId() == R.id.tontd){
            intent.putExtra("ACTION", "tontd");
        }else{
            intent.putExtra("ACTION", "ntdto");
        }
        intent.putExtra("COIN", coin);
        intent.putExtra("AMOUNT", Double.parseDouble(et_amount.getText().toString()));
        intent.putExtra("RATE", Double.parseDouble(et_rate.getText().toString()));
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}