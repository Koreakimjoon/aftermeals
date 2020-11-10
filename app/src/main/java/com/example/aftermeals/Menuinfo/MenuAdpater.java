package com.example.aftermeals.Menuinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aftermeals.R;
import com.example.aftermeals.Store.Store;

import java.util.ArrayList;

public class MenuAdpater extends RecyclerView.Adapter<MenuAdpater.CustomViewHolder> {
    private ArrayList<Menu> arrayList;
    private Context context;


    public MenuAdpater(ArrayList<Menu> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_info_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
       holder.menu_info_list_item_name.setText(arrayList.get(position).getMenu_info_name());
       holder.menu_info_list_item_price.setText(arrayList.get(position).getMenu_info_price());
      


    }

    @Override
    public int getItemCount() {
        return (arrayList !=null?arrayList.size() : 0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        CheckBox menu_info_list_item_check;
        TextView menu_info_list_item_price, menu_info_list_item_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);


            this.menu_info_list_item_price= itemView.findViewById(R.id.menu_info_list_item_price);
            this.menu_info_list_item_name = itemView.findViewById(R.id.menu_info_list_item_name);
            this.menu_info_list_item_check = itemView.findViewById(R.id.menu_info_list_item_check);

        }

    }

}
