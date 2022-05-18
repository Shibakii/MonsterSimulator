package com.example.meaburro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter {

    private Item [] items;

    public ItemAdapter(Context context, Item [] items)
    {
        super(context, R.layout.shop_item, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.shop_item, parent, false);

        ImageView item_img = item.findViewById(R.id.item_img);
        TextView item_name = item.findViewById(R.id.item_name);
        TextView item_description = item.findViewById(R.id.item_description);
        TextView item_price = item.findViewById(R.id.item_price);

        item_img.setImageResource(items[position].getItem_img());
        item_name.setText(items[position].getItem_name());
        item_description.setText(items[position].getItem_description());
        item_price.setText(items[position].getItem_price() + "G");

        return item;
    }

}
