package com.example.silascampos.acaosocial.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silascampos.acaosocial.R;

public class Info extends Lifecycle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @Override
    protected void onResume(){
        super.onResume();
        restoreInfo();
    }

    protected void restoreInfo(){

        EditText tx_name = (EditText) findViewById(R.id.tx_name);
        EditText tx_email = (EditText) findViewById(R.id.tx_email);
        EditText tx_password = (EditText) findViewById(R.id.tx_password);

        if (user.exists()) {
            tx_name.setText(user.getName());
            tx_email.setText(user.getEmail());
            tx_password.setText(user.getPassword());
        }

    }

    public void save(View v){

        EditText tx_name = (EditText) findViewById(R.id.tx_name);

        EditText tx_email = (EditText) findViewById(R.id.tx_email);

        EditText tx_password = (EditText) findViewById(R.id.tx_password);

        SharedPreferences.Editor ed = userPrefs.edit();
        ed.putString("name", tx_name.getText().toString());
        ed.putString("email", tx_email.getText().toString());
        ed.putString("password", tx_password.getText().toString());
        ed.commit();

        Toast.makeText(getApplicationContext(),"Information Saved",Toast.LENGTH_LONG).show();

        finish();
    }

    public void cancel(View v){
        finish();
    }


}
