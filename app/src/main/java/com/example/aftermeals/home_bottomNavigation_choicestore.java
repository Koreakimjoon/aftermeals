package com.example.aftermeals; // 서강호 제작

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home_bottomNavigation_choicestore extends Fragment/* implements View.OnClickListener*/ {
    private View view;
    private RecyclerView mChoicestoreRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Choicestore> mArrayList;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mFirebaseUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_bottom_navigation_choicestore, container, false);

        mChoicestoreRecyclerView = view.findViewById(R.id.choicestore_recyclerview);
        mChoicestoreRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mChoicestoreRecyclerView.setLayoutManager(mLayoutManager);
        mArrayList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("Choicestore");


        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Choicestore choicestore = snapshot.getValue(Choicestore.class);
                    mArrayList.add(choicestore);
                }
                mAdapter.notifyDataSetChanged();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("home_bottomNavigation_choicestore", String.valueOf((error.toException())));
            }
        });

        mAdapter = new ChoicestoreAdapter(mArrayList, this.getContext());
        mChoicestoreRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
