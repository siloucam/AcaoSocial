package com.example.silascampos.acaosocial;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Instituicao_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao_);

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

            ImageView foto = (ImageView) findViewById(R.id.foto);
            foto.setBackgroundResource(R.drawable.logo_orfanato);



            //The key argument here must match that used in the other activity
        }

        findViewById(R.id.call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView wordToGuess = (TextView) findViewById(R.id.contato);
                dialContactPhone((String) wordToGuess.getText());
            }
        });



    }

    private void dialContactPhone(String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
