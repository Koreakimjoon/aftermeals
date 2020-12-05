package com.example.aftermeals.Storeinfo;  // 제작 가게 정보 -김준

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class storeinfo_west extends AppCompatActivity implements View.OnClickListener {

    ImageButton storeinfo_west_btn_back;
    Button storeinfo_west_btn_mirror;
    ImageView storeinfo_west_img_west1;
    TextView storeinfo_west_text_storename, storeinfo_west_text_storenumber;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference,databaseReferencelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_west);

        storeinfo_west_text_storename = findViewById(R.id.storeinfo_west_text_storename);
        storeinfo_west_text_storenumber = findViewById(R.id.storeinfo_west_text_storenumber);
        storeinfo_west_img_west1 = findViewById(R.id.storeinfo_west_img_west1);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",12);
        Glide.with(this).load(img).into(storeinfo_west_img_west1);
        storeinfo_west_text_storename.setText(name);
        storeinfo_west_text_storenumber.setText(number);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        findViewById(R.id.storeinfo_west_btn_choice).setOnClickListener(this);



        storeinfo_west_btn_mirror = (Button) findViewById(R.id.storeinfo_west_btn_mirror);
        storeinfo_west_btn_back = (ImageButton) findViewById(R.id.storeinfo_west_btn_back);



        storeinfo_west_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_west_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_west_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                intent.putExtra("name",name);
                intent.putExtra("number",number);
                startActivity(intent);
            }
        });



        databaseReference = database.getReference("List_west/list1/Store_west1");
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
        databaseReference.child("Choicestore").child("choicewest").child("choicestore_name").setValue("BBQ 치킨");
        databaseReference.child("Choicestore").child("choicewest").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%EC%B9%98%ED%82%A8%2F%EB%B9%84%EB%B9%84%ED%81%90%2F%EB%B9%84%EB%B9%84%ED%81%90.jpg?alt=media&token=b08b80e2-d959-4eb1-8ed6-5836f3ab7416");
        databaseReference.child("Choicestore").child("choicewest").child("choicestore_number").setValue("050-4830-2028");
        databaseReference.child("Choicestore").child("choicewest").child("choicestore_time").setValue("12:00 ~ 23:00");
        databaseReference.child("Choicestore").child("choicewest").child("choicestore_breaktime").setValue("15:00 ~ 16:00");

        Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
    }
}