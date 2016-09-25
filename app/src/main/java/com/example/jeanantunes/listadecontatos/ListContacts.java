package com.example.jeanantunes.listadecontatos;

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

    public void setAttrsContacts(ArrayList<Contact> contactList) {
        JSONArray jsonArray = null;
        String rootPath = "/storage/emulated/legacy/Contacts/";

        try {
            jsonArray = getListContacts();

            int length_list = jsonArray.length();
            List<String> list_contact = new ArrayList<String>(length_list);

            for (int j = 0; j < length_list; j++) {
                JSONObject ob = jsonArray.getJSONObject(j);

                contactList.add(new Contact(ob.getString("Name"),
                        ob.getString("LastName"),
                        rootPath.concat(ob.getString("Photo")),
                        ob.getString("Email"),
                        ob.getString("Phone"),
                        ob.getString("CellPhone"),
                        ob.getString("Birthday")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
