package com.example.aftermeals.Storeinfo;  // 제작 가게 정보 - 서강호

import android.content.Context;
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

public class storeinfo_china extends AppCompatActivity implements View.OnClickListener {

    ImageButton storeinfo_china_btn_back, storeinfo_china_btn_mirror;
    ImageView storeinfo_china_img;
    TextView storeinfo_china_text_storename,storeinfo_china_text_storenumber;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public int mycount;
    public static Context china_context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_china);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",0);


        storeinfo_china_text_storename = findViewById(R.id.storeinfo_china_text_storename);
        storeinfo_china_text_storenumber = findViewById(R.id.storeinfo_china_text_storenumber);
        storeinfo_china_img = findViewById(R.id.storeinfo_china_img_china1);


        Glide.with(this).load(img).into(storeinfo_china_img);
        storeinfo_china_text_storename.setText(name);
       storeinfo_china_text_storenumber.setText(number);


        recyclerView = findViewById(R.id.recyclerView_china);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        findViewById(R.id.storeinfo_china_btn_choice).setOnClickListener(this);

        storeinfo_china_btn_mirror = (ImageButton) findViewById(R.id.storeinfo_china_btn_mirror);
        storeinfo_china_btn_back = (ImageButton) findViewById(R.id.storeinfo_china_btn_back);



        storeinfo_china_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_china_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_china_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
            }
        });

        databaseReference = database.getReference("List_china/list1/Store_china1");
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
        databaseReference.child("Choicestore").child("choicechina").child("choicestore_name").setValue("현짜장");
        databaseReference.child("Choicestore").child("choicechina").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%EC%A4%91%EC%8B%9D%2F%ED%98%84%EC%A7%9C%EC%9E%A5%2F%ED%98%84%EC%A7%9C%EC%9E%A5.jpg?alt=media&token=cf0f85d9-60af-49dc-883b-f63252ab1d3d");
        databaseReference.child("Choicestore").child("choicechina").child("choicestore_number").setValue("050-6518-8188");
        databaseReference.child("Choicestore").child("choicechina").child("choicestore_time").setValue("11:30 ~ 20:30");
        databaseReference.child("Choicestore").child("choicechina").child("choicestore_breaktime").setValue("14:00 ~ 15:00");
    }
}