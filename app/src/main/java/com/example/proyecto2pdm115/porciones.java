package com.example.proyecto2pdm115;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class porciones extends Activity {
   ListView list;
   String SlectedItem, SlectedPrecio;
   Integer p;
   principal pr;
    String[] item_name={
            "Acetaminofen",
            "Antigripal",
            "Ibuprofeno",
            "Paracetamol",
            "Pepto Bismol",
            "Tabcin",
            "Naproxeno"

    };

    String[] item_desc={
            "2 blisters de 10 tabletas",
            "1 blister de 10 tabletas",
            "5 blisters de 10 tabletas",
            "2 blisters de 8 tabletas",
            "Frasco de 473 ml",
            "60 tabletas efervescentes",
            "10 tabletas"
    };

    String[] item_precio={
            "$5,00",
            "$4.75",
            "$8,75",
            "$5,25",
            "$15,75",
            "$10,50",
            "$6,00"

    };
    Integer[] img_id={

            R.drawable.acetaminofen,
            R.drawable.antigripal,
            R.drawable.ibuprofeno,
            R.drawable.paracetamol,
            R.drawable.peptobismol,
            R.drawable.tabcin,
            R.drawable.naproxeno,
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
        setContentView(R.layout.activity_porciones);

        list = (ListView) findViewById(R.id.listaPorciones);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                new AlertDialog.Builder(porciones.this).setTitle("Confirmacion de Agregacion").setMessage("Deseas agregar ese plato?").setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //  Toast.makeText(porciones.this,"si",Toast.LENGTH_LONG).show();

                        SlectedItem = item_name[p];
                        SlectedPrecio = item_precio[p];

                        Intent i = new Intent(porciones.this, Carrito.class);
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
        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                porciones.this.startActivity(new Intent(porciones.this, categorias.class));
            }
        });
    }
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