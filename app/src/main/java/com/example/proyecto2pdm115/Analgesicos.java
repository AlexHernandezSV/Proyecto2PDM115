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
import java.util.ArrayList;
import java.util.Calendar;

public class Analgesicos extends AppCompatActivity implements View.OnClickListener {

    //============= Inicio voz =================
    ListView lv;
    static final int check=1111;
    Button Voice;
    //============= Fin voz =================


    ListView list;
    Integer p;
    String SlectedItem, SlectedPrecio, valor;
    EditText captar;

    Button TomarFoto;
    ImageView image;
    final int FOTOGRAFIA = 654;
    Uri file;


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

        //============= Inicio voz =================
        Voice=(Button) findViewById(R.id.bvoice);
        lv =(ListView) findViewById(R.id.lvVoiceReturn);
        Voice.setOnClickListener((View.OnClickListener) this);
        //============= Fin voz =================

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
        //============= Inicio voz =================
        if (RequestCode==check && ResultCode==RESULT_OK){
            ArrayList<String> results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if(results.indexOf("amikacina") == 0 || results.indexOf("amoxicilina" ) == 0 || results.indexOf("azitromicina") == 0 || results.indexOf("gentamicina") == 0 || results.indexOf("Neomicina") == 0 || results.indexOf("ertapenem") == 0)
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
