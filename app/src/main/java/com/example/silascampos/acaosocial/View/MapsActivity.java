package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.example.silascampos.acaosocial.Model.Instituicao;
import com.example.silascampos.acaosocial.R;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silascampos.acaosocial.db.DAO;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sa90.materialarcmenu.ArcMenu;
import com.sa90.materialarcmenu.StateChangeListener;

import java.io.IOException;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DAO dao;
    private Spinner spCategories;
    public Instituicao sjbatista;
    public Instituicao malonso;

    PopupWindow popup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        malonso = new Instituicao("logo_malonso.png", "Sociedade Cultural e Beneficente Mons. Alonso", "Lar de idosos. Abriga atualmente cerca de 10 a 15 idosos.", "Rua 23 de Maio, 359. Parque Moscoso. Vitória-ES CEP 29018-615", "", "02732231936", "Frei Marcos", -20.3179623, -40.3422072, Instituicao.ASILO);
        sjbatista = new Instituicao("logo_orfanato.png", "Casa Menino São João Batista", "Orfanato que abriga atualmente cerca de 10 crianças de 0 a 4 anos.", "Rua J, Quadra 32, nº5, Bairro Manoel Plaza. Serra-ES", "", "02732813701", "", -20.2312145, -40.2683045, Instituicao.ORFANATO);

        DAO dao = null;
        try {
            dao = new DAO(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.putInstituicao(malonso.getNome(), malonso.getFoto(), malonso.getDescricao(), malonso.getEndereco(), malonso.getDoacoes(), malonso.getContato(), malonso.getResponsavel(), malonso.getLatitude(), malonso.getLongitude(), malonso.getCategory());
        dao.putInstituicao(sjbatista.getNome(), sjbatista.getFoto(), sjbatista.getDescricao(), sjbatista.getEndereco(), sjbatista.getDoacoes(), sjbatista.getContato(), sjbatista.getResponsavel(), sjbatista.getLatitude(), sjbatista.getLongitude(), sjbatista.getCategory());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("Map", "Ready");
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                if (popup != null) {
                    popup.dismiss();
                    popup = null;
                }
            }
        });

        LatLng vitoria = new LatLng(-20.28218807020176, -40.32118996649758);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vitoria, 11));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.clear();

        try {
            dao = new DAO(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("myTag", "1");
        ArrayList<Instituicao> locations = new ArrayList<>();
        locations = dao.getLocations();

        if (locations == null) {
            Log.i("myTag", "Cade as parada?");
        } else {
            Log.i("myTag", "tem pelo menos " + locations.get(0).getNome() + "com lat:" + locations.get(0).getLatitude() + " log:" + locations.get(0).getLongitude());
        }


        for (Instituicao l : locations) {

            switch (l.getCategory()) {
                case Instituicao.ASILO:
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(l.getLatitude(), l.getLongitude()))
                            .title(l.getNome())
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.asiloicon))
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("asiloicon", 70, 100)))
                    );
                    break;
                case Instituicao.ABRIGO:

                    break;
                case Instituicao.ORFANATO:
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(l.getLatitude(), l.getLongitude()))
                            .title(l.getNome())
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.asiloicon))
                            .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("orfanatoicon", 70, 100)))
                    );
                    break;
            }

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(Marker arg0) {
                    if (arg0.getTitle().equals("Sociedade Cultural e Beneficente Mons. Alonso")) {

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arg0.getPosition(), 17));

                        LayoutInflater layoutInflater
                                = (LayoutInflater) getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.activity_popup_, null);
                        if (popup != null) {
                            popup.dismiss();
                            popup = null;
                        }
                        popup = new PopupWindow(
                                popupView,
                                ActionBar.LayoutParams.WRAP_CONTENT,
                                ActionBar.LayoutParams.WRAP_CONTENT);

                        //Popup Button Click
                        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                popup.dismiss();
                                Intent i = new Intent(getApplicationContext(), Instituicao_Activity.class);
                                i.putExtra("nome", malonso.getNome());
                                i.putExtra("endereco", malonso.getEndereco());
                                i.putExtra("contato", malonso.getContato());
                                i.putExtra("foto", malonso.getFoto());
                                i.putExtra("responsavel", malonso.getResponsavel());
                                i.putExtra("descricao", malonso.getDescricao());
                                i.putExtra("foto", malonso.getFoto());
                                startActivity(i);
                            }
                        });

                        //Set data to correspondent i
                        TextView txt = (TextView) popupView.findViewById(R.id.popup_nome);
                        txt.setText(malonso.getNome());
                        ImageView foto = (ImageView) popupView.findViewById(R.id.foto);
                        foto.setBackgroundResource(R.drawable.logo_malonso);

                        ImageView center = (ImageView) findViewById(R.id.center);
                        popup.showAsDropDown(center, 0, 0);
                    }

                    if (arg0.getTitle().equals("Casa Menino São João Batista")) {

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arg0.getPosition(), 17));

                        LayoutInflater layoutInflater
                                = (LayoutInflater) getBaseContext()
                                .getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = layoutInflater.inflate(R.layout.activity_popup_, null);
                        if (popup != null) {
                            popup.dismiss();
                            popup = null;
                        }
                        popup = new PopupWindow(
                                popupView,
                                ActionBar.LayoutParams.WRAP_CONTENT,
                                ActionBar.LayoutParams.WRAP_CONTENT);

                        //Popup Button Click
                        Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                popup.dismiss();
                                Intent i = new Intent(getApplicationContext(), Instituicao_Activity.class);
                                i.putExtra("nome", sjbatista.getNome());
                                i.putExtra("endereco", sjbatista.getEndereco());
                                i.putExtra("contato", sjbatista.getContato());
                                i.putExtra("foto", sjbatista.getFoto());
                                i.putExtra("responsavel", sjbatista.getResponsavel());
                                i.putExtra("descricao", sjbatista.getDescricao());
                                i.putExtra("foto", sjbatista.getFoto());
                                startActivity(i);
                            }
                        });

                        //Set data to correspondent i
                        TextView txt = (TextView) popupView.findViewById(R.id.popup_nome);
                        txt.setText(sjbatista.getNome());
                        ImageView foto = (ImageView) popupView.findViewById(R.id.foto);
                        foto.setBackgroundResource(R.drawable.logo_orfanato);

                        ImageView center = (ImageView) findViewById(R.id.center);
                        popup.showAsDropDown(center, 0, 0);
                    }
                    return true;
                }

            });


        }

        final ArcMenu arcmenu = (ArcMenu) findViewById(R.id.arcMenu);
        arcmenu.setStateChangeListener(new StateChangeListener() {
            @Override
            public void onMenuOpened() {
                //Snackbar.make(arcmenu, "Menu Opened", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuClosed() {

            }
        });

    }

    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public void OpenCard(View v) {
        callCard();
    }

    public void callCard() {
        Intent it = new Intent(this, Card.class);
        startActivity(it);
    }


}
