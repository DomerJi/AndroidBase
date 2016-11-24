package com.jsp.base.util;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by admin on 2016/11/15.
 */
public class LogUtil {
    /**
     * 调试开关
     */
    private static boolean isDebug = true;
    /**
     * 默认TAG
     */
    private static String TAG = "【MyInfo】";

    public void setDebug(boolean isDebug){
        this.isDebug = isDebug;
    }

    public void setTAG(String tag){
        this.TAG = tag;
    }

    public static void e(String tag,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(msg))
                return;
            if(TextUtils.isEmpty(tag)){
                printf('e', TAG, msg);
            }else {
                printf('e', TAG + "==》[" + tag + "]", msg);
            }
        }
    }
    public static void e(String msg){
       e(null,msg);
    }


    public static void v(String tag,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(msg))
                return;
            if(TextUtils.isEmpty(tag)){
                printf('v', TAG, msg);
            }else {
                printf('v', TAG + "==》[" + tag + "]", msg);
            }
        }
    }

    public static void v(String msg){
        v(null, msg);
    }

    public static void d(String tag,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(msg))
                return;
            if(TextUtils.isEmpty(tag)){
                printf('d', TAG, msg);
            }else {
                printf('d', TAG + "==》[" + tag + "]", msg);
            }
        }
    }
    public static void d(String msg){
        d(null, msg);
    }

    public static void i(String tag,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(msg))
                return;
            if(TextUtils.isEmpty(tag)){
                printf('i', TAG, msg);
            }else {
                printf('i', TAG + "==》[" + tag + "]", msg);
            }
        }
    }
    public static void i(String msg){
        i(null, msg);
    }

    public static void w(String tag,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(msg))
                return;
            if(TextUtils.isEmpty(tag)){
                printf('w', TAG, msg);
            }else {
                printf('w',TAG + "==》[" + tag + "]",msg);
            }
        }
    }
    public static void w(String msg){
        w(null, msg);
    }

    /**
     * 打印所有信息
     * @param type
     * @param tag
     * @param msg
     */
    private static void printf(char type,String tag,String msg){
        if (msg.length() > 4000) {
//            Log.v(tag, "msg.length = " + msg.length());
            int chunkCount = msg.length() / 4000;
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= msg.length()) {
                    switch (type){
                        case 'w':
                            Log.w(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i));
                            break;
                        case 'v':
                            Log.v(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i));
                            break;
                        case 'd':
                            Log.d(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i));
                            break;
                        case 'i':
                            Log.i(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i));
                            break;
                        case 'e':
                            Log.e(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i));
                            break;
                    }
                } else {
                    switch (type){
                        case 'w':
                            Log.w(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i, max));
                            break;
                        case 'v':
                            Log.v(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i, max));
                            break;
                        case 'd':
                            Log.d(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i, max));
                            break;
                        case 'i':
                            Log.i(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i, max));
                            break;
                        case 'e':
                            Log.e(tag, i + "/" + chunkCount + ":" + msg.substring(4000 * i, max));
                            break;
                    }
                }
            }
        } else {
            switch (type){
                case 'w':
                    Log.w(tag, "1/1" + ":" + msg);
                    break;
                case 'v':
                    Log.v(tag,"1/1" + ":" + msg);
                    break;
                case 'd':
                    Log.d(tag,"1/1" + ":" + msg);
                    break;
                case 'i':
                    Log.i(tag,"1/1" + ":" + msg);
                    break;
                case 'e':
                    Log.e(tag,"1/1" + ":" + msg);
                    break;
            }
        }
    }

}
