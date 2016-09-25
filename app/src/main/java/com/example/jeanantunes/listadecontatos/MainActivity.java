package com.example.jeanantunes.listadecontatos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clearApplicationData();
        setContentView(R.layout.content_main);
        Context context = this;

        final ArrayList<Contact> contactList = new ArrayList<Contact>();

        ListView listView = (ListView) findViewById(R.id.contacts);

        ListContacts contacts = new ListContacts(new File("/storage/emulated/legacy/Contacts/list.json"));

        try {
            JSONArray list = contacts.getListContacts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        contacts.setAttrsContacts(contactList);

        listView.setAdapter(new AdapterListView(context, contactList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;

                Toast.makeText(getApplicationContext(), "Nome: "+ contactList.get(position).getName()+" "+contactList.get(position).getLastName()+
                      "\nEmail: "+ contactList.get(position).getEmail()+
                      "\nTelefone: "+contactList.get(position).getPhone()+
                      "\nCelular: "+contactList.get(position).getCellPhone()+
                      "\nAniversário: "+contactList.get(position).getBirthday() , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void clearApplicationData() {

        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }
        return deletedAll;
    }
}
