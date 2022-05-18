 package com.example.meaburro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdventureFragment extends Fragment {

    private int actualStage = 0;
    private Button moveFurther;
    private Button explore;
    private ImageView imgStage;
    private TextView txtStage;

    private ProgressBar enemyHealth1;
    private ImageButton enemyButton1;

    private ProgressBar enemyHealth2;
    private ImageButton enemyButton2;

    private ProgressBar enemyHealth3;
    private ImageButton enemyButton3;

    private Enemy enemigo1;
    private Enemy enemigo2;
    private Enemy enemigo3;

    private Character player;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.adventure_fragment_layout, container, false);

//        if (((MainGame) getActivity()).isCambioAventura()) {
//            fillData(((MainGame) getActivity()).getPlayer());
//            ((MainGame) getActivity()).setCambioAventura(false);
//        }

        return view;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            enemigo1 = (Enemy) savedInstanceState.getSerializable("e1");
            enemigo2 = (Enemy) savedInstanceState.getSerializable("e2");
            enemigo3 = (Enemy) savedInstanceState.getSerializable("e3");
            actualStage = savedInstanceState.getInt("stage");
            // Do something with value if needed
        }
    }

    public void nextStage()
    {
        if(actualStage == 1)
        {
            this.imgStage.setImageResource(R.drawable.stage_forest_mid);
            this.actualStage++;
        }
        else if(actualStage == 2)
        {
            this.imgStage.setImageResource(R.drawable.stage_forest_hard);
            this.actualStage++;
        }
    }

    public void fillData(Character player)
    {

        this.player = player;
        ((MainGame) getActivity()).setCambioAventura(true);

        // Seteando botones de exploración
        this.explore = getView().findViewById(R.id.AdventureExplore);
        this.moveFurther = getView().findViewById(R.id.AdventureMoveFurther);

        // Seteando img de fondo junto con el texto de fondo
        this.imgStage = getView().findViewById(R.id.imgBackgroundStage);
        this.txtStage = getView().findViewById(R.id.txtBackgroundStage);

        // Asignando los valores al fondo
        this.imgStage.setImageResource(R.drawable.stage_forest_easy);
        this.txtStage.setText("Te adentras en un frondoso bosque...");

        this.enemigo1 = ((MainGame) getActivity()).getEnemigo1();
        this.enemigo2 = ((MainGame) getActivity()).getEnemigo2();
        this.enemigo3 = ((MainGame) getActivity()).getEnemigo3();

        // Marcar a los enemigos
        if(enemigo1 != null)
        {
            this.enemyHealth1 = getView().findViewById(R.id.adventureEnemyLife_1);
            this.enemyButton1 = getView().findViewById(R.id.adventureEnemyImg_1);
            enemyHealth1.setProgress(enemigo1.getHealth());
            enemyButton1.setEnabled(true);
        }
        else
        {
            this.enemyHealth1 = getView().findViewById(R.id.adventureEnemyLife_1);
            enemyHealth1.setVisibility(View.INVISIBLE);
            this.enemyButton1 = getView().findViewById(R.id.adventureEnemyImg_1);
            enemyButton1.setEnabled(false);
        }

        if(enemigo2 != null)
        {
            this.enemyHealth2 = getView().findViewById(R.id.adventureEnemyLife_2);
            this.enemyButton2 = getView().findViewById(R.id.adventureEnemyImg_2);
            enemyHealth2.setProgress(enemigo2.getHealth());
            enemyButton2.setEnabled(true);
        }
        else
        {
            this.enemyHealth2 = getView().findViewById(R.id.adventureEnemyLife_2);
            enemyHealth2.setVisibility(View.INVISIBLE);
            this.enemyButton2 = getView().findViewById(R.id.adventureEnemyImg_2);
            enemyButton2.setEnabled(false);
        }

        if(enemigo3 != null)
        {
            this.enemyHealth3 = getView().findViewById(R.id.adventureEnemyLife_3);
            this.enemyButton3 = getView().findViewById(R.id.adventureEnemyImg_3);
            enemyHealth3.setProgress(enemigo1.getHealth());
            enemyButton3.setEnabled(true);
        }
        else
        {
            this.enemyHealth3 = getView().findViewById(R.id.adventureEnemyLife_3);
            enemyHealth3.setVisibility(View.INVISIBLE);
            this.enemyButton3 = getView().findViewById(R.id.adventureEnemyImg_3);
            enemyButton3.setEnabled(false);
        }

        this.actualStage++;

        // Añadir sistema de busqueda / combate / recolección para poder hacer uso de este boton y el otro

        moveFurther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explore();
            }
        });

        enemyButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enemyButton1.isEnabled() == true)
                {
                    enemigo1.quitarVidaEnemigo((player.getAtack() - enemigo1.getDefense()));
                    enemyHealth1.setProgress(enemigo1.getHealth());
                    String textoEnemigo = player.getName() + " ha causado " + player.getAtack() + " de daño a " + enemigo1.getName() + "!";

                    if(enemigo1.getHealth() <= 0 && enemyHealth1.getProgress() <= 0)
                    {
                        player.setExp(enemigo1.getGivedExp());
                        player.setGold(enemigo1.getGivedGold());
                        player.TryLevelUp();
                        enemyButton1.setImageResource(0);
                        enemyButton1.setEnabled(false);
                        enemyHealth1.setVisibility(View.INVISIBLE);
                        habilitarButtons();


                    }
                    else
                    {
                        atackPlayer(textoEnemigo);
                        if (player.getHealth() <= 0)
                        {
                            txtStage.setText("Has muerto :(");
                            Intent intent = new Intent(getActivity(), PopUpMuerte.class);
                            startActivity(intent);
                        }
                    }
                    ((MainGame) getActivity()).setEnemigo1(enemigo1);
                }
            }
        });

        enemyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enemyButton2.isEnabled() == true)
                {
                    enemigo2.quitarVidaEnemigo((player.getAtack() - enemigo2.getDefense()));
                    enemyHealth2.setProgress(enemigo2.getHealth());
                    String textoEnemigo = player.getName() + " ha causado " + player.getAtack() + " de daño a " + enemigo2.getName() + "!";

                    if(enemigo2.getHealth() <= 0 && enemyHealth2.getProgress() <= 0)
                    {
                        player.setExp(enemigo2.getGivedExp());
                        player.setGold(enemigo2.getGivedGold());
                        player.TryLevelUp();
                        enemyButton2.setImageResource(0);
                        enemyButton2.setEnabled(false);
                        enemyHealth2.setVisibility(View.INVISIBLE);
                        habilitarButtons();
                    }
                    else
                    {
                        atackPlayer(textoEnemigo);
                        if (player.getHealth() <= 0)
                        {
                            txtStage.setText("Has muerto :(");
                            Intent intent = new Intent(getActivity(), PopUpMuerte.class);
                            startActivity(intent);
                        }
                    }
                    ((MainGame) getActivity()).setEnemigo2(enemigo2);
                }
            }
        });

        enemyButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enemyButton3.isEnabled() == true)
                {
                    enemigo3.quitarVidaEnemigo((player.getAtack() - enemigo3.getDefense()));
                    enemyHealth3.setProgress(enemigo3.getHealth());
                    String textoEnemigo = player.getName() + " ha causado " + player.getAtack() + " de daño a " + enemigo3.getName() + "!";

                    if(enemigo3.getHealth() <= 0 && enemyHealth3.getProgress() <= 0)
                    {
                        player.setExp(enemigo3.getGivedExp());
                        player.setGold(enemigo3.getGivedGold());
                        player.TryLevelUp();
                        enemyButton3.setImageResource(0);
                        enemyButton3.setEnabled(false);
                        enemyHealth3.setVisibility(View.INVISIBLE);
                        habilitarButtons();
                    }
                    else
                    {
                        atackPlayer(textoEnemigo);
                        if (player.getHealth() <= 0)
                        {
                            txtStage.setText("Has muerto :(");
                            Intent intent = new Intent(getActivity(), PopUpMuerte.class);
                            startActivity(intent);
                        }
                    }
                    ((MainGame) getActivity()).setEnemigo3(enemigo3);
                }
            }
        });
    }

    public void explore ()
    {
        int numAleatorio = (int) (Math.random() * 100 + 1);
        int numExpAleatorio = (int) (Math.random()*10 + 1);
        int numGoldAleatorio = (int) (Math.random()*5 + 1);

        if (numAleatorio >= 1 && numAleatorio <= 20)
        {
            txtStage.setText("Te has encontrado una hermosa flor, resplandece antes de desvancerse.\n"
                            + "Has obtenido " + numExpAleatorio + " de EXP!");
            player.setExp(numExpAleatorio);
        }
        else if (numAleatorio >= 21 && numAleatorio <= 50)
        {
            txtStage.setText("Encuentras una bolsa de cuero maltrecha, buscando dentro de ella encuentras un par de doblones.\n"
                    + "Has obtenido " + numExpAleatorio + " monedas de oro!");
            player.setGold(numGoldAleatorio);
        }
        else if (numAleatorio >= 51 && numAleatorio <= 100)
        {
            txtStage.setText("Escuchas como se rompen las ramas alrededor tuyo, te han encontrado!.\n");
            int quantityEnemies = (int) (Math.random() * 3);
            explore.setEnabled(false);
            explore.setBackgroundColor(getResources().getColor(R.color.gray));
            moveFurther.setEnabled(false);
            moveFurther.setBackgroundColor(getResources().getColor(R.color.gray));

            setEnemies(quantityEnemies);
        }
    }

    // Mirar de como hacer el combate
    public ArrayList<Enemy> setEnemies (int numEnemies)
    {
        ArrayList<Enemy> enemies = new ArrayList<>();

        int randomEnemy1 = (int) (Math.random() * getEnemies().length);
        int randomEnemy2 = (int) (Math.random() * getEnemies().length);
        int randomEnemy3 = (int) (Math.random() * getEnemies().length);
        Enemy actualEnemy1 = getEnemies()[randomEnemy1];
        Enemy actualEnemy2 = getEnemies()[randomEnemy2];
        Enemy actualEnemy3 = getEnemies()[randomEnemy3];

        if(numEnemies == 0)
        {
            setEnemy1(enemies, actualEnemy1);
            enemigo1 = actualEnemy1;
        }
        else if (numEnemies == 1)
        {
            setEnemy1(enemies, actualEnemy1);
            setEnemy2(enemies, actualEnemy2);
            enemigo1 = actualEnemy1;
            enemigo2 = actualEnemy2;
        }
        else
        {
            setEnemy1(enemies, actualEnemy1);
            setEnemy2(enemies, actualEnemy2);
            setEnemy3(enemies, actualEnemy3);
            enemigo1 = actualEnemy1;
            enemigo2 = actualEnemy2;
            enemigo3 = actualEnemy3;
        }

        return enemies;
    }

    // En un futuro que se lean por JSON y no escribiendo cada uno a mano.
    public Enemy[] getEnemies()
    {
        Enemy[] enemies = {
                new Enemy("Boar", "Beast", 40, 3, 2, R.drawable.enemy_boar, (int) (Math.random() * 10 + 4), (int) (Math.random() * 5)),
                new Enemy("Bat", "Beast", 25, 4, 0, R.drawable.enemy_bat, (int) (Math.random() * 8 + 2), (int) (Math.random() * 3)),
                new Enemy("Snake", "Beast", 33, 5, 0, R.drawable.enemy_snake, (int) (Math.random() * 9 + 3), (int) (Math.random() * 4)),
                new Enemy("Wolf", "Beast", 46, 6, 1, R.drawable.enemy_wolf, (int) (Math.random() * 12 + 6), (int) (Math.random() * 6 + 1)),
                new Enemy("Ent", "Elemental", 50, 7, 2, R.drawable.enemy_ent, (int) (Math.random() * 13 + 7), (int) (Math.random() * 7 + 1)),
                new Enemy("Wild Goblin", "Goblin", 35, 6, 1, R.drawable.goblin, (int) (Math.random() * 11 + 5), (int) (Math.random() * 8 + 2)),
                new Enemy("Baby Wyvern", "Drake", 60, 7, 2, R.drawable.enemy_wyvern, (int) (Math.random() * 20 + 8), (int) (Math.random() * 9 + 3))
        };
        return enemies;
    }
    public Enemy[] getBosses()
    {
        Enemy[] bosses = {
            new Enemy("Gnome Girald", "Human", 44, 2, 2, R.drawable.enemy_companion_gnome_1, (int) (Math.random() * 14 + 6), (int) (Math.random() * 5 + 1)),
            new Enemy("Sir Castemir of Joltia", "Human", 163, 5, 3, R.drawable.enemy_boss_royale_knight, (int) (Math.random() * 38 + 22), (int) (Math.random() * 27 + 10)),
            new Enemy("Gnome Astolf", "Human", 33, 3, 1, R.drawable.enemy_companion_gnome_2, (int) (Math.random() * 14 + 6), (int) (Math.random() * 5 + 1))
        };
        return bosses;
    }

    public void setEnemy1(ArrayList<Enemy> enemies, Enemy actualEnemy)
    {
        enemyHealth1.setVisibility(View.VISIBLE);
        enemyButton1.setEnabled(true);
        enemyButton1.setImageResource(actualEnemy.getImg());
        enemyHealth1.setMax(actualEnemy.getHealth());
        enemyHealth1.setProgress(actualEnemy.getHealth());
        enemies.add(actualEnemy);
    }

    public void setEnemy2(ArrayList<Enemy> enemies, Enemy actualEnemy)
    {
        enemyHealth2.setVisibility(View.VISIBLE);
        enemyButton2.setEnabled(true);
        enemyButton2.setImageResource(actualEnemy.getImg());
        enemyHealth2.setMax(actualEnemy.getHealth());
        enemyHealth2.setProgress(actualEnemy.getHealth());
        enemies.add(actualEnemy);
    }

    public void setEnemy3(ArrayList<Enemy> enemies, Enemy actualEnemy)
    {
        enemyHealth3.setVisibility(View.VISIBLE);
        enemyButton3.setEnabled(true);
        enemyButton3.setImageResource(actualEnemy.getImg());
        enemyHealth3.setMax(actualEnemy.getHealth());
        enemyHealth3.setProgress(actualEnemy.getHealth());
        enemies.add(actualEnemy);
    }

    public void habilitarButtons()
    {
        if(enemyButton1.isEnabled() == false && enemyButton2.isEnabled() == false && enemyButton3.isEnabled() == false)
        {
            explore.setEnabled(true);
            explore.setBackgroundColor(getResources().getColor(R.color.white));
            moveFurther.setEnabled(true);
            moveFurther.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    public void atackPlayer(String textoPlayer)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(textoPlayer + "\n");

        // Quitar vida enemigo 1 si esta visible
        if (enemyButton1.isEnabled() == true)
        {
            player.quitarVida((enemigo1.getAtack() - player.getDefense()));
            String enemigo1text = "El " + enemigo1.getName() + " enemigo ha causado " + enemigo1.getAtack() + " de daño!";
            ((MainGame) getActivity()).getPlayerLife().setProgress(player.getHealth());
            sb.append(enemigo1text + "\n");
        }
        // Quitar vida enemigo 2 si esta visible
        if (enemyButton2.isEnabled() == true)
        {
            player.quitarVida((enemigo2.getAtack() - player.getDefense()));
            String enemigo2text = "El " + enemigo2.getName() + " enemigo ha causado " + enemigo2.getAtack() + " de daño!";
            ((MainGame) getActivity()).getPlayerLife().setProgress(player.getHealth());
            sb.append(enemigo2text + "\n");
        }
        // Quitar vida enemigo 3 si esta visible
        if (enemyButton3.isEnabled() == true)
        {
            player.quitarVida((enemigo3.getAtack() - player.getDefense()));
            String enemigo3text = "El " + enemigo3.getName() + " enemigo ha causado " + enemigo3.getAtack() + " de daño!";
            ((MainGame) getActivity()).getPlayerLife().setProgress(player.getHealth());
            sb.append(enemigo3text + "\n");
        }

        txtStage.setText(sb);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("e1", enemigo1);
        outState.putSerializable("e2", enemigo2);
        outState.putSerializable("e3", enemigo3);
        outState.putInt("stage", actualStage);

        super.onSaveInstanceState(outState);
    }
}