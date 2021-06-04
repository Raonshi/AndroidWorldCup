package com.raon.androidworldcup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private ArrayList<ListItem> list = new ArrayList<ListItem>();
    private ImageView icon;
    private EditText text;

    public void addItem(int icon, String text){
        ListItem item = new ListItem();

        item.setIcon(icon);
        item.setText(text);

        list.add(item);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int pos = position;
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        icon = convertView.findViewById(R.id.list_item_image);
        text = convertView.findViewById(R.id.list_item_text);

        ListItem item = list.get(position);


        icon.setImageResource(item.getIcon());
        text.setText(item.getText());

        return convertView;
    }
}
