package com.example.proyecto2pdm115;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class Notas extends Activity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_notas);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        findViewById(R.id.pedido1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "1";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.pedido2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "2";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.pedido3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "3";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.pedido4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "4";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.pedido5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Notas.this, Pedido.class);
                String eso = "5";
                i.putExtra("eso", eso);
                startActivity(i);
            }
        });
        findViewById(R.id.imageButton6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas.this.startActivity(new Intent(Notas.this, principal.class));
            }
        });


    }
    public void bloq(View v){
        Global.log="0";
        Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Notas.this, principal.class);
        startActivity(i);
        singOut();
    }

    void singOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();

            }
        });
    }

}
