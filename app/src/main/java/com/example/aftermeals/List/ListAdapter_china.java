package com.example.aftermeals.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aftermeals.R;
import com.example.aftermeals.Storeinfo.storeinfo_china;
import com.example.aftermeals.Storeinfo.storeinfo_china2;
import com.example.aftermeals.Storeinfo.storeinfo_china3;
import com.example.aftermeals.Storeinfo.storeinfo_china4;

import java.util.ArrayList;

public class ListAdapter_china extends RecyclerView.Adapter<ListAdapter_china.CustomViewHolder>{


    private ArrayList<List> arrayList;

    private Context context;

    public ListAdapter_china(ArrayList<List> arrayList, Context context) { //해당 리스트 배열 + context ----준
        this.arrayList = arrayList;
        this.context = context;
    }





    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storelist_item_china,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {

        Glide.with(holder.itemView) //이미지를 glide해서 sotrelist_china_img에 해당 받아온 값을 대입
                .load(arrayList.get(position).getStorelist_img())
                .into(holder.storelist_china_img);
        holder.storelist_china_name.setText(arrayList.get(position).getStorelist_name()); //파이어베이스에 해당 값을 불러와서 이름을 대입
        holder.storelist_china_time.setText(String.valueOf(arrayList.get(position).getStorelist_time())); //파이어베이스에 해당 값을 불러와서 시간을 대입
        holder.storelist_china_breaktime.setText(arrayList.get(position).getStorelist_breaktime()); //파이어베이스에 해당 값을 불러와서 휴계시간을 대입
        holder.storelist_china_number.setText(arrayList.get(position).getStorelist_number()); //파이어베이스에 해당 값을 불러와서 전화번호를 대입


       holder.storelist_china.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Intent intent;

                int count = holder.getAdapterPosition(); //리싸이클러뷰에 해당 포지션 값을 count로 셋팅

            switch (position) {
                case 0: //포지션값에 대한 반복문
                    intent = new Intent(v.getContext(), storeinfo_china.class); //해당 클래스로 인텐트 시킴
                    intent.putExtra("img",arrayList.get(position).getStorelist_img()); //이미지를 img라는 이름에 값을 저장하여 인텐트 시킨 클래스로 값을 넘김
                    intent.putExtra("name",arrayList.get(position).getStorelist_name()); //이름을 name이라는 이름에 값을 저장하여 인텐트 시킨 클래스로 값을 넘김
                    intent.putExtra("number",arrayList.get(position).getStorelist_number()); //전화번호를 number라는 이름에 값을 저장하여 인텐트시킨 클래스로 값을 넘김
                    v.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(v.getContext(), storeinfo_china2.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(v.getContext(), storeinfo_china3.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(v.getContext(), storeinfo_china4.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;

                default:
                    break;

                 }

            }
        });


    }


    @Override
    public int getItemCount() {
        return (arrayList !=null? arrayList.size() : 0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{ //초기화
        LinearLayout storelist_china;
        ImageView storelist_china_img;
        TextView storelist_china_name, storelist_china_time, storelist_china_breaktime, storelist_china_number;




        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.storelist_china_img= itemView.findViewById(R.id.storelist_china_img);
            this.storelist_china_name = itemView.findViewById(R.id.storelist_china_name);
            this.storelist_china_time = itemView.findViewById(R.id.storelist_china_time);
            this.storelist_china_breaktime= itemView.findViewById(R.id.storelist_china_breaktime);
            this.storelist_china_number = itemView.findViewById(R.id.storelist_china_number);
            this.storelist_china = itemView.findViewById(R.id.storelist_china);

        }

    }


}
