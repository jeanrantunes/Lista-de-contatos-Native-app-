package com.example.jeanantunes.listadecontatos;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jean.antunes on 07/09/2016.
 */
public class ListContacts {
    private File list;
    public ListContacts(File list) {
        this.list = list;
    }

    public JSONArray getListContacts() throws IOException, JSONException {
        String json_list = null;
        JSONArray contacts = null;
        FileInputStream stream = new FileInputStream(list);

        try {

            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            json_list = Charset.defaultCharset().decode(bb).toString();

        } finally {
            stream.close();
        }

        try {
            contacts = new JSONArray(json_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*retorna JSONArray com todos os contatos*/
        return contacts;
    }

    public void setAttrsContacts(final Context context, JSONArray jsonArray, ListView listView) {

        final ArrayList<Contact> contactList = new ArrayList<>();
        String rootPath = "/storage/emulated/legacy/Contacts/";

        try {
            JSONArray order = sortJson(jsonArray);
            int length_list = order.length();


            for (int j = 0; j < length_list; j++) {
                JSONObject ob = order.getJSONObject(j);

                contactList.add(new Contact(ob.getString("Name"),
                        ob.getString("LastName"),
                        rootPath.concat(ob.getString("Photo")),
                        ob.getString("Email"),
                        ob.getString("Phone"),
                        ob.getString("CellPhone"),
                        ob.getString("Birthday")));
            }
            AdapterListView ad = new AdapterListView(context, contactList);
            listView.setAdapter(ad);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int itemPosition = position;

                    Toast.makeText(context, "Nome: " + contactList.get(position).getName() + " " + contactList.get(position).getLastName() +
                            "\nE-mail: " + contactList.get(position).getEmail() +
                            "\nTelefone: " + contactList.get(position).getPhone() +
                            "\nCelular: " + contactList.get(position).getCellPhone() +
                            "\nAniversário: " + contactList.get(position).getBirthday(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private JSONArray sortJson(JSONArray array) throws JSONException {
        List<JSONObject> jsons = new ArrayList<JSONObject>();
        for (int i = 0; i < array.length(); i++) {
            jsons.add(array.getJSONObject(i));
        }
        Collections.sort(jsons, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject lhs, JSONObject rhs) {
                String lid = null;
                try {
                    lid = lhs.getString("Name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String rid = null;
                try {
                    rid = rhs.getString("Name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Here you could parse string id to integer and then compare.
                return lid.compareTo(rid);
            }
        });
        return new JSONArray(jsons);
    }
}
