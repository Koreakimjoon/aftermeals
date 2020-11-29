package com.example.aftermeals;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aftermeals.Menuinfo.Menuinfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ChoicestoreAdapter extends RecyclerView.Adapter<ChoicestoreAdapter.ChoicestoreViewHolder> {

    private ArrayList<Choicestore> arrayList;
    private Context context;
    private int choice_count;
    FirebaseDatabase mDatabase;
    DatabaseReference dataRef;


    public ChoicestoreAdapter(ArrayList<Choicestore> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.choice_count = choice_count;
    }


    @NonNull
    @Override
    public ChoicestoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choicestore, parent, false);
        ChoicestoreViewHolder holder = new ChoicestoreViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChoicestoreViewHolder holder, final int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getChoicestore_img())
                .into(holder.choicestore_img);
        holder.choicestore_name.setText("매장명 : " + arrayList.get(position).getChoicestore_name());
        holder.choicestore_time.setText("운영시간 : " + arrayList.get(position).getChoicestore_time());
        holder.choicestore_breaktime.setText("브레이크타임 : " +arrayList.get(position).getChoicestore_breaktime());
        holder.choicestore_number.setText("매장번호 : " + arrayList.get(position).getChoicestore_number());

        holder.choicestore_delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String NAme= arrayList.get(position).getChoicestore_name();

                dataRef = FirebaseDatabase.getInstance().getReference( "Choicestore" );

                dataRef.orderByChild("choicestore_name")
                        .equalTo( NAme )
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                    String clubkey = childSnapshot.getKey();

                                    Log.d(TAG, "KEY = " + clubkey );


                                    childSnapshot.getRef().removeValue();


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }

                        });
            }
        });



    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    class ChoicestoreViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemchoice_btn;
        Button choicestore_delete;

        private ImageView choicestore_img;
        private TextView choicestore_name;
        private TextView choicestore_time;
        private TextView choicestore_breaktime;
        private TextView choicestore_number;

        public ChoicestoreViewHolder(@NonNull View itemView) {
            super(itemView);

            choicestore_img = itemView.findViewById(R.id.itemchoice_img);
            choicestore_name = itemView.findViewById(R.id.itemchoice_name);
            choicestore_time = itemView.findViewById(R.id.itemchoice_time);
            choicestore_breaktime = itemView.findViewById(R.id.itemchoice_breaktime);
            choicestore_number = itemView.findViewById(R.id.itemchoice_number);
            itemchoice_btn = itemView.findViewById(R.id.itemchoice_btn);
            choicestore_delete = itemView.findViewById(R.id.choicestore_delete);

        }
    }
}
