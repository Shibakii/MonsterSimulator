package com.example.meaburro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class PopUpNombre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_layout);

        int ancho, alto;

        Button continuar = findViewById(R.id.siguienteActividad);
        TextView name = findViewById(R.id.charName);
        ImageView charImg = findViewById(R.id.imgPersonaje);

        // Asignando el tamaño del popup
        DisplayMetrics medidasVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(medidasVentana);
        ancho = medidasVentana.widthPixels;
        alto = medidasVentana.heightPixels;
        // Haciendo la ventana del popup
        getWindow().setLayout( (int) (ancho * 0.75), (int) (alto * 0.50));
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Intent intent = getIntent();
        Character player = (Character) intent.getSerializableExtra("player");

        charImg.setImageResource(player.getImg());

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals(""))
                {
                    player.setName(name.getText().toString());
                    Intent intent = new Intent(PopUpNombre.this, MainGame.class);
                    intent.putExtra("player", player);
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Tienes que escribir un nombre!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
