package com.example.aftermeals.Menuinfo; //Menu Adpater -김준

import android.app.Activity;
import android.app.Application;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aftermeals.R;

import java.util.ArrayList;
import java.util.List;


public class MenuAdpater extends RecyclerView.Adapter<MenuAdpater.CustomViewHolder> {
    private ArrayList<Menu> arrayList;
    private Context context;
    private MenuListener menuListener;



    public MenuAdpater(ArrayList<Menu> arrayList, Context context, MenuListener menuListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.menuListener = menuListener;
    }
    public interface MenuListener{
        void clickBtn(String data);
    }





    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_info_list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
       holder.menu_info_list_item_name.setText(arrayList.get(position).getMenu_info_name());
       holder.menu_info_list_item_price.setText(arrayList.get(position).getMenu_info_price());
       holder.menu_info_list_item_check.setChecked(arrayList.get(position).isChecked());
       holder.menu_info_list_item_check.setTag(arrayList.get(position));
       holder.menu_info_list_item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(holder.menu_info_list_item_check.isChecked() == true){
                 ((Menuinfo)context).setData(arrayList.get(position).getMenu_info_price());
                 menuListener.clickBtn(arrayList.get(position).getMenu_info_price());
                System.out.println(holder.menu_info_list_item_price.toString());
            } else{
                ((Menuinfo)context).setData(null);
            }
           }
       });

    }

    @Override
    public int getItemCount() {
        return (arrayList !=null?arrayList.size() : 0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {
        CheckBox menu_info_list_item_check;
        TextView menu_info_list_item_price, menu_info_list_item_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.menu_info_list_item_price = itemView.findViewById(R.id.menu_info_list_item_price);
            this.menu_info_list_item_name = itemView.findViewById(R.id.menu_info_list_item_name);
            this.menu_info_list_item_check = itemView.findViewById(R.id.menu_info_list_item_check);


        }
    }

}


