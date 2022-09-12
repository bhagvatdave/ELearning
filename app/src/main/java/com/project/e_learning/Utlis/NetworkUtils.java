package com.project.e_learning.Utlis;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.project.e_learning.R;


public class NetworkUtils {

    private static NetworkInfo getNetworkInfo(@NonNull Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static Boolean isConnected(Context context)  {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    public static Boolean isConnectedWifi(Context context)  {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            return info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    public static Boolean isConnectedMobile(Context context)  {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            return info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    public static Boolean isConnectedFast(Context context)  {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            return info.isConnected() && isConnectionFast(info.getType(), info.getSubtype());
        }
        return false;
    }

//    public static void showNoNetworkDialog(Context context,String title,String description) {
//        final AlertDialog.Builder noNetworkDialog = new AlertDialog.Builder(context);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.no_internet_dialog, null);
//        noNetworkDialog.setView(view);
//        TextView tvtitle = view.findViewById(R.id.tv_info_message);
//        tvtitle.setText(title);
//        TextView tvdescription = view.findViewById(R.id.tv_extra_info_message);
//        tvdescription.setText(description);
//        final AlertDialog dialog = noNetworkDialog.create();
//        dialog.show();
//        dialog.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
//        Button btnDone = view.findViewById(R.id.btn_done);
//        btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//    }

    private static boolean isConnectionFast(int type, int subtype) {
        Boolean answer = false;
        if (type==ConnectivityManager.TYPE_WIFI){
            answer = true;
        }else if (type==ConnectivityManager.TYPE_MOBILE) {
            switch (type){
                case TelephonyManager.NETWORK_TYPE_1xRTT: answer = false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_CDMA: answer = false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE: answer = false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0: answer = true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A: answer = true; // ~ 600-1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS: answer = false; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA: answer = true; // ~ 2-14 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPA: answer = true; // ~ 700-1700 kbps
                case TelephonyManager.NETWORK_TYPE_HSUPA: answer = true; // ~ 1-23 Mbps
                case TelephonyManager.NETWORK_TYPE_UMTS: answer = true; // ~ 400-7000 kbps
                case TelephonyManager.NETWORK_TYPE_EHRPD: answer = true; // ~ 1-2 Mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: answer = true; // ~ 5 Mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP: answer = true; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: answer = false; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: answer = true; // ~ 10+ Mbps
                case TelephonyManager.NETWORK_TYPE_UNKNOWN: answer = false;
                default:
                  break;
            }
        }
      return answer;
    }

}
