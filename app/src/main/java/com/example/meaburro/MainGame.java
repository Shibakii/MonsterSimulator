package com.example.meaburro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainGame extends AppCompatActivity {

    Character player;
    ProgressBar playerLife;
    AdventureFragment af;

    Enemy enemigo1;
    Enemy enemigo2;
    Enemy enemigo3;
    int StateStage;

    boolean cambioAventura = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        af = (AdventureFragment) getSupportFragmentManager().findFragmentById(R.id.adventureFragment);

//        if (savedInstanceState != null) {
//
//            af = (AdventureFragment) getSupportFragmentManager().findFragmentByTag("frag1");
//
//        } else if (af == null) {
//
//            af = new AdventureFragment();
//        }

//        if (!af.isInLayout()) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.aquiVaElFragment, af, "frag1")
//                    .commit();
//        }

        Intent intent = getIntent();
        this.player = (Character) intent.getSerializableExtra("player");

        ImageView playerImg = findViewById(R.id.adventureCharImg);
        TextView playerName = findViewById(R.id.adventureCharName);
        TextView playerRace = findViewById(R.id.adventureCharRace);
        playerLife = findViewById(R.id.adventureCharLife);

        playerImg.setImageResource(this.player.getImg());
        playerName.setText(this.player.getName());
        playerRace.setText(this.player.getRace());
        // Life bar from the player
        playerLife.setMax(this.player.getHealth());
        playerLife.setProgress(this.player.getHealth());

        ImageButton shop = findViewById(R.id.adventure_shop);
        ImageButton adventure = findViewById(R.id.adventure_adventure);

        //af.fillData(player);

        af.fillData(player);

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.aquiVaElFragment, new ShopFragment(), "frag");
                //ft.setReorderingAllowed(true);
                //ft.addToBackStack(null);
                ft.commit();

            }
        });

        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (savedInstanceState != null) { // saved instance state, fragment may exist
//                    // look up the instance that already exists by tag
//                    af = (AdventureFragment) getSupportFragmentManager().getFragment(savedInstanceState, "frag1");
//                }
//                else if (!af.isInLayout())
//                {
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.aquiVaElFragment, af, "frag1");
//                    ft.commit();
//                }
                AdventureFragment af = new AdventureFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.aquiVaElFragment, af , "frag");
                //ft.setReorderingAllowed(true);
                //ft.addToBackStack(null);
                ft.commit();

                // No tiene view por lo que hay que darle una :))))
                //af.fillData(player);

                //FragmentTransaction ft = mgr.beginTransaction();
                //ft.replace(R.id.aquiVaElFragment, new AdventureFragment());
                //ft.setReorderingAllowed(true);
                //ft.addToBackStack(null);
                //ft.commit();

            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "frag1", af);
    }

    public Enemy getEnemigo1() {
        return enemigo1;
    }

    public void setEnemigo1(Enemy enemigo1) {
        this.enemigo1 = enemigo1;
    }

    public Enemy getEnemigo2() {
        return enemigo2;
    }

    public void setEnemigo2(Enemy enemigo2) {
        this.enemigo2 = enemigo2;
    }

    public Enemy getEnemigo3() {
        return enemigo3;
    }

    public void setEnemigo3(Enemy enemigo3) {
        this.enemigo3 = enemigo3;
    }

    public int getStateStage() {
        return StateStage;
    }

    public void setStateStage(int stateStage) {
        StateStage = stateStage;
    }

    public boolean isCambioAventura() {
        return cambioAventura;
    }

    public void setCambioAventura(boolean cambioAventura) {
        this.cambioAventura = cambioAventura;
    }

    public Character getPlayer() {
        return player;
    }

    public ProgressBar getPlayerLife() {
        return playerLife;
    }

    public AdventureFragment getAf() {
        return af;
    }
}