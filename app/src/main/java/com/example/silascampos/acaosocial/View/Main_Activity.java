package com.example.silascampos.acaosocial.View;

import android.content.Intent;
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

import com.example.silascampos.acaosocial.Fragments.PopupClickListener;
import com.example.silascampos.acaosocial.Model.Instituicao;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.DAO;

public class Main_Activity extends AppCompatActivity {

    PopupWindow popup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configure Markers and POPUP on button1
        final Instituicao button1 = (Instituicao)findViewById(R.id.button1);
        button1.setNome("Sociedade Cultural e Beneficente Mons. Alonso");
        button1.setEndereco("Rua 23 de Maio, 359. Parque Moscoso. Vitória-ES CEP 29018-615");
        button1.setContato("02732231936");
        button1.setFoto("logo_malonso.png");
        button1.setResponsavel("Frei Marcos");
        button1.setDescricao("Lar de idosos. Abriga atualmente cerca de 10 a 15 idosos.");

        DAO dao = new DAO(getApplicationContext());
        dao.putInstituicao(button1.getNome(),button1.getFoto(),button1.getDescricao(),button1.getEndereco(),button1.getDoacoes(),button1.getContato(),button1.getResponsavel());


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
                        Intent i = new Intent(getApplicationContext(), Instituicao_Activity.class);
                        i.putExtra("nome",button1.getNome());
                        i.putExtra("endereco",button1.getEndereco());
                        i.putExtra("contato",button1.getContato());
                        i.putExtra("foto",button1.getFoto());
                        i.putExtra("responsavel",button1.getResponsavel());
                        i.putExtra("descricao",button1.getDescricao());
                        i.putExtra("foto",button1.getFoto());
                        startActivity(i);
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
        button2.setEndereco("Rua J, Quadra 32, nº5, Bairro Manoel Plaza. Serra-ES");
        button2.setContato("02732813701");
        button2.setFoto("logo_orfanato.png");
        button2.setResponsavel("");
        button2.setDescricao("Orfanato que abriga atualmente cerca de 10 crianças de 0 a 4 anos.");

        dao.putInstituicao(button2.getNome(),button2.getFoto(),button2.getDescricao(),button2.getEndereco(),button2.getDoacoes(),button2.getContato(),button2.getResponsavel());

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
                        Intent i = new Intent(getApplicationContext(), Instituicao_Activity.class);
                        i.putExtra("nome",button2.getNome());
                        i.putExtra("endereco",button2.getEndereco());
                        i.putExtra("contato",button2.getContato());
                        i.putExtra("foto",button2.getFoto());
                        i.putExtra("responsavel",button2.getResponsavel());
                        i.putExtra("descricao",button2.getDescricao());
                        i.putExtra("foto",button2.getFoto());
                        startActivity(i);
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
