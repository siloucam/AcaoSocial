package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.silascampos.acaosocial.R;

import java.io.File;

public class Instituicao_Activity extends AppCompatActivity {

    String photo_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("nome");
            TextView wordToGuess = (TextView) findViewById(R.id.nome);
            wordToGuess.setText(value);
            value = extras.getString("endereco");
            wordToGuess = (TextView) findViewById(R.id.endereco);
            wordToGuess.setText(value);
            value = extras.getString("contato");
            wordToGuess = (TextView) findViewById(R.id.contato);
            wordToGuess.setText(value);
            value = extras.getString("responsavel");
            wordToGuess = (TextView) findViewById(R.id.responsavel);
            wordToGuess.setText(value);
            value = extras.getString("descricao");
            wordToGuess = (TextView) findViewById(R.id.descricao);
            wordToGuess.setText(value);
            photo_value = extras.getString("foto");

            File imageFile;
            ImageView foto = (ImageView) findViewById(R.id.foto);


            //File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString());
            File extStorageDirectory = Environment.getExternalStorageDirectory();

            imageFile = new File(extStorageDirectory,photo_value);
            Bitmap mImageBitmap = BitmapFactory.decodeFile(String.valueOf(imageFile));
            Bitmap scaled = Bitmap.createScaledBitmap(mImageBitmap, 600, 250, true);
            foto.setImageBitmap(scaled);

            /*
            if(value.equals("logo_malonso.png")){
                foto.setBackgroundResource(R.drawable.logo_malonso);
            }else if(value.equals("logo_orfanato.png")){
                foto.setBackgroundResource(R.drawable.logo_orfanato);
            }*/

        }

        findViewById(R.id.call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView wordToGuess = (TextView) findViewById(R.id.contato);
                dialContactPhone((String) wordToGuess.getText());
            }
        });

        Button btn = (Button)findViewById(R.id.visitar);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuAgendar.class);
                TextView wordToGuess = (TextView) findViewById(R.id.nome);
                i.putExtra("nome",wordToGuess.getText());
                i.putExtra("foto",photo_value);
                startActivity(i);
            }});
    }

    private void dialContactPhone(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
