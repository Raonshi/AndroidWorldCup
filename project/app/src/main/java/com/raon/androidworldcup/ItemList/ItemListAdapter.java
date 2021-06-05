package com.raon.androidworldcup.ItemList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.raon.androidworldcup.R;

import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {

    private ArrayList<Item> list = new ArrayList<Item>();
    private ImageButton icon;
    private EditText text;

    public void addItem(ImageButton icon, String text){
        Item item = new Item();

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
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        icon = convertView.findViewById(R.id.list_item_image);
        text = convertView.findViewById(R.id.list_item_text);

        Item item = list.get(position);


        icon = item.getIcon();
        text.setText(item.getText());

        return convertView;
    }
}
