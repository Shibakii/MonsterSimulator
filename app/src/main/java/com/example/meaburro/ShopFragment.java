package com.example.meaburro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class ShopFragment extends Fragment {

    GridView lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.shop_fragment_layout, container, false);

        ItemAdapter adapter = new ItemAdapter(view.getContext(), getItems());
        lista = view.findViewById(R.id.shop_list);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });

        return view;
    }

    public Item[] getItems()
    {
        Item[] items = {
                new Item(R.drawable.item_health_potion, "Poción de Salud", "Heals +35HP", 15, 50, 1),
                new Item(R.drawable.item_pocion_fuerza, "Poción de Fuerza", "+10ATK (5 Hits)", 50, 10, 2),
                new Item(R.drawable.item_pocion_dureza, "Poción de Dureza", "+1DEF (10HITS)", 95, 10, 3),
                new Item(R.drawable.item_corazon_en_lata, "Corazón en lata", "+25HP", 150, 5, 4),
                new Item(R.drawable.item_sorbo_demoniaco, "Sorbo demoníaco", "-15HP, +5ATK", 200, 5, 5),
                new Item(R.drawable.item_pergamino_de_huida, "Pergamino de fuga", "Huyes de un combate", 100, 20, 6),
                new Item(R.drawable.item_pergamino_de_odio, "Pergamino de odio", "-10HP a todos", 105, 10, 7),
                new Item(R.drawable.item_simple_sword, "Espada simple", "+8ATK", 75, 1, 8),
                new Item(R.drawable.item_simple_axe, "Hacha simple", "+12ATK", 175, 1, 9),
                new Item(R.drawable.item_simple_shield, "Escudo simple", "+2DEF", 225, 1, 10),
                new Item(R.drawable.item_demon_sword, "Espada demoníaca", "+20ATK", 500, 1, 11),
                new Item(R.drawable.item_swiftness_necklace, "Amuleto de Gordias", "+1DEF, +5ATK ,+10HP", 315, 1, 12),
                new Item(R.drawable.item_toughness_mantra, "Mantra de dureza", "+2DEF (20HITS)", 205, 5, 13),
        };
        return items;
    }
}