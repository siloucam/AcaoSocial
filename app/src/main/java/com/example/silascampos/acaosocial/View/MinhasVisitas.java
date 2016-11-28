package com.example.silascampos.acaosocial.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.DAO;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;

import org.json.JSONObject;

import java.io.IOException;

public class MinhasVisitas extends Lifecycle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_visitas);
    }

    protected void onResume() {
        super.onResume();

        ImageView photo = (ImageView) findViewById(R.id.photo);
        new DownloadImageTask(photo).execute(user.getPhoto_url());

        DAO dao = null;
        try {
            dao = new DAO(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao.open("read");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_row, dao.getVisitasByUser(user.getId()),
                new String[]{"data","hora","n_pessoas"}, new int[] { R.id.tx_item,R.id.tx_item2,R.id.editText2});
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        dao.close();



    }
}
