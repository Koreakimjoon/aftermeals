package com.example.aftermeals.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aftermeals.R;

import static com.example.aftermeals.HomeActivity.context;

public class payment extends AppCompatActivity {

    TextView payment_textview_price0;
    TextView payment_textview_number;//김은철 작성
    Button payment_btn_callbutton; //김은철 작성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final String storeNumber; //김은철 작성
//        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics(); //디바이스 화면크기를 구하기위해
//        int width = dm.widthPixels; //디바이스 화면 너비
//        int height = ((DisplayMetrics) dm).heightPixels; //디바이스 화면 높이

        payment_textview_price0 = findViewById(R.id.payment_textview_price0);
        payment_btn_callbutton = (Button)findViewById(R.id.payment_btn_callbutton); //김은철 작성
        payment_textview_number = (TextView)findViewById(R.id.payment_textview_storenumber);//김은철 작성

        storeNumber = (String)payment_textview_number.getText();//김은철 작성
//        dial = (Button) findViewById(R.id.btn);
//        cd = new CostomDialog(this);
//        WindowManager.LayoutParams wm = cd.getWindow().getAttributes();  //다이얼로그의 높이 너비 설정하기위해
//        wm.copyFrom(cd.getWindow().getAttributes()); //여기 설정한 값을 다이얼로그에 넣겠다는 의미
//        wm.width = width / 2;  //화면 너비의 절반
//        wm.height = height / 2;  //화면 높이의 절반
        
//        dial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cd.show();  //다이얼로그
//            }
//        });


        //btn_전화걸기 이벤트
        payment_btn_callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이벤트 동작
                Intent callButton = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+storeNumber));//김은철 작성
                startActivity(callButton);//김은철 작성
            }
        });

        Intent intent = getIntent();
        String china_pyment  = intent.getStringExtra("china_pyment");


        payment_textview_price0.setText(china_pyment);
    }
}