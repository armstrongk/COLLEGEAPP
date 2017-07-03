package com.example.armstrong.college;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

/**
 * Created by Franc on 4/7/2017.
 */

public class ConnectivityReceiver extends BroadcastReceiver {
    Intent downloadposts, updateComments;
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = check.getActiveNetworkInfo();
        if (info.getState() == NetworkInfo.State.CONNECTED){

            String updateLink = "http://10.3.35.95/hive/update_product.php";
            //updateComments = new Intent(context, UpdateComments.class);
            updateComments.setData(Uri.parse(updateLink));
            context.startService(updateComments);

            String url = "http://10.3.35.95/hive/get_all_products.php";
            downloadposts = new Intent(context, Download_User_Posts.class);
            downloadposts.setData(Uri.parse(url));
            context.startService(downloadposts);
        }
    }
}
