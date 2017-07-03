package jocelyn_test03.com.myconnectdb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jocelyn on 23/9/2016.
 */

public class Connecter {

    public static HttpURLConnection connect(String urlAddress){
        HttpURLConnection con = null;
        try {
            URL url = new URL(urlAddress);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);
            con.setDoOutput(true);

            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
