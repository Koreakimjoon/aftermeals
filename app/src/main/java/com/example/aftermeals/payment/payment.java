package com.example.aftermeals.payment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aftermeals.R;
import com.example.aftermeals.main_bottom;

import kr.co.bootpay.Bootpay;
import kr.co.bootpay.BootpayAnalytics;
import kr.co.bootpay.enums.Method;
import kr.co.bootpay.enums.PG;
import kr.co.bootpay.enums.UX;
import kr.co.bootpay.listener.CancelListener;
import kr.co.bootpay.listener.CloseListener;
import kr.co.bootpay.listener.ConfirmListener;
import kr.co.bootpay.listener.DoneListener;
import kr.co.bootpay.listener.ErrorListener;
import kr.co.bootpay.listener.ReadyListener;
import kr.co.bootpay.model.BootExtra;
import kr.co.bootpay.model.BootUser;

import static com.example.aftermeals.HomeActivity.context;


public class payment extends AppCompatActivity {

    TextView payment_textview_price0;
    TextView payment_textview_number;//김은철 작성
    Button payment_btn_callbutton; //김은철 작성
    Button payment_btn_payment;
    TextView payment_textview_address, payment_textview_storenumber;
    private int stuck =10;
    Button payment_togglebtn_btn0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        String china_pyment  = intent.getStringExtra("china_pyment");
        String name = intent.getStringExtra("name");
        final String number = intent.getStringExtra("number");

        final String storeNumber; //김은철 작성

        payment_textview_price0 = findViewById(R.id.payment_textview_price0);
        payment_btn_callbutton = (Button)findViewById(R.id.payment_btn_callbutton); //김은철 작성
        payment_textview_number = (TextView)findViewById(R.id.payment_textview_storenumber);//김은철 작성
        payment_textview_address = findViewById(R.id.payment_textview_address);
        payment_textview_storenumber = findViewById(R.id.payment_textview_storenumber);
        payment_btn_payment = findViewById(R.id.payment_btn_payment);

        storeNumber = (String)payment_textview_number.getText();//김은철 작성


        //btn_전화걸기 이벤트
        payment_btn_callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이벤트 동작
                Intent callButton = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));//김은철 작성
                startActivity(callButton);//김은철 작성
            }
        });



        payment_textview_address.setText(name);
        payment_textview_storenumber.setText(number);



        BootpayAnalytics.init(this, "5fbe68422fa5c2002103750d");
        payment_togglebtn_btn0 =(Button)findViewById(R.id.payment_btn_payment);

        payment_togglebtn_btn0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                // 결제호출
                BootUser bootUser = new BootUser().setPhone("010-1234-5678");
                BootExtra bootExtra = new BootExtra().setQuotas(new int[] {0,2,3});

                Bootpay.init(getFragmentManager())
                        .setApplicationId("5fbe68422fa5c2002103750d") // 해당 프로젝트(안드로이드)의 application id 값
                        .setPG(PG.INICIS) // 결제할 PG 사
                        .setMethod(Method.CARD) // 결제수단
                        .setContext(payment.this)
                        .setBootUser(bootUser)
                        .setBootExtra(bootExtra)
                        .setUX(UX.PG_DIALOG)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
                        .setName("레드착착") // 결제할 상품명
                        .setOrderId("1234") // 결제 고유번호expire_month
                        .setPrice(26000) // 결제할 금액
                        .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                        .onConfirm(new ConfirmListener() { // 결제가 진행되기 바로 직전 호출되는 함수로, 주로 재고처리 등의 로직이 수행
                            @Override
                            public void onConfirm(@Nullable String message) {


                                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                                Log.d("confirm", message);
                            }
                        })
                        .onDone(new DoneListener() { // 결제완료시 호출, 아이템 지급 등 데이터 동기화 로직을 수행합니다
                            @Override
                            public void onDone(@Nullable String message) {
                                Log.d("done", message);
                                Intent intent = new Intent(getApplicationContext(), payment_end.class);
                                startActivity(intent);
                            }
                        })
                        .onReady(new ReadyListener() { // 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
                            @Override
                            public void onReady(@Nullable String message) {
                                Log.d("ready", message);
                            }
                        })
                        .onCancel(new CancelListener() { // 결제 취소시 호출
                            @Override
                            public void onCancel(@Nullable String message) {

                                Log.d("cancel", message);
                            }
                        })
                        .onError(new ErrorListener() { // 에러가 났을때 호출되는 부분
                            @Override
                            public void onError(@Nullable String message) {
                                Log.d("error", message);
                            }
                        })
                        .onClose(
                                new CloseListener() { //결제창이 닫힐때 실행되는 부분
                                    @Override
                                    public void onClose(String message) {

                                        Log.d("close", "close");
                                    }
                                })
                        .request();
            }
        });
    }
}