package com.example.aftermeals.Storeinfo;  // 제작 가게 정보 - 서강호

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aftermeals.List.storelist_china;
import com.example.aftermeals.R;
import com.example.aftermeals.Store.Store;
import com.example.aftermeals.Store.StoreAdpater;
import com.example.aftermeals.payment.payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class storeinfo_china3 extends AppCompatActivity implements View.OnClickListener {


    ImageButton storeinfo_china3_btn_back, storeinfo_china3_btn_mirror;
    ImageView storeinfo_china3_img;
    TextView storeinfo_china3_text_storename,storeinfo_china3_text_storenumber;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_china3);

        storeinfo_china3_text_storename = findViewById(R.id.storeinfo_china3_text_storename);
        storeinfo_china3_text_storenumber = findViewById(R.id.storeinfo_china3_text_storenumber);
        storeinfo_china3_img = findViewById(R.id.storeinfo_china_img_china3);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",2);



        Glide.with(this).load(img).into(storeinfo_china3_img);
        storeinfo_china3_text_storename.setText(name);
        storeinfo_china3_text_storenumber.setText(number);

        recyclerView = findViewById(R.id.recyclerView_china3);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        findViewById(R.id.storeinfo_china_btn3_choice).setOnClickListener(this);

        storeinfo_china3_btn_mirror = (ImageButton) findViewById(R.id.storeinfo_china3_btn_mirror);
        storeinfo_china3_btn_back = (ImageButton) findViewById(R.id.storeinfo_china3_btn_back);



        storeinfo_china3_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_china3_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_china3_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
            }
        });

        databaseReference = database.getReference("List_china/list3/Store_china3");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Store store = snapshot.getValue(Store.class);
                    arrayList.add(store);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity",String.valueOf(databaseError.toException()));

            }
        });

        adapter = new StoreAdpater(arrayList, this,Listcount);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        databaseReference = database.getReference();
        databaseReference.child("Choicestore").child("choicechina3").child("choicestore_name").setValue("만다린");
        databaseReference.child("Choicestore").child("choicechina3").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%EC%A4%91%EC%8B%9D%2F%EB%A7%8C%EB%8B%A4%EB%A6%B0%2F%EB%A7%8C%EB%8B%A4%EB%A6%B0.jpg?alt=media&token=ac086a3a-edea-4833-a7f5-609145350235");
        databaseReference.child("Choicestore").child("choicechina3").child("choicestore_number").setValue("050-7779-2710");
        databaseReference.child("Choicestore").child("choicechina3").child("choicestore_time").setValue("10:10 ~ 20:40");
        databaseReference.child("Choicestore").child("choicechina3").child("choicestore_breaktime").setValue("14:30 ~ 15:30");
    }
}