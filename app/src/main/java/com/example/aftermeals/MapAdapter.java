package com.example.aftermeals; //하성빈 제작 맵 어뎁터 진행중

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MapAdapter extends BaseAdapter {
    public ArrayList<MapSearchData> itemList = new ArrayList<>();
    public String id;

    public MapAdapter(Context con){
        final Context context = con;
    }
    public void setItemList(ArrayList<MapSearchData> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_mapsearchdata, parent, false);
        }

        TextView name = convertView.findViewById(R.id.mapsearchdata_textview_name);
        TextView address = convertView.findViewById(R.id.mapsearchdata_textview_address);

        MapSearchData userItem = itemList.get(position);
        name.setText(userItem.getName());
        address.setText(userItem.getAddress());

        return convertView;
    }
    //Read that position information and return view

    public void addList(String name, String address, double x, double y){
        MapSearchData item = new MapSearchData(name,address,x,y);
        itemList.add(item);
    }
}
//MapSearch Listview Adapter