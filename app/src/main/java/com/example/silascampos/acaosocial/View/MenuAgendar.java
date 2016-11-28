package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.silascampos.acaosocial.Model.Visita;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.Contracts;
import com.example.silascampos.acaosocial.db.DAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuAgendar extends Lifecycle {
    String value;
    String photo_value;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_menu_agendar);

        File imageFile;
        photo_value = extras.getString("foto");
        ImageView photo = (ImageView) findViewById(R.id.photo);

        //File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES.toString());
        File extStorageDirectory = Environment.getExternalStorageDirectory();

        imageFile = new File(extStorageDirectory,photo_value);
        Bitmap mImageBitmap = BitmapFactory.decodeFile(String.valueOf(imageFile));
        Bitmap scaled = Bitmap.createScaledBitmap(mImageBitmap, 70, 70, true);
        photo.setImageBitmap(scaled);
        
        value = extras.getString("nome");
        nome = extras.getString("nome");
        TextView wordToGuess = (TextView) findViewById(R.id.textView5);
        wordToGuess.setText(value);

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgendarVisita_Activity.class);
                i.putExtra("nome",nome);
                startActivity(i);
            }});
    }

    protected void onResume(){
        super.onResume();

        DAO dao = null;
        try {
            dao = new DAO(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.open("read");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_row, dao.getVisitas(nome),
                new String[]{"data","hora","n_pessoas"}, new int[] { R.id.tx_item,R.id.tx_item2,R.id.editText2});

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        dao.close();



        /*
        for(Visita v: visitas){

        }
        */

    }
}
