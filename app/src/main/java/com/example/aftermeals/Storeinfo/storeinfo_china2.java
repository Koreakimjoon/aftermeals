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

public class storeinfo_china2 extends AppCompatActivity {

    ImageButton storeinfo_china_btn_back2, storeinfo_china_btn_mirror2;
    ImageView storeinfo_china_img2;
    TextView storeinfo_china_text_storename2,storeinfo_china_text_storenumber2;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    public int mycount;
    public static Context china2_context;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeinfo_china2);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String number= intent.getStringExtra("number");
        String img = intent.getStringExtra("img");
        int Listcount = intent.getIntExtra("List_count",1);




        storeinfo_china_text_storename2 = findViewById(R.id.storeinfo_china2_text_storename);
        storeinfo_china_text_storenumber2 = findViewById(R.id.storeinfo_china2_text_storenumber);
        storeinfo_china_img2 = findViewById(R.id.storeinfo_china_img_china2);


        Glide.with(this).load(img).into(storeinfo_china_img2);
        storeinfo_china_text_storename2.setText(name);
        storeinfo_china_text_storenumber2.setText(number);


        recyclerView = findViewById(R.id.recyclerView_china2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        storeinfo_china_btn_mirror2 = (ImageButton) findViewById(R.id.storeinfo_china2_btn_mirror);
        storeinfo_china_btn_back2 = (ImageButton) findViewById(R.id.storeinfo_china2_btn_back);



        storeinfo_china_btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 뒤로가기 버튼 눌러서 뒤로 가게해주는 코드 - 서강호
                Intent intent = new Intent(getApplicationContext(), storelist_china.class);
                startActivity(intent);
            }
        });

        storeinfo_china_btn_mirror2.setClickable(true);  // 오른쪽 상당에 결제하기로 넘어가는 코드 - 서강호
        storeinfo_china_btn_mirror2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), payment.class);
                intent2.putExtra("name",name);
                intent2.putExtra("number",number);
                startActivity(intent2);

            }
        });

        databaseReference = database.getReference("List_china/list2/Store_china2");
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

}