package com.example.silascampos.acaosocial.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silascampos.acaosocial.R;
import com.example.silascampos.acaosocial.db.DAO;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Login extends Lifecycle {

    LoginButton bt_login;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);

        bt_login = (LoginButton)findViewById(R.id.login_button);
        bt_login.setReadPermissions(Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();

        bt_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult){
                SharedPreferences.Editor ed = sysPrefs.edit();
                ed.putString("loginType", "facebook");
                ed.commit();

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

                                user.setName(name);
                                user.setPhoto_url(photo_url);
                                user.setFirst_name(object.optString("first_name"));
                                user.setLast_name(object.optString("middle_name")+' '+object.optString("last_name"));
                                user.setEmail(object.optString("email"));
                                user.setGender(object.optString("gender"));
                                user.setBirthday(object.optString("birthday"));

                                DAO dao = null;
                                try {
                                    dao = new DAO(getApplicationContext());
                                    long id = dao.putUser(name,user.getEmail(),photo_url,"password",user.getFirst_name(),user.getLast_name(),user.getGender(),user.getBirthday());
                                    //Log.i("myTag","USER SAVED, ID = " + id);
                                    user.setId(id);
                                    user.updateUser(userPrefs,context);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.i("myTag","EXCEPTION");
                                }


                            }
                        });

                request.executeAsync();

                //Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_LONG).show();
                Intent it = new Intent(getApplicationContext(), Main_Activity.class);
                startActivity(it);
            }
            @Override
            public void onCancel() {
                //Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    };

    protected void onResume(){
        super.onResume();
        Log.i("myTag","Resume");
        if(isLoggedIn()){

            Intent it = new Intent(getApplicationContext(), Main_Activity.class);
            startActivity(it);
        }else{
            Log.i("myTag","Deslogado?");
        }
    }

    public void cleanPrefs() {
        SharedPreferences.Editor ed = userPrefs.edit();
        ed.clear();
        ed.commit();
        ed = sysPrefs.edit();
        ed.clear();
        ed.commit();
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }


}
