package com.baidu.track.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by baidu on 17/3/21.
 */

public class NetUtil {

    /**
     * 检测网络状态是否联通
     *
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm == null) {
                return false;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Network network = cm.getActiveNetwork();
                NetworkCapabilities networkCapabilities = cm.getNetworkCapabilities(network);
                if (networkCapabilities != null && networkCapabilities
                        .hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    return true;
                }
                return false;
            }

            NetworkInfo info = cm.getActiveNetworkInfo();
            if (null != info && info.isConnected() && info.isAvailable()) {
                return true;
            }
        } catch (Exception e) {
            Log.e(Constants.TAG, "current network is not available");
            return false;
        }
        return false;
    }
}
