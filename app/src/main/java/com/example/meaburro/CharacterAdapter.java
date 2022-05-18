package com.example.meaburro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterAdapter extends ArrayAdapter {

    private Character [] characters;

    public CharacterAdapter (Context context, Character [] characters)
    {
        super(context, R.layout.characters_layout, characters);
        this.characters = characters;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View item = LayoutInflater.from(getContext()).inflate(R.layout.characters_layout, parent, false);

        ImageView charImg = item.findViewById(R.id.charImg);
        TextView charRace = item.findViewById(R.id.charRace);
        TextView charHealth = item.findViewById(R.id.charHealth);
        TextView charAtack = item.findViewById(R.id.charAtack);
        TextView charDefense = item.findViewById(R.id.charDefense);

        charImg.setImageResource(characters[position].getImg());
        charRace.setText(characters[position].getRace());
        charHealth.setText(characters[position].getHealth() + "");
        charAtack.setText(characters[position].getAtack() + "");
        charDefense.setText(characters[position].getDefense() + "");

        return item;
    }

}
