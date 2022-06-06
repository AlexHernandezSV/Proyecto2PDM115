package com.example.proyecto2pdm115;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class administrador extends AppCompatActivity {
    EditText user, pass;
    ImageButton lapiz;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageButton googleBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        googleBtn = findViewById(R.id.singIn);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        lapiz=(ImageButton)findViewById(R.id.notas);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singIn();
            }
        });



        findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                administrador.this.startActivity(new Intent(administrador.this, principal.class));    }
        });
        findViewById(R.id.imageButton20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                administrador.this.startActivity(new Intent(administrador.this, RegistroSms.class));      }
        });
    }


    void singIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"No se pudo acceder a la cuenta",Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(administrador.this,principal.class);
        startActivity(intent);
    }

    public void login(View v) {
        AdminSQLiteOpenHelper lg = new AdminSQLiteOpenHelper(this,"usuario",null,1);
        SQLiteDatabase db = lg.getWritableDatabase();
        //devuelve 0 o 1 fila //es una consulta
        String lu = user.getText().toString();
        String lp = pass.getText().toString();
        Cursor fila = db.rawQuery("select username, password from usuario where password= "+lp, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)

            Toast.makeText(this, "Login Correcto",
            Toast.LENGTH_SHORT).show();
            Global.log="1";

            Intent i =new Intent(administrador.this,principal.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Login Fallido", Toast.LENGTH_SHORT).show();
            Global.log="0";
              }
        db.close();

    }
}
