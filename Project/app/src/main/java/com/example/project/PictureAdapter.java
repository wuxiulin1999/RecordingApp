package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Picture> pictures;
    public PictureAdapter(String[] titles, int[] images, Context context){
        super();
        pictures=new ArrayList<Picture>();
        inflater=LayoutInflater.from(context);
        for(int i=0;i<images.length;i++){
            Picture picture=new Picture(titles[i],images[i]);
            pictures.add(picture);
        }
    }
    @Override
    public int getCount() {
        if(null!=pictures){
            return pictures.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.gvitem,null);
            viewHolder=new ViewHolder();
            viewHolder.title=(TextView)convertView.findViewById(R.id.ItemTitle);
            viewHolder.image=(ImageView)convertView.findViewById(R.id.ItemImage);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        viewHolder.title.setText(pictures.get(position).getTitle());
        viewHolder.image.setImageResource(pictures.get(position).getImageId());
        return convertView;
    }
}
