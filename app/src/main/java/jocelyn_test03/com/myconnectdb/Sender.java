package jocelyn_test03.com.myconnectdb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by Jocelyn on 23/9/2016.
 */

public class Sender extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    EditText edtFname, edtLname, edtName, edtPass, edtEmail;
    String fName,lName, uName, pass, email;

    ProgressDialog pd;

    public Sender(Context c, String urlAddress,EditText...editTexts) {
        this.c = c;
        this.urlAddress = urlAddress;

        this.edtFname = editTexts[0];
        this.edtLname = editTexts[1];
        this.edtName = editTexts[2];
        this.edtPass = editTexts[3];
        this.edtEmail = editTexts[4];

        fName = edtName.getText().toString();
        lName = edtLname.getText().toString();
        uName = edtName.getText().toString();
        pass = edtPass.getText().toString();
        email = edtEmail.getText().toString();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Send");
        pd.setMessage("Sending.....Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.send();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();
        if(s != null){
            //SUCCESSFULL
            Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
            //设置空输入格 CLEAR EDITORS
            edtFname.setText("");
            edtLname.setText("");
            edtName.setText("");
            edtPass.setText("");
            edtEmail.setText("");

        }else {
            //NO SUCCESS
            Toast.makeText(c,"Sign up Unsuccessfull",Toast.LENGTH_SHORT).show();
        }

    }

    public String send(){
        //CONNECT
        HttpURLConnection con = Connecter.connect(urlAddress);
        if(con == null){
            return null;
        }

        try{
            OutputStream os = con.getOutputStream();
            //WRITE OUT TO SERVER
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bfw.write(new DataPackager(fName,lName, uName, pass, email).packData());

            //RELEASE RESOURCE
            bfw.flush();
            bfw.close();
            os.close();

            // HAS IT BEEN SUCCESSFULL
            int responseCode = con.getResponseCode();
            if(responseCode == con.HTTP_OK){
                //GET EXACT RESPONSE
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer response = new StringBuffer();
                String line = null;
                while((line = br.readLine()) !=null){
                    response.append(line);
                }
                //RELEASE RES
                br.close();

                return response.toString();

            }else{
               // Toast.makeText(c,"Sign up Unsuccessfull",Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
}
