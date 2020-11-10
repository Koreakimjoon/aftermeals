package com.example.aftermeals;  // 서강호 제작

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class main_bottom extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰 -서강호
    private FragmentManager fm; //프레그먼트 메니저 -서강호
    private FragmentTransaction ft; //프레그먼트 트렌젝션 -서강호
    private HomeActivity activity_main;   // 홈엑티비티로 전환 -서강호
    private home_bottomNavigation_choicestore activity_bottomNavigation_choicestore;  // 찜한가게로 전환 -서강호
    private home_bottomNavigation_post activity_home_bottomNavigation_post; // 예약 내역으로 전환 -서강호
    private home_bottomNavigation_mypage activity_home_bottomNavigation_mypage; // 마이페이지로 전환 -서강호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bottom);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) { // 네비게이션 버튼 순차적으로 클릭시 부여되는 아이템 -서강호
                switch (menuItem.getItemId()) {
                    case R.id.mainbottom_item_home:
                        setFrag(0);
                        break;
                    case R.id.mainbottom_item_choicestore:
                        setFrag(1);
                        break;
                    case R.id.mainbottom_item_post:
                        setFrag(2);
                        break;
                    case R.id.mainbottom_item_mypage:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        activity_main = new HomeActivity();
        activity_bottomNavigation_choicestore = new home_bottomNavigation_choicestore();
        activity_home_bottomNavigation_post = new home_bottomNavigation_post();
        activity_home_bottomNavigation_mypage = new home_bottomNavigation_mypage();
        setFrag(0); // 첫 프래그먼트 화면을 무엇으로 지정해 줄것인지 선택 -서강호

        Intent intent = getIntent();

        String nickname = intent.getStringExtra("username");
        String photoUrl = intent.getStringExtra("photoUrl");


    }

    // 프래그먼트 교체가 일어나는 실행문 -서강호
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); // 프래그먼트 교체시 가지고 와서 트렌잭션 해주는거 -서강호
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, activity_main);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, activity_bottomNavigation_choicestore);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, activity_home_bottomNavigation_post);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, activity_home_bottomNavigation_mypage);
                ft.commit();
                break;
        }
    }
}