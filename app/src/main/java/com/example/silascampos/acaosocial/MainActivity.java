package com.example.silascampos.acaosocial;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PopupWindow popup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configure Markers and POPUP on button1
        final Instituicao button1 = (Instituicao)findViewById(R.id.button1);
        button1.setNome("Sociedade Cultural e Beneficente Mons. Alonso");
        button1.setEndereco("Rua 23 de Maio, 359. Parque Moscoso. Vitória-ES CEP 29018-615");
        button1.setContato("(27) 3223-1936");
        button1.setFoto("logo_malonso");

        button1.setOnClickListener(new PopupClickListener(popup){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_popup_, null);
                if(popup!=null){popup.dismiss();popup=null;}
                popup = new PopupWindow(
                        popupView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT);

                //Popup Button Click
                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popup.dismiss();
                    }});

                //Set data to correspondent i
                TextView txt = (TextView)popupView.findViewById(R.id.popup_nome);
                txt.setText(button1.getNome());
                ImageView foto = (ImageView)popupView.findViewById(R.id.foto);
                foto.setBackgroundResource(R.drawable.logo_malonso);

                popup.showAsDropDown(button1, 0, 0);
            }});

        //Configure Markers and POPUP on button2
        final Instituicao button2 = (Instituicao)findViewById(R.id.button2);
        button2.setNome("Casa Menino São João Batista");
        button1.setEndereco("Rua J, Quadra 32, nº5, Bairro Manoel Plaza. Serra-ES");
        button1.setContato("(27) 3281-3701");
        button1.setFoto("logo_malonso");

        button2.setOnClickListener(new PopupClickListener(popup){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_popup_, null);
                if(popup!=null){popup.dismiss();popup=null;}
                popup = new PopupWindow(
                        popupView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT);

                //Popup Button Click
                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popup.dismiss();
                    }});

                //Set Text to correspondent i
                TextView txt = (TextView)popupView.findViewById(R.id.popup_nome);
                txt.setText(button2.getNome());
                ImageView foto = (ImageView)popupView.findViewById(R.id.foto);
                foto.setBackgroundResource(R.drawable.logo_orfanato);

                popup.showAsDropDown(button2, 0, 0);
            }});

        //Map Click
        RelativeLayout app_layer = (RelativeLayout) findViewById (R.id.Map);
        app_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popup!=null){popup.dismiss();popup=null;}
            }
        });

    }
};
