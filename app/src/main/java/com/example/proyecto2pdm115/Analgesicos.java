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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Analgesicos extends Activity {
    ListView list;
    Integer p;
    String SlectedItem, SlectedPrecio, valor;
    EditText captar;

    String[] item_name={
            "Amikacina",
            "Amoxicilina",
            "Azitromicina",
            "Gentamicina",
            "Neomicina",
            "Ertapenem"
    };

    String[] item_desc={
            "1 vial de 2ml" ,
            "Caja con 12 capsulas",
            "Caja con 3 tabletas" ,
            "1 vial de 2ml",
            "Caja con 12 tabletas",
            "1 vial de 1g"

    };

    String[] item_precio={
            "$20,00",
            "$15,75",
            "$18.99",
            "$25.00",
            "$18.75",
            "$30.00"


    };
    Integer[] img_id={
            R.drawable.amikacina,
            R.drawable.amoxicilina,
            R.drawable.azitromicina,
            R.drawable.gentamicina,
            R.drawable.neomicina,
            R.drawable.ertapenem,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analgesicos);


        list = (ListView) findViewById(R.id.listaVista);
        CustomListAdapter adaptador = new CustomListAdapter(this, item_name, item_desc, item_precio, img_id);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
               // valor=captar.getText().toString();

               new AlertDialog.Builder(Analgesicos.this)
                       .setTitle("Confirmacion de Agregacion")
                       .setMessage("Deseas agregar ese plato?")
                       .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                       SlectedItem = item_name[p];
                       SlectedPrecio = item_precio[p];

                       Intent i = new Intent(Analgesicos.this, Carrito.class);
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

        findViewById(R.id.imageButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Analgesicos.this.startActivity(new Intent(Analgesicos.this, categorias.class));
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
