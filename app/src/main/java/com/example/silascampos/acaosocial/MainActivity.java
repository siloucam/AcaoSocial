package com.example.silascampos.acaosocial;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    PopupWindow popup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configure Markers and POPUP
        final Instituicao button1 = (Instituicao)findViewById(R.id.button1);
        button1.setNome("Asilo dos Idosos de Vitória");

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

                //Set Text to correspondent i
                TextView txt = (TextView)popupView.findViewById(R.id.popup_nome);
                txt.setText(button1.getNome());

                popup.showAsDropDown(button1, 0, 0);
            }});

        //Configure Markers and POPUP on button 2
        final Instituicao button2 = (Instituicao)findViewById(R.id.button2);
        button2.setNome("Instituição 2");

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
