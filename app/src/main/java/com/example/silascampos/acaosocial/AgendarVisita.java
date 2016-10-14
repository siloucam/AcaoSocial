package com.example.silascampos.acaosocial;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class AgendarVisita extends TabActivity{

    TabHost TabHostWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign id to Tabhost.
        TabHostWindow = (TabHost)findViewById(R.id.tabhost);

        //Creating tab menu.
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");

        //Setting up tab 1 name.
        TabMenu1.setIndicator("Tab1");
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this,TabActivity_1.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator("Tab2");
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this,TabActivity_2.class));

        //Adding tab1, tab2, tab3 to tabhost view.
        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
    }
}
