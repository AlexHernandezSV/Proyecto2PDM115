package com.example.proyecto2pdm115;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class parrillada extends AppCompatActivity implements View.OnClickListener {

    //============= Inicio voz =================
    ListView lv;
    static final int check=1111;
    Button Voice;
    //============= Fin voz =================

    ListView list;
    String SlectedItem, SlectedPrecio;
    Integer p;
    String[] item_name={
            "Alcohol",
            "Curas",
            "Gazas",
            "Mascarillas",
            "Condones"


    };

    String[] item_desc={
            "500 ml",
            "20 unidades",
            "50 unidades",
            "50 unidades",
            "3 unidades"


    };

    String[] item_precio={
            "$9,99",
            "$5,25",
            "$11,99",
            "10,00",
            "3.50"


    };
    Integer[] img_id={
            R.drawable.alcohol,
            R.drawable.curas,
            R.drawable.gazas,
            R.drawable.mascarillas,
            R.drawable.condones

    };
    Boolean[] eleccion={
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parrillada);

        //============= Inicio voz =================
        Voice=(Button) findViewById(R.id.bvoice);
        lv =(ListView) findViewById(R.id.lvVoiceReturn);
        Voice.setOnClickListener((View.OnClickListener) this);
        //============= Fin voz =================


        // EditText mas = (EditText)findViewById(R.id.mas);
        list = (ListView) findViewById(R.id.parrilla);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                new AlertDialog.Builder(parrillada.this).setTitle("Confirmacion de Agregacion").setMessage("Deseas agregar ese Medicamento?").setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                //        Toast.makeText(parrillada.this,"si",Toast.LENGTH_LONG).show();

                        SlectedItem = item_name[p];
                        SlectedPrecio = item_precio[p];

                        Intent i = new Intent(parrillada.this, Carrito.class);
                        i.putExtra("itemn", item_name[p].toString());
                        i.putExtra("itemd", item_desc[p].toString());
                        i.putExtra("itemp", item_precio[p].toString());
                        i.putExtra("itemi", img_id[p]);
                        finish();
                        startActivity(i);
                        GuardaItem();
                    }
                }).setNegativeButton("No!",null).show();
                //   Toast toast = Toast.makeText(mariscos.this,position+ " Item: " + SlectedItem, Toast.LENGTH_LONG);
                //   toast.show();
                //   item_precio.equals("S0");
            }
        });
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parrillada.this.startActivity(new Intent(parrillada.this, categorias.class));
            }
        });

    }

    //============= Inicio voz =================
    public void onClick(View v) {
        if (v.getId() == R.id.bvoice) {
// Si entramos a dar clic en el boton
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hable ahora ");
            startActivityForResult(i, check);
        }
    }
    //============= Fin voz =================

    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {

        //============= Inicio voz =================
        if (RequestCode==check && ResultCode==RESULT_OK){
            ArrayList<String> results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if(results.indexOf("alcohol") == 0 || results.indexOf("curas" ) == 0 || results.indexOf("gasas") == 0 || results.indexOf("mascarillas") == 0 || results.indexOf("condones") == 0)
            {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Medicamento en existencia", Toast.LENGTH_SHORT);

                toast1.show();
            }
            else
            {
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "Medicamento no encontrado, inténtalo nuevamente", Toast.LENGTH_SHORT);

                toast2.show();
            }

            lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,results));
        }
        super.onActivityResult(RequestCode, ResultCode, intent);

    }
    public void onDestroy(){
        super.onDestroy();
    }
    //============= Fin voz =================

    public void GuardaItem() {
        AdminSQLiteOpenHelper guarda = new AdminSQLiteOpenHelper(this,"item",null,1);
        SQLiteDatabase base =guarda.getWritableDatabase();

        String hola = Global.ivar1;

        //es una clase para guardar datos
        ContentValues guardar_item =new ContentValues();
        guardar_item.put("descripcion",SlectedItem);
        guardar_item.put("precio",SlectedPrecio);
        guardar_item.put("pedido",hola);

        base.insert("item",null,guardar_item);
        base.close();
        Toast.makeText(this,"Item Guardado: "+SlectedItem+" "+hola, Toast.LENGTH_SHORT).show();


    }


}