package com.example.dell.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.dell.fragments.MyMenu;
import com.example.dell.fragments.MyProfile;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.Inflater;

import static android.content.Context.MODE_PRIVATE;
import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Dell on 10/20/2016.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String>  {
    Context context;
    AlertDialog alertDialog;

    Fragment fm;
    public static String result="",line="",name="",contact="",email="",pass="",address="",registerResult="",type="";
    public static String Rname="",Remailid="",Rcontact="",Radd="",Rpassw="";
    public BackgroundWorker(Context context) {
        this.context = context;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        BackgroundWorker.address = address;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        BackgroundWorker.email = email;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BackgroundWorker.name = name;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        BackgroundWorker.pass = pass;
    }

    public static String getContact() {
        return contact;
    }

    public static void setContact(String contact) {
        BackgroundWorker.contact = contact;
    }

    @Override
    protected String doInBackground(String... params) {
        type=params[5];
        String login_url="http://premierleagueinferno.com/foodlogin.php";
        if(type.equals("login")){
            try {
                MyProfile.inSession=true;
                String emailid=params[2];
                String passw=params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(emailid,"UTF-8")+"&"+
                        URLEncoder.encode("passw","UTF-8")+"="+URLEncoder.encode(passw,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
                result="";
                int count=1;
                while((line=bufferedReader.readLine())!=null){
                    switch(count){
                        case 1:{
                            result=line;
                            break;
                        }
                        case 2:{
                            email=line;
                            break;}
                        case 3:{
                            name=line;
                            break;}
                        case 4:{
                            contact=line;
                            break;}
                        case 5:{
                            address=line;
                            break;}
                        case 6:{
                            pass=line;
                            break;}
                        default:break;
                    }
                    count++;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if((type.equals("register"))||(type.equals("MyCustomerInfo"))){
            try {
                Rname=params[0];
                Rpassw=params[1];
                Remailid=params[2];
                Rcontact=params[3];
                Radd=params[4];
                URL url = new URL("http://premierleagueinferno.com/foodregister.php");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(Rname,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(Rpassw,"UTF-8")+"&"+
                        URLEncoder.encode("emailid","UTF-8")+"="+URLEncoder.encode(Remailid,"UTF-8")+"&"+
                        URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(Rcontact,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                registerResult="";
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
                while((line=bufferedReader.readLine())!=null){
                registerResult=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return registerResult;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    @Override
    protected void onPreExecute() {
       if(BackgroundWorker.type.equals("register")) {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Registration Status");
        }
        }

    @Override
    protected void onPostExecute(String result) {
            if (result.equals("Login Successful.")) {
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Login Status");
                alertDialog.setMessage(result);
                alertDialog.show();
                MainActivity.name.setText(name);
                MainActivity.emailaddress.setText(email);
            }
        else if (result.equals("Registration Successful")){
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Registration Status");
                alertDialog.setMessage(registerResult);
                alertDialog.show();
            }
        else{
                alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Status");
                alertDialog.setMessage(result);
                alertDialog.show();
            }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
