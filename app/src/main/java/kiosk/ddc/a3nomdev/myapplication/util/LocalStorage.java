package kiosk.ddc.a3nomdev.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 3nomdev on 11/25/17.
 */

public class LocalStorage {

    public static String getUser(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return prefs.getString("User", "user");
    }
    public static String getPass(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return prefs.getString("Pass", "pass");
    }
    public static void setLogin(Context c,String user,String pass)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("User", user);
        editor.putString("Pass", pass);
        editor.commit();
    }

    public static String getServer(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return prefs.getString("Server", "http://ddc2.3nom.com/api/Guest/");
    }
    public static void setSettings(Context c,String server)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Server", server);
        editor.commit();
    }

}
