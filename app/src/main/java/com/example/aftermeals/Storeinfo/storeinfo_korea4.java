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

public class storeinfo_korea4 extends AppCompatActivity implements View.OnClickListener {

    ImageButton storeinfo_korea4_btn_back, storeinfo_korea4_btn_mirror;
    ImageView storeinfo_korea4_img_korea1;
    TextView storeinfo_korea4_text_storename, storeinfo_korea4_text_storenumber;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference,databaseReferencelist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_korea4);

        storeinfo_korea4_text_storename = findViewById(R.id.storeinfo_korea4_text_storename);
        storeinfo_korea4_text_storenumber = findViewById(R.id.storeinfo_korea4_text_storenumber);
        storeinfo_korea4_img_korea1 = findViewById(R.id.storeinfo_korea4_img_korea1);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",11);
        Glide.with(this).load(img).into(storeinfo_korea4_img_korea1);
        storeinfo_korea4_text_storename.setText(name);
        storeinfo_korea4_text_storenumber.setText(number);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        findViewById(R.id.storeinfo_korea4_btn_choice).setOnClickListener(this);



        storeinfo_korea4_btn_mirror = (ImageButton) findViewById(R.id.storeinfo_korea4_btn_mirror);
        storeinfo_korea4_btn_back = (ImageButton) findViewById(R.id.storeinfo_korea4_btn_back);



        storeinfo_korea4_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_korea4_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_korea4_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
            }
        });



        databaseReference = database.getReference("List_korea/list4/Store_korea4");
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
        databaseReference.child("Choicestore").child("choicekorea4").child("choicestore_name").setValue("백채김치찌개");
        databaseReference.child("Choicestore").child("choicekorea4").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%ED%95%9C%EC%8B%9D%2F%ED%95%9C%EC%8B%9D4%2F%EB%B0%B1%EC%B1%84%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C.jpg?alt=media&token=1a87f47c-fc67-4004-b566-af36373703af");
        databaseReference.child("Choicestore").child("choicekorea4").child("choicestore_number").setValue("031-722-5690");
        databaseReference.child("Choicestore").child("choicekorea4").child("choicestore_time").setValue("11:00 ~ 22:30");
        databaseReference.child("Choicestore").child("choicekorea4").child("choicestore_breaktime").setValue("15:00 ~ 17:00");

        Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
    }
}