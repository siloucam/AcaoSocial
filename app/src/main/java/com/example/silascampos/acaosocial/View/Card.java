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
                                user.setPhoto_url(photo_url);
                                new DownloadImageTask(photo).execute(photo_url);

                                TextView tx_id = (TextView) findViewById(R.id.tx_id);
                                TextView tx_first_name = (TextView) findViewById(R.id.tx_first_name);
                                TextView tx_last_name = (TextView) findViewById(R.id.tx_last_name);
                                TextView tx_email = (TextView) findViewById(R.id.tx_email);
                                TextView tx_gender = (TextView) findViewById(R.id.tx_gender);
                                TextView tx_birthday = (TextView) findViewById(R.id.tx_birthday);

                                tx_id.setText(object.optString("id"));
                                tx_first_name.setText(object.optString("first_name"));
                                tx_last_name.setText(object.optString("middle_name")+' '+object.optString("last_name"));
                                tx_email.setText(object.optString("email"));
                                tx_gender.setText(object.optString("gender"));
                                tx_birthday.setText(object.optString("birthday"));

                                user.setId(tx_id.getText().toString());
                                user.setFirst_name(tx_first_name.getText().toString());
                                user.setLast_name(tx_last_name.getText().toString());
                                user.setEmail(tx_email.getText().toString());
                                user.setGender(tx_gender.getText().toString());
                                user.setBirthday(tx_birthday.getText().toString());

                                user.updateUser(userPrefs,context);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, middle_name, last_name, email,gender, birthday");
        request.setParameters(parameters);
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
        finish();
    }

}

