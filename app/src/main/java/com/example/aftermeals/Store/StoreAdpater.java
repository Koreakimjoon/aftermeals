package com.example.aftermeals.Store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
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
import com.example.aftermeals.Menuinfo.Menuinfo;

import java.util.ArrayList;

public class StoreAdpater extends RecyclerView.Adapter<StoreAdpater.CustomViewHolder>{ //김준
    private ArrayList<Store> arrayList;
    private Context context;
    private int store_count;

    public StoreAdpater(ArrayList<Store> arrayList, Context context,int store_count) {
        this.arrayList = arrayList;
        this.context = context;
        this.store_count = store_count;

    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        Glide.with(holder.itemView) //해당 이미지를 데이터베이스에서 받아옴.
                .load(arrayList.get(position).getMenu_img())
                .into(holder.menu_img);
        holder.menu_name.setText(arrayList.get(position).getMenu_name()); //해당 이름을 데이터베이스에서 받아옴
        holder.menu_info.setText(String.valueOf(arrayList.get(position).getMenu_info())); //해당 정보를 데이터베이스에서 받아옴
        holder.menu_price.setText(arrayList.get(position).getMenu_price()); //해당가격을 데이터 베이스에서 받아옴


        holder.storeinfo_china_btn_num.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                        final Intent intent;
                        int count = holder.getAdapterPosition();


                        intent = new Intent(v.getContext(), Menuinfo.class);
                        intent.putExtra("menu_img", arrayList.get(position).getMenu_img()); //이미지를 인텐트 시키는곳에 정보를 menu_img로 넘김
                        intent.putExtra("menu_name", arrayList.get(position).getMenu_name()); //이름을 인텐트 시키는곳에 정보를 menu_name으로 넘김
                        intent.putExtra("menu_info", arrayList.get(position).getMenu_info()); //정보을 인텐트 시키는곳에 정보를 menu_info으로 넘김
                        intent.putExtra("menu_price", arrayList.get(position).getMenu_price()); //가격을 인텐트 시키는곳에 정보를 menu_price으로 넘김
                        intent.putExtra("count",count); //해당 store adapter에서 포지션값을 count로 넘김
                        intent.putExtra("StorearrayList",store_count); //엑티비티인 storeinfo_***에서 값을 받아온것을 storearrayList로 넘김 조건문 쓸때 필요함.
                        ((Activity) context).startActivityForResult(intent, 1);
            }
        });


    }
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MyAdapter", "onActivityResult");
    }

    @Override
    public int getItemCount() {
        return (arrayList !=null?arrayList.size() : 0);
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder{
        LinearLayout storeinfo_china_btn_num;
        ImageView menu_img;
        TextView menu_name, menu_info, menu_price;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.menu_img= itemView.findViewById(R.id.storeinfo_china_image_noodlo);
            this.menu_name = itemView.findViewById(R.id.storeinfo_china_text_menuname);
            this.menu_info = itemView.findViewById(R.id.storeinfo_china_text_menuinfo);
            this.menu_price= itemView.findViewById(R.id.storeinfo_china_text_menuprice);
            this.storeinfo_china_btn_num = itemView.findViewById(R.id.storeinfo_china_btn_num);


        }

    }



}
