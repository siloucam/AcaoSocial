package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.silascampos.acaosocial.Model.Visita;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.Contracts;
import com.example.silascampos.acaosocial.db.DAO;

import java.io.IOException;
import java.util.ArrayList;

public class MenuAgendar extends AppCompatActivity {
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_agendar);

        Bundle extras = getIntent().getExtras();
        value = extras.getString("nome");
        TextView wordToGuess = (TextView) findViewById(R.id.textView5);
        wordToGuess.setText(value);

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AgendarVisita_Activity.class);
                TextView wordToGuess = (TextView) findViewById(R.id.textView5);
                i.putExtra("nome",wordToGuess.getText());
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

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_row, dao.getVisitas(value),
                new String[]{"data","hora","n_pessoas","_id"}, new int[] { R.id.tx_item,R.id.tx_item2,R.id.editText2});

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        dao.close();

        /*
        for(Visita v: visitas){

        }
        */

    }
}
