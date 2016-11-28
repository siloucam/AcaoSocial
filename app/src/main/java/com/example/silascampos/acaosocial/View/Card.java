package com.example.silascampos.acaosocial.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.silascampos.acaosocial.R;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Card extends Lifecycle {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();

        setContentView(R.layout.activity_card_facebook);
        Log.i("Estado", sysPrefs.getString("loginType",""));

        restoreInfoFacebook();
    }

    protected void restoreInfoFacebook(){

        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                                Profile profile = Profile.getCurrentProfile();

                                String name = profile.getName();
                                String photo_url = String.valueOf(profile.getProfilePictureUri(300, 300));

                                TextView tx_name = (TextView) findViewById(R.id.tx_name);
                                ImageView photo = (ImageView) findViewById(R.id.photo);

                                tx_name.setText(name);
                                new DownloadImageTask(photo).execute(photo_url);
                    }
                });
        request.executeAsync();
    }

    public void updateUser(View v){

    }

    public void updateText(View v){
        Intent it = new Intent(this, SaveText.class);
        startActivity(it);
    }

    public void info(View v){
        Intent it = new Intent(this, Info.class);
        startActivity(it);
    }

    public void logout(View v){
        LoginManager.getInstance().logOut();

        Intent it = new Intent(getApplicationContext(), Login.class);
        startActivity(it);

        finish();
    }

}

