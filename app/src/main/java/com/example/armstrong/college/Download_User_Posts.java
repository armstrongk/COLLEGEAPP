package com.example.armstrong.college;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */

public class Download_User_Posts extends IntentService {
    SQLiteDatabase storeData;
    String last;

    public Download_User_Posts() {
        super("Download_User_Posts");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String url_loc = intent.getDataString();
        last = getLastId();
        if (last != null)
            url_loc += last;
        else
            url_loc += "0";

        System.out.println(url_loc);
        savePosts(getPosts(url_loc));

    }

    public String getPosts(String link){
        String jsonPosts  = "";
        try{
            ConnectivityManager check = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = check.getActiveNetworkInfo();
            if (info.getState() == NetworkInfo.State.CONNECTED){
                URL url = new URL(link);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestMethod("GET");

                int statusCode = conn.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode == 200) {
                    InputStream inputStream = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String data="";
                    while ((data = reader.readLine()) != null){
                        jsonPosts += data+"\n";
                    }
                    inputStream.close();
                } else {
                    System.out.println("Failed to fetch data!!");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return jsonPosts;
    }

    public void savePosts(String jsonPost){
        JSONArray posts = null;
        JSONObject post = null;
        String[] cred = new String[5];
        storeData = openOrCreateDatabase("poultryDB",MODE_PRIVATE,null);
        try {
            posts = new JSONArray(jsonPost);
            storeData.execSQL("CREATE TABLE IF NOT EXISTS forum_posts(id int AUTO_INCREMENT primary key, sender varchar(100), question text, sdate datetime, comm int);");

            for (int i = 0; i < posts.length(); i++){
                post = new JSONObject(posts.get(i).toString());
                cred[0] = post.optString("id");
                cred[1] = post.optString("name");
                cred[2] = post.optString("question");
                cred[3] = post.optString("sdate");
                cred[4] = post.optString("number");

                storeData.execSQL("INSERT INTO forum_posts VALUES('"+cred[0]+"','"+cred[1]+"','"+cred[2]+"','"+cred[3]+"','"+cred[4]+"');");
            }

            System.out.println("finish");
        } catch (JSONException e) {
            System.out.println(e);
        }
        storeData.close();
    }

    public String getLastId(){
        storeData = openOrCreateDatabase("poultryDB",MODE_PRIVATE,null);
        try {
            Cursor getMax = storeData.rawQuery("SELECT max(id) FROM forum_posts;", null);
            getMax.moveToFirst();
            storeData.close();
            return getMax.getString(0);
        }catch (Exception e) {
            storeData.close();
            return null;
        }
    }
}
