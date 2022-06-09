package com.example.proyecto2pdm115;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Button;

import java.io.IOException;



public class categorias extends Activity {
    String cabron;
    MediaPlayer Media;
    Button Play;
    Button Stop;

    //camara
    Button abrirCamara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        //Codigo para el audio
        //setContentView(R.layout.activity_categorias);
        Play=(Button) findViewById(R.id.play);
        Stop=(Button) findViewById(R.id.stop);
        Play.setOnClickListener(onClick);
        Stop.setOnClickListener(onClick);
        Media=MediaPlayer.create(getApplicationContext(), R.raw.music);

       /* Bundle b=getIntent().getExtras();
        cabron=b.getString("elcoso");*/


        //codigo para abrir camara
        abrirCamara=(Button)findViewById(R.id.btnReceta);
        abrirCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(categorias.this,CamaraActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.porciones).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, Analgesicos.class));
            }
        });
        findViewById(R.id.baquita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, Varios.class));
            }
        });


        findViewById(R.id.atras).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, principal.class));
            }
        });

        findViewById(R.id.jugo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, Suplementos.class));
            }
        });
        findViewById(R.id.cangrejo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorias.this.startActivity(new Intent(categorias.this, Antibioticos.class));
            }
        });
    }

    View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (v.getId()==R.id.play){
                if (Media.isPlaying()){
                    Media.pause();
                    Play.setText("Play");
                }
                else{
                    Media.start();
                    Play.setText("Pause");
                }
            }
            else{
                Media.stop();
                Play.setText("Play");
                try{
                    Media.prepare();
                }
                catch(IllegalStateException e){
                    e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    };

    public String  pedido() {
       String  ped=cabron;
        return ped;
    }
}