package com.example.aftermeals.Storeinfo;  // 제작 가게 정보 - 서강호

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class storeinfo_korea extends AppCompatActivity implements View.OnClickListener {

    ImageButton storeinfo_korea_btn_back, storeinfo_korea_btn_mirror;
    ImageView storeinfo_korea_img_korea1;
    TextView storeinfo_korea_text_storename, storeinfo_korea_text_storenumber;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference,databaseReferencelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_korea);

        storeinfo_korea_text_storename = findViewById(R.id.storeinfo_korea_text_storename);
        storeinfo_korea_text_storenumber = findViewById(R.id.storeinfo_korea_text_storenumber);
        storeinfo_korea_img_korea1 = findViewById(R.id.storeinfo_korea_img_korea1);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",8);
        Glide.with(this).load(img).into(storeinfo_korea_img_korea1);
        storeinfo_korea_text_storename.setText(name);
        storeinfo_korea_text_storenumber.setText(number);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        findViewById(R.id.storeinfo_korea_btn_choice).setOnClickListener(this);



        storeinfo_korea_btn_mirror = (ImageButton) findViewById(R.id.storeinfo_korea_btn_mirror);
        storeinfo_korea_btn_back = (ImageButton) findViewById(R.id.storeinfo_korea_btn_back);



        storeinfo_korea_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_korea_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_korea_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
            }
        });



        databaseReference = database.getReference("List_korea/list1/Store_korea1");
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
        databaseReference.child("Choicestore").child("choicekorea").child("choicestore_name").setValue("할매순대국");
        databaseReference.child("Choicestore").child("choicekorea").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%ED%95%9C%EC%8B%9D%2F%ED%95%9C%EC%8B%9D1%2F%ED%95%A0%EB%A7%A4%EC%88%9C%EB%8C%80%EA%B5%AD.jpg?alt=media&token=ef2b3c33-16ee-4920-ad90-cf77cb46e0ef");
        databaseReference.child("Choicestore").child("choicekorea").child("choicestore_number").setValue("031-758-6637");
        databaseReference.child("Choicestore").child("choicekorea").child("choicestore_time").setValue("00:00 ~ 24:00");
        databaseReference.child("Choicestore").child("choicekorea").child("choicestore_breaktime").setValue("15:00 ~ 17:00");

        Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
    }
}