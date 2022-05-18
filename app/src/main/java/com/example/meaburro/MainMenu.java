package com.example.meaburro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView continuar = findViewById(R.id.start);
        continuar.setVisibility(View.INVISIBLE);

        ImageView imagenInicio = findViewById(R.id.icon_img);
        imagenInicio.setVisibility(View.INVISIBLE);

        TextView textoTitulo = findViewById(R.id.title_text);
        TextView copyright = findViewById(R.id.title_text_copyright);

        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        Animation fadeOneTime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_one);
        Animation fadeOneTime_finish = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_one_icon);

        textoTitulo.startAnimation(fadeOneTime);
        copyright.startAnimation(fadeOneTime);

        fadeOneTime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imagenInicio.setVisibility(View.VISIBLE);
                imagenInicio.setVisibility(View.VISIBLE);
                imagenInicio.startAnimation(fadeOneTime_finish);
                continuar.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        LinearLayout ll = findViewById(R.id.logInClic);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainMenu.this, PickCharacter.class);
                startActivity(intent);
                finish();

            }
        });

    }
}