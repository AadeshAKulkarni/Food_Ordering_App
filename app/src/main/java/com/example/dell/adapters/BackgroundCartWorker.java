package com.example.dell.adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.fragments.MyCartFragment;
import com.example.dell.models.CartItems;
import com.example.dell.slidingmenu_tabhostviewpager.CartDialog;
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
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Dell on 10/20/2016.
 */

public class BackgroundCartWorker extends AsyncTask<String,Void,String>  {
    Context context;
    AlertDialog alertDialog;
    DatabaseHelper databaseHelper;
    List listCartItems;
    boolean statusOfCart;
    long success;
    MyCartFragment mcf;
    View rootView;
    Fragment fm;
    ListView cartlistview;
    @Override
    protected String doInBackground(String... params) {
        mcf=new MyCartFragment();

//        View v=mcf.getV();

        if(params[0].equals("insert")) {
            databaseHelper=new DatabaseHelper(context);
            databaseHelper.onCreate();
            success = databaseHelper.onInsert(params[1], params[2], params[3]);



            //
        }
        return "";

    }
    public BackgroundCartWorker(Context context,View rootView)
    {
        this.context = context;
        this.rootView=rootView;
    }

    @Override
    protected void onPreExecute() {
        }

    @Override
    protected void onPostExecute(String result) {
     /*   MyCartFragment mcf=new MyCartFragment();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        View v= inflater.inflate(R.layout.fragment_mycart,null,false);
//        Toast.makeText(this.context, "Row id affected : " + success, Toast.LENGTH_SHORT).show();
        cartlistview=(ListView)v.findViewById(R.id.listView1);

        Cursor res=databaseHelper.onView();
        int len=res.getCount();

        listCartItems = new ArrayList<CartItems>();
        listCartItems.add(new CartItems(9,"Item Name", "Quantity", "Price","Delete"));

        if(len==0)
        {
            //Toast.makeText(this.getContext(),"Cart is Empty.",Toast.LENGTH_SHORT).show();
            statusOfCart=false;
        }
        else {
            while (res.moveToNext()) {
                int id=res.getInt(0);
                String itemname = res.getString(1).toString();  // 0 is id, 1 is name, 2 is qty, 3 price
                String itemqty = Integer.toString(res.getInt(2));
                String itemprice = Integer.toString(res.getInt(3)) ;
                //Toast.makeText(context,itemname,Toast.LENGTH_SHORT).show();
                listCartItems.add(new CartItems(id,itemname, itemqty, itemprice,"X"));
            }
        }
        CartListAdapter cartListAdapter = new CartListAdapter(mcf.getContext(),R.layout.cartlist_layout, listCartItems);
        cartlistview.setAdapter(cartListAdapter);*/

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
