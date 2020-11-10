package com.example.aftermeals;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class home_bottomNavigation_post extends Fragment implements View.OnClickListener { //서강호 제작 네비게이션뷰 연동후 하성빈 수정
    private View view;
    private RecyclerView mPostRecyclerView;
    private PostAdapter mAdapter;
    private List<Post> mDatas;
    FirebaseFirestore mstore = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_bottom_navigation_post, container, false);

        mPostRecyclerView = view.findViewById(R.id.post_recyclerview);


        mDatas = new ArrayList<>();



        view.findViewById(R.id.post_floating_writeicon).setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() { //데이터 불러오는 코드
        super.onStart();
        mstore.collection(FirebasePost.post)
                .orderBy(FirebasePost.timestamp, Query.Direction.DESCENDING) //시간순 정렬 최신글이 위로
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            mDatas.clear(); //중복 불러오기 방지 불러왓던것 초기화
                            for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String title = String.valueOf(shot.get(FirebasePost.title));
                                String nickname = String.valueOf(shot.get(FirebasePost.nickname));
                                String contents = String.valueOf(shot.get(FirebasePost.contents));
                                Post data = new Post(title, nickname, contents);
                                mDatas.add(data);//데이터 불러옴
                            }
                            mAdapter = new PostAdapter(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);//어댑터연결
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), PostActivity.class);
        startActivity(intent);
    }
}