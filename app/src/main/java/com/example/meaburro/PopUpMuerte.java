package com.example.meaburro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpMuerte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_muerte);

        int ancho, alto;

        TextView contador = findViewById(R.id.death_timer);

        CountDownTimer cdt = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                contador.setText("" + (int) ((l / 1000) % 60));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(PopUpMuerte.this, PickCharacter.class);
                startActivity(intent);
            }
        };

        cdt.start();

        // Asignando el tamaño del popup
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(medidasVentana);
        ancho = medidasVentana.widthPixels;
        alto = medidasVentana.heightPixels;
        // Haciendo la ventana del popup
        getWindow().setLayout( (int) (ancho * 0.75), (int) (alto * 0.50));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}