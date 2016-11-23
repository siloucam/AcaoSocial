package com.example.silascampos.acaosocial.View;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silascampos.acaosocial.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveText extends Lifecycle {
    EditText ed_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_text);

        ed_text = (EditText) findViewById(R.id.ed_text);

        try {
            readTextFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void readTextFile() throws IOException {
        BufferedReader buffer;
        String text="";

        File file = new File(getApplicationContext().getFilesDir(), "text.txt");
        FileReader fr = null;
        fr = new FileReader(file);

        buffer = new BufferedReader(fr);
        String line;

        while ((line = buffer.readLine()) != null) {
            text += line+'\n';
        }

        ed_text.setText(text);
    }

    public void saveText(View v) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), "text.txt");
        FileWriter fr =  new FileWriter(file,false);
        fr.write(ed_text.getText().toString());
        fr.flush();
        fr.close();

        Toast.makeText(getApplicationContext(),"Text Saves",Toast.LENGTH_LONG).show();
        finish();
    }

    public void cancel(View v){
        finish();
    }


}
