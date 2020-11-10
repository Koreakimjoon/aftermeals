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
import com.example.aftermeals.Storeinfo.storeinfo_japan;
import com.example.aftermeals.Storeinfo.storeinfo_japan2;
import com.example.aftermeals.Storeinfo.storeinfo_japan3;
import com.example.aftermeals.Storeinfo.storeinfo_japan4;

import java.util.ArrayList;

public class ListAdapter_japan extends RecyclerView.Adapter<ListAdapter_japan.CustomViewHolder>{
    private ArrayList<List> arrayList;

    private Context context;

    public ListAdapter_japan(ArrayList<List> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.storelist_item_japan,parent,false);
        CustomViewHolder holder1 = new CustomViewHolder(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder1, final int position) {

        Glide.with(holder1.itemView)
                .load(arrayList.get(position).getStorelist_img())
                .into(holder1.storelist_japan_img);
        holder1.storelist_japan_name.setText(arrayList.get(position).getStorelist_name());
        holder1.storelist_japan_time.setText(String.valueOf(arrayList.get(position).getStorelist_time()));
        holder1.storelist_japan_breaktime.setText(arrayList.get(position).getStorelist_breaktime());
        holder1.storelist_japan_number.setText(arrayList.get(position).getStorelist_number());





       holder1.storelist_japan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              final Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(v.getContext(), storeinfo_japan.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(v.getContext(), storeinfo_japan2.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(v.getContext(), storeinfo_japan3.class);
                    intent.putExtra("img",arrayList.get(position).getStorelist_img());
                    intent.putExtra("name",arrayList.get(position).getStorelist_name());
                    intent.putExtra("number",arrayList.get(position).getStorelist_number());
                    v.getContext().startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(v.getContext(), storeinfo_japan4.class);
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
        LinearLayout storelist_japan;
        ImageView storelist_japan_img;
        TextView storelist_japan_name, storelist_japan_time, storelist_japan_breaktime, storelist_japan_number;



        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.storelist_japan_img= itemView.findViewById(R.id.storelist_japan_img);
            this.storelist_japan_name = itemView.findViewById(R.id.storelist_japan_name);
            this.storelist_japan_time = itemView.findViewById(R.id.storelist_japan_time);
            this.storelist_japan_breaktime= itemView.findViewById(R.id.storelist_japan_breaktime);
            this.storelist_japan_number = itemView.findViewById(R.id.storelist_japan_number);
            this.storelist_japan = itemView.findViewById(R.id.storelist_japan);
        }

    }

}
