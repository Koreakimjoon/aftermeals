package com.example.aftermeals.Menuinfo; //김준

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aftermeals.List.List;
import com.example.aftermeals.List.ListAdapter_china;
import com.example.aftermeals.R;
import com.example.aftermeals.Store.Store;
import com.example.aftermeals.Store.StoreAdpater;
import com.example.aftermeals.Storeinfo.storeinfo_china;
import com.example.aftermeals.Storeinfo.storeinfo_china2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Menuinfo extends AppCompatActivity implements Serializable {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Menu> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ImageView imageView_store;
    private TextView menuinfo_name, menuinfo_info, menuinfo_price,menuinfo_sumprice;
    private ArrayList<Store> arrayList_store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuinfo);

        Button btn_orderButton;
        imageView_store = (ImageView) findViewById(R.id.imageView_store);
        menuinfo_name = (TextView) findViewById(R.id.menuinfo_name);
        menuinfo_info = (TextView) findViewById(R.id.menuinfo_info);
        menuinfo_price = (TextView) findViewById(R.id.menuinfo_price);
        menuinfo_sumprice = (TextView) findViewById(R.id.menuinfo_sumprice);

        btn_orderButton = (Button) findViewById(R.id.btn_orderButton);

        Intent intent = getIntent();
        String img = intent.getStringExtra("menu_img"); //인텐트로 넘어온값을 불러와서 img로 선언
        String name = intent.getStringExtra("menu_name"); //인텐트로 넘어온값을 불러와서 name로 선언
        String info = intent.getStringExtra("menu_info"); //인텐트로 넘어온값을 불러와서 info로 선언
        String price = intent.getStringExtra("menu_price"); //인텐트로 넘어온값을 불러와서 price로 선언
        final int count = intent.getIntExtra("count", 1); //인텐트로 넘어온 menu에 포지션을 불러와서 count로 선언
        int StorearrayList = intent.getIntExtra("StorearrayList", 0); //인텐트로 storeinfo에 포지션값을을 불러와서 storearraylist로 선언



        Glide.with(this).load(img).into(imageView_store);
        menuinfo_name.setText(name); //이름을 선언
        menuinfo_info.setText(info); //정보를 선언
        menuinfo_price.setText(price); //가격을 선언
        menuinfo_sumprice.setText(price);

        btn_orderButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }


        });

        recyclerView = findViewById(R.id.menuinfo_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        switch (StorearrayList){
            case 0:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_china/list1/Store_china1/china/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_china/list1/Store_china1/china" + i + "/menu");
                    }
                }
                break;
            case 1:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_china/list2/Store_china2/china/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_china/list2/Store_china2/china" + i + "/menu");
                    }
                }
                break;
            case 2:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_china/list3/Store_china3/china/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_china/list3/Store_china3/china" + i + "/menu");
                    }
                }
                break;
            case 3:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_china/list4/Store_china4/china/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_china/list4/Store_china4/china" + i + "/menu");
                    }
                }
                break;
            case 4:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_japan/list1/Store_japan1/japan/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_japan/list1/Store_japan1/japan" + i + "/menu");
                    }
                }
                break;
            case 5:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_japan/list2/Store_japan2/japan/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_japan/list2/Store_japan2/japan" + i + "/menu");
                    }
                }
                break;
            case 6:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_japan/list3/Store_japan3/japan/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_japan/list3/Store_japan3/japan" + i + "/menu");
                    }
                }
                break;
            case 7:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_japan/list4/Store_japan4/japan/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_japan/list4/Store_japan4/japan" + i + "/menu");
                    }
                }
                break;
            case 8:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_korea/list1/Store_korea1/korea/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_korea/list1/Store_korea1/korea" + i + "/menu");
                    }
                }
                break;
            case 9:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_korea/list2/Store_korea2/korea/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_korea/list2/Store_korea2/korea" + i + "/menu");
                    }
                }
                break;
            case 10:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_korea/list3/Store_korea3/korea/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_korea/list3/Store_korea3/korea" + i + "/menu");
                    }
                }
                break;
            case 11:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_korea/list4/Store_korea4/korea/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_korea/list4/Store_korea4/korea" + i + "/menu");
                    }
                }
                break;

            case 12:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_west/list1/Store_west1/west/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_west/list1/Store_west1/west" + i + "/menu");
                    }
                }
                break;
            case 13:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_west/list2/Store_west2/west/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_west/list2/Store_west2/west" + i + "/menu");
                    }
                }
                break;
            case 14:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_west/list3/Store_west3/west/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_west/list3/Store_west3/west" + i + "/menu");
                    }
                }
                break;
            case 15:
                for(int i =0; i<=4;i++) {
                    if (count == 0) { //리싸이클러뷰 포지션 0이면 해당 조건문을 실행
                        databaseReference = database.getReference("List_west/list4/Store_west4/west/menu");
                    } else if (count == i) {
                        databaseReference = database.getReference("List_west/list4/Store_west4/west" + i + "/menu");
                    }
                }
                break;


            }




           databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Menu menu = snapshot.getValue(Menu.class);
                    arrayList.add(menu);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity",String.valueOf(databaseError.toException()));

            }
        });

        adapter = new MenuAdpater(arrayList, this);
        recyclerView.setAdapter(adapter);



    }
}