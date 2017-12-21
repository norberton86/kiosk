package kiosk.ddc.a3nomdev.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/**
 * Created by 3nomdev on 11/25/17.
 */

public class LocalStorage {

    public static String getUser(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return prefs.getString("User", "");
    }
    public static String getPass(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return prefs.getString("Pass", "");
    }

    public static void setLogin(Context c,String user,String pass)
    {
        SharedPreferences prefs = c.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("User", user);
        editor.putString("Pass", pass);
        editor.commit();
    }
    //----------------------------------------------------------------------------------------------
    public static String getServer(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return prefs.getString("Server", "http://ddc2.3nom.com/api/Guest/");
    }

    public static String getID(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return prefs.getString("uuid", UUID.randomUUID().toString());
    }

    public static String getDescription(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return prefs.getString("description", "");
    }


    public static void setSettings(Context c,String server,String uuid,String description)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Server", server);
        editor.putString("uuid", uuid);
        editor.putString("description", description);
        editor.commit();

        setConfigured(c);
    }
    //----------------------------------------------------------------------------------------------

    public static Boolean isConfigured(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        return prefs.getBoolean("configured",false);
    }
    public static void setConfigured(Context c)
    {
        SharedPreferences prefs = c.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("configured", true);
        editor.commit();
    }
}
