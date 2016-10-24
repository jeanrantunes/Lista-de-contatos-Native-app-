package com.example.jeanantunes.listadecontatos;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    long start = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Debug.startMethodTracing("calc");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        final ListView listView = (ListView) findViewById(R.id.contacts);

        ListContacts contacts = new ListContacts(new File("/storage/emulated/legacy/Contacts/list.json"));

        try {
            JSONArray list = contacts.getListContacts();
            contacts.setAttrsContacts(this, list, listView);
            long time = System.currentTimeMillis() - start;
            Toast.makeText(this, "Time: " + time / 1000.0, Toast.LENGTH_LONG).show();
            Log.i("Time program", "" + time / 1000.0);
            Debug.stopMethodTracing();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}
