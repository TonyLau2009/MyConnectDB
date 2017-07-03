package jocelyn_test03.com.myconnectdb;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;
import java.io.*;

/**
 * Created by Jocelyn on 23/9/2016.
 */

public class DataPackager{
    private String fName,lName,uName,pass,email;
    //this is a constructor
    public DataPackager(String fName,String lName,String uName,String pass,String email){
        this.fName = fName;
        this.lName = lName;
        this.uName = uName;
        this.pass = pass;
        this.email = email;
    }
    //packet DATA to be send

    public String packData(){
        JSONObject jo = new JSONObject();
        StringBuffer sb = new StringBuffer();

        try{
            jo.put("fName",fName);
            jo.put("lName",lName);
            jo.put("uName",uName);
            jo.put("pass",pass);
            jo.put("email",email);

            Iterator it = jo.keys();
            boolean firstValue = true;
            do{
                String key = it.next().toString();
                String value  = jo.get(key).toString();
                if(firstValue){
                    firstValue = false;
                }else{
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value,"UTF-8"));
            }while(it.hasNext());

            return sb.toString();

        }catch(JSONException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
