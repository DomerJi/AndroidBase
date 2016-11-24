package com.jsp.base.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class NetUtil {

    public static final int NETTYPE_NO = 0;
    public static final int NETTYPE_WIFI = 1;
    public static final int NETTYPE_2G = 2;
    public static final int NETTYPE_3G = 3;
    public static final int NETTYPE_4G = 4;

    /**
     * 查看wifi是否打开，打开返回ture，关闭状态返回false
     *
     * @param inContext
     * @return
     */
    public static boolean isWiFiConnected(Context context) {

        WifiManager mWifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = mWifiManager.getConnectionInfo();
        int ipAddress = (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
        if (mWifiManager.isWifiEnabled() && ipAddress != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMobileConnected(Context context) {

        boolean isMobileConnected = false;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (networkInfo != null) {
            isMobileConnected = networkInfo.isConnected();
        }
        return isMobileConnected;
    }

    public static int getNetType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                return NETTYPE_WIFI;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);

                switch (telephonyManager.getNetworkType()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return NETTYPE_2G;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NETTYPE_4G;
                    default:
                        return NETTYPE_3G;
                }
            }
        } else {
            return NETTYPE_NO;
        }
    }

    public static String getNetTypeInString(Context context) {
        if(context==null){
            return "unkwon";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                return "WIFI";
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                String IMSI; // 返回唯一的用户ID;就是这张卡的编号神马的
                IMSI = telephonyManager.getSubscriberId();
                String ProvidersName = null;
                if (IMSI == null)
                    return "unkwon";
                // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。其中
                if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                    ProvidersName = "移动";
                } else if (IMSI.startsWith("46001")) {
                    ProvidersName = "联通";
                } else if (IMSI.startsWith("46003")) {
                    ProvidersName = "电信";
                }
                switch (telephonyManager.getNetworkType()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:// 移动联通2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_EDGE:// 移动联通2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_CDMA:// 电信2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_UMTS:// 移动联通3G
                        return ProvidersName+"3G";
                    case TelephonyManager.NETWORK_TYPE_HSDPA:// 移动联通3G
                        return ProvidersName+"3G";
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:// 电信3G
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:// 电信3G
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        return ProvidersName+"3G";
                    default:
                        return ProvidersName+"4G";
                }
            }
        } else {
            return "NO";
        }
    }

    public static String getNetTypeInEnString(Context context) {
        if(context==null){
            return "unkwon";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                return "WIFI";
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                String IMSI; // 返回唯一的用户ID;就是这张卡的编号神马的
                IMSI = telephonyManager.getSubscriberId();
                String ProvidersName = "";
                if (IMSI == null)
                    return "unkwon";
                // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。其中
                if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
                    ProvidersName = "ChinaMobile";//China Mobile
                } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
                    ProvidersName = "ChinaUnicom";//China Unicom
                } else if (IMSI.startsWith("46003") || IMSI.startsWith("46005") || IMSI.startsWith("46011")) {
                    ProvidersName = "ChinaTelecom";//China Telecom
                }
                switch (telephonyManager.getNetworkType()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:// 移动联通2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_EDGE:// 移动联通2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_CDMA:// 电信2G
                        return ProvidersName+"2G";
                    case TelephonyManager.NETWORK_TYPE_UMTS:// 移动联通3G
                        return ProvidersName+"3G";
                    case TelephonyManager.NETWORK_TYPE_HSDPA:// 移动联通3G
                        return ProvidersName+"3G";
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:// 电信3G
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:// 电信3G
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        return ProvidersName+"3G";
                    default:
                        return ProvidersName+"4G";
                }
            }
        } else {
            return "NO";
        }
    }

    /**
     * 返回用户手机运营商名称 * @param telephonyManager * @return
     */
    public String getProvidersName(Context context,TelephonyManager telephonyManager) {
        String ProvidersName = null;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI; // 返回唯一的用户ID;就是这张卡的编号神马的
        IMSI = telephonyManager.getSubscriberId();
        if (IMSI == null)
            return "unkwon";
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。其中
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "电信";
        }
        return ProvidersName;
    }
}
