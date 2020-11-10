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
import com.example.aftermeals.Storeinfo.storeinfo_korea;
import com.example.aftermeals.Storeinfo.storeinfo_korea2;
import com.example.aftermeals.Storeinfo.storeinfo_korea3;
import com.example.aftermeals.Storeinfo.storeinfo_korea4;

import java.util.ArrayList;

public class ListAdapter_korea extends RecyclerView.Adapter<ListAdapter_korea.CustomViewHolder>{
    private ArrayList<List> arrayList;

    private Context context;

    public ListAdapter_korea(ArrayList<List> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storelist_item_korea,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getStorelist_img())
                .into(holder.storelist_korea_img);
        holder.storelist_korea_name.setText(arrayList.get(position).getStorelist_name());
        holder.storelist_korea_time.setText(String.valueOf(arrayList.get(position).getStorelist_time()));
        holder.storelist_korea_breaktime.setText(arrayList.get(position).getStorelist_breaktime());
        holder.storelist_korea_number.setText(arrayList.get(position).getStorelist_number());





       holder.storelist_korea.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              final Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(v.getContext(), storeinfo_korea.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(v.getContext(), storeinfo_korea2.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(v.getContext(), storeinfo_korea3.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(v.getContext(), storeinfo_korea4.class);
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



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        LinearLayout storelist_korea;
        ImageView storelist_korea_img;
        TextView storelist_korea_name, storelist_korea_time, storelist_korea_breaktime, storelist_korea_number;



        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.storelist_korea_img= itemView.findViewById(R.id.storelist_korea_img);
            this.storelist_korea_name = itemView.findViewById(R.id.storelist_korea_name);
            this.storelist_korea_time = itemView.findViewById(R.id.storelist_korea_time);
            this.storelist_korea_breaktime= itemView.findViewById(R.id.storelist_korea_breaktime);
            this.storelist_korea_number = itemView.findViewById(R.id.storelist_korea_number);
            this.storelist_korea = itemView.findViewById(R.id.storelist_korea);
        }

    }

}
