
package com.example.aftermeals;  // 서강호 제작

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.aftermeals.List.storelist_china;
import com.example.aftermeals.List.storelist_japan;
import com.example.aftermeals.List.storelist_korea;
import com.example.aftermeals.List.storelist_west;

public class HomeActivity extends Fragment {
    private View view;  // 서강호 제작
    public static Context context; //하성빈 컨텍스트 베너값 설정
    public static double lon; //베너값 x좌표
    public static double lat; //배너값 y좌표
    ViewFlipper v_fllipper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {  // 서강호 제작
        view = inflater.inflate(R.layout.activity_main, container, false);

        final ImageButton main_grid_img0, main_grid_img1, main_grid_img2, main_grid_img3, main_grid_img4, main_grid_img5, main_grid_img6, main_grid_img7;

        main_grid_img0 = (ImageButton) view.findViewById(R.id.main_grid_img0); //main_grid_img0 버튼을 이미지버튼으로 초기화 -준
        main_grid_img1 = (ImageButton) view.findViewById(R.id.main_grid_img1);
        main_grid_img2 = (ImageButton) view.findViewById(R.id.main_grid_img2);
        main_grid_img3 = (ImageButton) view.findViewById(R.id.main_grid_img3); /*
        main_grid_img4 = (ImageButton) view.findViewById(R.id.main_grid_img4);
        main_grid_img5 = (ImageButton) view.findViewById(R.id.main_grid_img5);  하단4개 -김준
        main_grid_img6 = (ImageButton) view.findViewById(R.id.main_grid_img6);
        main_grid_img7 = (ImageButton) view.findViewById(R.id.main_grid_img7); */

        main_grid_img0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //main_gid_img0 클릭시 다음화면으로 넘어감 -준
                Intent intent = new Intent(getActivity(), storelist_korea.class);
                startActivity(intent);
            }
        });

        main_grid_img1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //main_gid_img1 클릭시 다음화면으로 넘어감 -준
                Intent intent = new Intent(getActivity(), storelist_japan.class);
                startActivity(intent);
            }
        });


        main_grid_img2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //main_gid_img2 클릭시 다음화면으로 넘어감 -준
                Intent intent = new Intent(getActivity(), storelist_china.class);
                startActivity(intent);
            }
        });


        main_grid_img3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { //main_gid_img3 클릭시 다음화면으로 넘어감 -준
                Intent intent = new Intent(getActivity(), storelist_west.class);
                startActivity(intent);
            }
        });




        context = getContext(); //하성빈 맵관련된.

        lat = 37.505620; //하성빈 경도 좌표
        lon = 126.7088113;  //하성빈 위도 좌표

        //ViewPager banner = (ViewPager) view.findViewById(R.id.main_viewPager_banner); //하성빈 배너 좌표
        //ViewpagerAdapter adapter = new ViewpagerAdapter(getContext()); //하성빈 어뎁터
        //banner.setAdapter(adapter); //하성빈 어뎁터 연결

        int images[] = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g
        };

        v_fllipper = view.findViewById(R.id.main_vf_banner);

        for(int image : images) {
            fllipperImages(image);
        }


        return view;  // 서강호 제작
    }

    private void fllipperImages(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        v_fllipper.addView(imageView);      // 이미지 추가
        v_fllipper.setFlipInterval(3000);       // 자동 이미지 슬라이드 딜레이시간(1000 당 1초)
        v_fllipper.setAutoStart(true);          // 자동 시작 유무 설정

        // animation
        v_fllipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        v_fllipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
    }
}
