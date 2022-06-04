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


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.util.Calendar;

public class Analgesicos extends Activity {
    ListView list;
    Integer p;
    String SlectedItem, SlectedPrecio, valor;
    EditText captar;

    Button TomarFoto;
    ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;

    String[] item_name={
            "Arroz con Camaron",
            "Camaron de Apanado-Tortilla de Camaron",
            "Camaron Reventado",
            "Camaron al Ajillo",
            "Camaron a la Plancha",
            "Camaron en Salsa de Mostaza"
    };

    String[] item_desc={
            "Arroz,papas fritas o patacones y guarniciones " ,
            "Papas fritas o arroz y mayonesa",
            "Reventado en mantequilla,arroz,papas fritas" ,
            "En salsa de aji y ajo ,papas fritas,arroz",
            "Arroz,papas salteadas con champinones  o papas fritas y guarniciones",
            "Camaron en cascara con mostaza ,patacones, y mayonesa"

    };

    String[] item_precio={
            "$3,50",
            "$6,50",
            "$9.00",
            "$1.00",
            "$1.50",
            "$8.50"


    };
    Integer[] img_id={
            R.drawable.arrozcamaron,
            R.drawable.tortilla,
            R.drawable.reventado,
            R.drawable.ajilll,
            R.drawable.plancha,
            R.drawable.salsa,

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analgesicos);

        TomarFoto = (Button) findViewById(R.id.mainbttomarfoto);
        image = (ImageView) findViewById(R.id.mainimage);
        TomarFoto.setOnClickListener(onClick);
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("Foto") != null) {
                image.setImageURI(Uri.parse(savedInstanceState
                        .getString("Foto")));
                file = Uri.parse(savedInstanceState.getString("Foto"));
            }
        }

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

    public void onSaveInstanceState(Bundle bundle){
        if (file!=null){
            bundle.putString("Foto", file.toString());
        }
        super.onSaveInstanceState(bundle);
    }
    View.OnClickListener onClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File photo =new File(Environment.getExternalStorageDirectory(),String.valueOf(Calendar.getInstance().getTimeInMillis())+".jpg");
            file=Uri.fromFile(photo);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent,FOTOGRAFIA);
        }
    };
    @Override
    public void onActivityResult(int RequestCode, int ResultCode, Intent intent) {
        if (RequestCode==FOTOGRAFIA){
            if(ResultCode == RESULT_OK){
                image.setImageURI(file);
            }
            else{
                Toast.makeText(getApplicationContext(), "fotografia No tomada",
                        Toast.LENGTH_SHORT).show();
            }
        }
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
