package com.example.aftermeals.List; //-김준

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aftermeals.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class storelist_china extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<List> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist_china);

        recyclerView = findViewById(R.id.storelist_recyclerView_china);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();


        databaseReference = database.getReference("List_china"); //데이터베이스인 list_china를 불러옴
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear(); //배열 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    List list = snapshot.getValue(List.class); //데이터베이스에 값을 불러옴
                    arrayList.add(list);  //데이터 베이스로 불러온 값을 어레이리스트에 추가함.
                }
                adapter.notifyDataSetChanged(); //새로고침.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", String.valueOf(databaseError.toException()));

            }
        });

        adapter = new ListAdapter_china(arrayList, this); //어뎁터 선언
        recyclerView.setAdapter(adapter); //어뎁터 연결

    }
}
