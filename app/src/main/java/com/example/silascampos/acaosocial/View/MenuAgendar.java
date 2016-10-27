package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.silascampos.acaosocial.Model.Visita;
import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.DAO;

import java.util.ArrayList;

public class MenuAgendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_agendar);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("nome");
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

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("nome");

        DAO dao = new DAO(getApplicationContext());
        ArrayList<Visita> visitas = dao.getVisitas(value);

        for(Visita v: visitas){

        }

    }
}
