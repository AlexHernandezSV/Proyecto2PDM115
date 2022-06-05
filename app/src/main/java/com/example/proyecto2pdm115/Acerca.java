package com.example.proyecto2pdm115;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;


public class Acerca extends AppCompatActivity {

    MapView map = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_acerca);

        Button btnCall = findViewById(R.id.btnCall);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);


        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(17);
        GeoPoint startPoint = new GeoPoint(13.721223111930575, -89.20026799383206);
        mapController.setCenter(startPoint);
        mapController.animateTo(startPoint);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setTitle("EISI UES");

        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);





        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String telefono = "tel:50370550326";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(telefono));
                startActivity(intent);
            }
        });

        ImageSlider imageSlider;


        imageSlider =findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel("https://www.diariofarma.com/wp-content/uploads/2018/12/Farmacia-Ja%C3%A9n_27.08.15-e1544535985370.jpg"));
        imageList.add(new SlideModel("https://muchosnegociosrentables.com/wp-content/uploads/2021/06/franquicias-de-farmacias-acompanamiento-personal.jpg"));
        imageList.add(new SlideModel("https://www.definicionabc.com/wp-content/uploads/2009/12/Farmacia.jpg"));

        imageSlider.setImageList(imageList,false);


        findViewById(R.id.imageButton12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Acerca.this.startActivity(new Intent(Acerca.this, principal.class));
            }
        });

    }
}
