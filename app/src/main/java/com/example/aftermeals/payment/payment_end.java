package com.example.aftermeals.payment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;


import com.example.aftermeals.HomeActivity;
import com.example.aftermeals.R;

public class payment_end extends AppCompatActivity {

    Button payment_end_btn_gohome; // 홈으로 돌아가기 버튼 - 학주

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_end);

        payment_end_btn_gohome = findViewById(R.id.payment_end_btn_gohome);

        // 홈으로 돌아가는 인텐트 - 학주
        payment_end_btn_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}