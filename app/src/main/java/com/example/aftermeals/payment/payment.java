package com.example.aftermeals.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aftermeals.R;

import org.w3c.dom.Text;

public class payment extends AppCompatActivity {

    TextView payment_textview_price0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment_textview_price0 = findViewById(R.id.payment_textview_price0);

        Intent intent = getIntent();
        String china_pyment  = intent.getStringExtra("china_pyment");

        payment_textview_price0.setText(china_pyment);
    }
}