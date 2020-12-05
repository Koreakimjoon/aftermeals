package com.example.aftermeals.Storeinfo;  // 제작 가게 정보 - -김준

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
import com.example.aftermeals.List.storelist_japan;
import com.example.aftermeals.R;
import com.example.aftermeals.Store.Store;
import com.example.aftermeals.Store.StoreAdpater;
import com.example.aftermeals.payment.payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class storeinfo_japan extends AppCompatActivity implements View.OnClickListener {

    ImageButton storeinfo_japan_btn_back;
    Button storeinfo_japan_btn_mirror;
    ImageView storeinfo_japan_img_japan1;
    TextView storeinfo_japan_text_storename, storeinfo_japan_text_storenumber;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_japan);

        storeinfo_japan_text_storename = findViewById(R.id.storeinfo_japan_text_storename);
        storeinfo_japan_text_storenumber = findViewById(R.id.storeinfo_japan_text_storenumber);
        storeinfo_japan_img_japan1 = findViewById(R.id.storeinfo_japan_img_japan1);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",4);
        Glide.with(this).load(img).into(storeinfo_japan_img_japan1);
        storeinfo_japan_text_storename.setText(name);
        storeinfo_japan_text_storenumber.setText(number);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();



        storeinfo_japan_btn_mirror = (Button) findViewById(R.id.storeinfo_japan_btn_mirror);
        storeinfo_japan_btn_back = (ImageButton) findViewById(R.id.storeinfo_japan_btn_back);
        findViewById(R.id.storeinfo_japan_btn_choice).setOnClickListener(this);




        storeinfo_japan_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_japan.class);
                startActivity(intent);
            }
        });

        storeinfo_japan_btn_mirror.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_japan_btn_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), payment.class);
                startActivity(intent);
            }
        });



        databaseReference = database.getReference("List_japan/list1/Store_japan1");
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
        databaseReference.child("Choicestore").child("choicejapan").child("choicestore_name").setValue("가츠야");
        databaseReference.child("Choicestore").child("choicejapan").child("choicestore_img").setValue("https://firebasestorage.googleapis.com/v0/b/duproject-a57a3.appspot.com/o/%EC%9D%BC%EC%8B%9D%2F%EA%B0%80%EC%B8%A0%EC%95%BC%2F%EA%B0%80%EC%B8%A0%EC%95%BC.jpg?alt=media&token=32e69a02-5e6c-419f-8a3a-197e962c5208");
        databaseReference.child("Choicestore").child("choicejapan").child("choicestore_number").setValue("0507-1400-5057");
        databaseReference.child("Choicestore").child("choicejapan").child("choicestore_time").setValue("11:00 ~ 20:00");
        databaseReference.child("Choicestore").child("choicejapan").child("choicestore_breaktime").setValue("15:00 ~ 17:00");

        Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
    }
}