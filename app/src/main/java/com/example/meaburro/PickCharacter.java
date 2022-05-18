package com.example.meaburro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;

public class PickCharacter extends AppCompatActivity {

    Character [] characters = getCharacters();

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_character2);

        CharacterAdapter adapter = new CharacterAdapter(this, characters);
        lista = findViewById(R.id.Lista);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Character player = characters[position];

                Intent intent = new Intent(PickCharacter.this, PopUpNombre.class);
                intent.putExtra("player", (Serializable) player);
                startActivity(intent);

            }
        });

    }

    private Character[] getCharacters()
    {
        Character [] chars = {
                new Character("Goblin", 94, 12, 1, R.drawable.goblin),
                new Character("Orc", 119, 6, 2, R.drawable.orc),
                new Character("Demon", 131, 10, 1, R.drawable.demon),
                new Character("Vampire", 116, 11, 2, R.drawable.vampire),
                new Character("Ghost", 88, 14, 0, R.drawable.ghost),
                new Character("Werewolf", 138, 9, 2, R.drawable.werewolf),
                new Character("Living armor", 150, 5, 3, R.drawable.livingarmor),
                new Character("Observer", 80, 16, 0, R.drawable.observer)
        };

        return chars;
    }

}