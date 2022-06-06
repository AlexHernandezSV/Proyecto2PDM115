package com.example.proyecto2pdm115;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;


public class principal extends Activity {
    String captar;
    EditText verga;
    ImageView imageView;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName =acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }
        else{
            name.setVisibility(View.INVISIBLE);
            email.setVisibility(View.INVISIBLE);
        }

        //agregar imagen a la prestania principal
        imageView=findViewById(R.id.imagen1702);
        String url="https://www.contigoentufarmacia.com/arxius/imatgesbutlleti/farmaceutico_asesor_salud_500x500.gif";
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageView);


        ImageButton lapiz=(ImageButton)findViewById(R.id.notas);
        lapiz.setVisibility(View.INVISIBLE);
        if(Global.log.equals("1")||acct!=null){
            lapiz.setVisibility(View.VISIBLE);
        }

        findViewById(R.id.Mesa1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.ivar1 = "1";
                Intent i = new Intent(principal.this, categorias.class);
                // i.putExtra("elcoso", captar);
                startActivity(i);
            }
        });
        findViewById(R.id.Mesa2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.ivar1 = "2";
                Intent i = new Intent(principal.this, categorias.class);
                startActivity(i);
            }
        });
        findViewById(R.id.Mesa3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "3";
            }
        });
        findViewById(R.id.Mesa4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "4";
            }
        });
        findViewById(R.id.Mesa5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, categorias.class));
                Global.ivar1 = "5";
            }
        });

        findViewById(R.id.home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, administrador.class));
            }
        });

        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, Acerca.class));
            }
        });
        findViewById(R.id.notas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principal.this.startActivity(new Intent(principal.this, Notas.class));
            }
        });


    }
}
