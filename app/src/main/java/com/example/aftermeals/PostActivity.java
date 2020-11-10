package com.example.aftermeals;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener { //하성빈 제작 저장후 게시판으로 안넘어감 인텐트 넣엇더니오류뜸

    EditText mTitle, mContents, mNickname;
    FirebaseFirestore mstore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle = findViewById(R.id.post_edit_title);
        mNickname = findViewById(R.id.post_edit_nickname);
        mContents = findViewById(R.id.post_edit_contents);

        findViewById(R.id.post_button_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) { //데이터 저장 코드
        String postId = mstore.collection(FirebasePost.post).document().getId();
        Map<String, Object> data = new HashMap<>();
        data.put(FirebasePost.title, mTitle.getText().toString());
        data.put(FirebasePost.nickname, mNickname.getText().toString());
        data.put(FirebasePost.contents, mContents.getText().toString());
        data.put(FirebasePost.timestamp, FieldValue.serverTimestamp());
        mstore.collection(FirebasePost.post).document(postId).set(data, SetOptions.merge());
        finish();
    }
}
