package com.example.silascampos.acaosocial.View;

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
import android.widget.Toast;

import com.example.silascampos.acaosocial.R;
import com.facebook.FacebookSdk;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

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
                Toast.makeText(getApplicationContext(), "SUCESS", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor ed = sysPrefs.edit();
                ed.putString("loginType", "facebook");
                ed.commit();

                Intent it = new Intent(getApplicationContext(), Main_Activity.class);
                startActivity(it);
            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    };

    public void cleanPrefs() {
        SharedPreferences.Editor ed = userPrefs.edit();
        ed.clear();
        ed.commit();
        ed = sysPrefs.edit();
        ed.clear();
        ed.commit();
    }


}
