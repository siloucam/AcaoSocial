package com.example.silascampos.acaosocial.Model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by novaes on 9/30/16.
 */
public class User {
    String name;
    String email;
    String photo_url;
    String password;
    long id;
    String first_name;
    String last_name;
    String gender;
    String birthday;

    public User(){
        this.name = "";
        this.email = "";
        this.photo_url = "";
        this.password = "";

        this.id = 0;
        this.first_name= "";
        this.last_name = "";
        this.gender = "";
        this.birthday = "";
    };

    public User(long id, String name, String photo_url){
        this.setId(id);
        this.setName(name);
        this.setPhoto_url(photo_url);
    }

    public void updateUser(SharedPreferences prefs, Activity context){
        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("name",this.first_name+' '+this.last_name);
        ed.putString("email",this.email);
        ed.putString("photo_url",this.photo_url);
        ed.putLong("id",this.id);
        ed.putString("first_name",this.first_name);
        ed.putString("last_name",this.last_name);
        ed.putString("gender",this.gender);
        ed.putString("birthday",this.birthday);
        ed.commit();
        //Toast.makeText(context, "Local User Info Updated", Toast.LENGTH_LONG).show();
    }

    public User restoreUser(SharedPreferences prefs){
        this.setEmail(prefs.getString("email", ""));
        this.setPassword(prefs.getString("password", ""));
        this.setName(prefs.getString("name", ""));
        this.setId(prefs.getLong("id",0));
        this.setPhoto_url(prefs.getString("photo_url", ""));
        return this;
    }

    public Boolean exists(){
        if (email != "") return true;
        return false;
    }

    public Boolean auth(String email, String password){
        if(this.email.equals(email) && this.password.equals(password)) return true;
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
