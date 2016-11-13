package com.example.dell.fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.adapters.BackgroundWorker;
import com.example.dell.adapters.BillListAdapter;
import com.example.dell.adapters.CartListAdapter;
import com.example.dell.adapters.DatabaseHelper;
import com.example.dell.adapters.NavListAdapter;
import com.example.dell.models.BillItems;
import com.example.dell.models.CartItems;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 10/16/2016.
 */

public class MyCartFragment extends Fragment {

    List listCartItems;
    DatabaseHelper db;
    Button login;
    boolean statusOfCart;
    List listBillItems;
    Dialog registerdialog,myDialog,cinfodialog,billDialog;
    ListView billlistview;

    ListView cartlistview;
    static EditText address;
    Cursor res;
    String emailcol,passwordcol,type;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_mycart,container,false);
        this.getActivity().setTitle("SATVIK");
   //     Toast.makeText(getContext(),"In CART FRAGMENT",Toast.LENGTH_SHORT).show();
        // this.getActivity().setTitle("MYCART");

        cartlistview=(ListView)view.findViewById(R.id.listView1);

           db=new DatabaseHelper(this.getContext());
            db.onCreate();
            res=db.onView();

            int len=res.getCount();
    //    Toast.makeText(this.getContext(),"Working",Toast.LENGTH_SHORT).show();


       listCartItems = new ArrayList<CartItems>();
        listCartItems.add(new CartItems(9,"Item Name", "Quantity", "Price",""));
        int total=0;
        if(len==0)
        {
            Toast.makeText(this.getContext(),"Cart is Empty.",Toast.LENGTH_SHORT).show();
            statusOfCart=false;
        }
        else {
            while (res.moveToNext()) {
                int id=res.getInt(0);
                String itemname = res.getString(1).toString();  // 0 is id, 1 is name, 2 is qty, 3 price
                String itemqty = Integer.toString(res.getInt(2));
                String itemprice = Integer.toString(res.getInt(3)) ;
             //   Toast.makeText(this.getContext(),itemname,Toast.LENGTH_SHORT).show();
                listCartItems.add(new CartItems(id,itemname, itemqty, itemprice,""));
                total=res.getInt(2)*res.getInt(3)+total;
            }
        }
        if(listCartItems.size()!=1)
            listCartItems.add(new CartItems(9,"",Integer.toString(total),"Total:",""));

        res.close();
        db.close();

        CartListAdapter cartListAdapter = new CartListAdapter(getContext(), R.layout.cartlist_layout, listCartItems);
        cartlistview.setAdapter(cartListAdapter);

        return view;
    }

    public View getV() {
        return view;
    }

    public void setV(View view) {
        this.view = view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if((isVisibleToUser)&&(view!=null)){
         //  View view1=getLayoutInflater(null).inflate(R.layout.fragment_mycart,null,false);
                cartlistview=(ListView)view.findViewById(R.id.listView1);

            db=new DatabaseHelper(this.getContext());
            db.onCreate();
            res=db.onView();

            int len=res.getCount();
            //    Toast.makeText(this.getContext(),"Working",Toast.LENGTH_SHORT).show();

            int total=0;
            listCartItems = new ArrayList<CartItems>();
            listCartItems.add(new CartItems(9,"Item Name", "Quantity", "Price",""));

            if(len==0)
            {
                Toast.makeText(this.getContext(),"Cart is Empty.",Toast.LENGTH_SHORT).show();
                statusOfCart=false;
            }
            else {
                while (res.moveToNext()) {
                    int id=res.getInt(0);
                    String itemname = res.getString(1).toString();  // 0 is id, 1 is name, 2 is qty, 3 price
                    String itemqty = Integer.toString(res.getInt(2));
                    String itemprice = Integer.toString(res.getInt(3)) ;
               //     Toast.makeText(this.getContext(),itemname,Toast.LENGTH_SHORT).show();
                    listCartItems.add(new CartItems(id,itemname, itemqty, itemprice,""));
                    total=res.getInt(3)+total;

                }
            }
            if(listCartItems.size()!=1)
                listCartItems.add(new CartItems(9,"",Integer.toString(total),"Total:",""));
            res.close();
            db.close();


            CartListAdapter cartListAdapter = new CartListAdapter(getContext(), R.layout.cartlist_layout, listCartItems);
            cartlistview.setAdapter(cartListAdapter);

            FloatingActionButton floatingActionButton=(FloatingActionButton)view.findViewById(R.id.floatingbtn1);
            floatingActionButton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyProfile myProfile=new MyProfile();
                            if(myProfile.inSession==false){
                                myDialog = new Dialog(getContext());
                                myDialog.setContentView(R.layout.layout_logindialog);
                                DisplayMetrics displaymetrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                                int width = (int) ((int)displaymetrics.widthPixels * 0.9);
                                int height = (int) ((int)displaymetrics.heightPixels * 0.7);
                                myDialog.getWindow().setLayout(width,height);

                                myDialog.setTitle("Login");
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.setCanceledOnTouchOutside(true);
                                myDialog.show();
                                login=(Button)myDialog.findViewById(R.id.loginBtn);
                                login.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String res="";
                                                EditText emailaddr = (EditText) myDialog.findViewById(R.id.editText16);
                                                EditText password = (EditText) myDialog.findViewById(R.id.editText17);
                                                emailcol=emailaddr.getText().toString();
                                                passwordcol=password.getText().toString();
                                                type="login";
                                                BackgroundWorker backgroundWorker=new BackgroundWorker(getContext());
                                                // (Name,password,email,contact,address,type
                                                try {
                                                    if((emailaddr.getText().toString().equals(""))||(password.getText().toString().equals("")))
                                                    {
                                                        Toast.makeText(getContext(),"Please enter credentials",Toast.LENGTH_SHORT).show();
                                                    }
                                                    else
                                                    {
                                                        res =new String(backgroundWorker.execute(null, passwordcol, emailcol, null, null, type).get());
                                                    }
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();

                                                }
                                                if(res.equals("Login Successful.")){
                                                    android.support.v4.app.FragmentManager fragmentManager= getFragmentManager();
                                                    MyLoginProfile.inSession=true;
                                                    Toast.makeText(getContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                                                    MainActivity.setInSession(MyLoginProfile.inSession);

                                                    // Setting the name in Profile Box
                                                    MainActivity.name.setText(backgroundWorker.name);
                                                    MainActivity.emailaddress.setText(backgroundWorker.email);

                                                    // Insert the Info Tab
                                                    MainActivity.listFragments.set(0,new MyLoggedProfile());
                                                    NavListAdapter navListAdapter=new NavListAdapter(getContext(),R.layout.item_nav_list,MainActivity.listNavItems);
                                                    MainActivity.lvNav.setAdapter(navListAdapter);
                                                    //fragmentManager.beginTransaction().replace(R.id.main_content,new MyMenu()).commit();
                                                    myDialog.dismiss();
                                                       }
                                                else {
                                                    Toast.makeText(getContext(),"Login UnSuccessful",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                );

                                TextView registered = (TextView) myDialog.findViewById(R.id.textView);
                                registered.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                myDialog.dismiss();
                                                registerdialog = new Dialog(getContext());
                                                registerdialog.setContentView(R.layout.layout_registerdialog);

                                                DisplayMetrics displaymetrics = new DisplayMetrics();
                                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                                                int width = (int) ((int)displaymetrics.widthPixels * 0.9);
                                                int height = (int) ((int)displaymetrics.heightPixels * 0.9);
                                                registerdialog.getWindow().setLayout(width,height);

                                                registerdialog.setTitle("Login");
                                                registerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                registerdialog.setCancelable(true);
                                                registerdialog.setCanceledOnTouchOutside(true);

                                                Button register = (Button) registerdialog.findViewById(R.id.button);

                                                registerdialog.show();
                                                register.setOnClickListener(
                                                        new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                MyLoginProfile myloginprofile=new MyLoginProfile();
                                                                myloginprofile.onRegister(v);
                                                            }
                                                        }
                                                );
                                            }
                                        }
                                );

                                // Toast.makeText(getContext(), "Login Page", Toast.LENGTH_SHORT).show();
                                FloatingActionButton floatingActionButton=(FloatingActionButton)getV().findViewById(R.id.floatingbtn1);
                                floatingActionButton.setBackgroundColor(Color.BLUE);

                            }
                            else
                            {
                                cinfodialog = new Dialog(getContext());
                                cinfodialog.setContentView(R.layout.layout_contactinfo);
                                DisplayMetrics displaymetrics = new DisplayMetrics();
                                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                                int width = (int) ((int)displaymetrics.widthPixels * 0.9);
                                int height = (int) ((int)displaymetrics.heightPixels * 0.7);
                                cinfodialog.getWindow().setLayout(width,height);

                                cinfodialog.setTitle("Contact Information");
                                cinfodialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                                cinfodialog.setCanceledOnTouchOutside(true);
                                cinfodialog.show();
                                login=(Button)cinfodialog.findViewById(R.id.button8);
                                login.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                address=(EditText)cinfodialog.findViewById(R.id.editText18);
                                                EditText contact=(EditText)cinfodialog.findViewById(R.id.editText20);
                                                EditText email=(EditText)cinfodialog.findViewById(R.id.editText21);

                                                if((address.getText().toString().isEmpty())||(contact.getText().toString().isEmpty())||(email.getText().toString().isEmpty()))
                                                {
                                                    Toast.makeText(getContext(), "Please fill all details.", Toast.LENGTH_SHORT).show();
                                                }
                                                else
                                                {

                                                    // Call Final Dialog Box with Shipping and Bill Details.
                                              //      Toast.makeText(getContext(), "Go to Final Bill and Address Confirmation Page.", Toast.LENGTH_SHORT).show();

                                                    billlistview=(ListView)view.findViewById(R.id.listView1);

                                                    db=new DatabaseHelper(getContext());
                                                    db.onCreate();
                                                    res=db.onView();

                                                    int len=res.getCount();

                                                    listBillItems = new ArrayList<BillItems>();
                                                    listBillItems.add(new BillItems(9,"Item Name", "Quantity", "Price"));
                                                    int total=0;
                                                    if(len==0)
                                                    {
                                                        Toast.makeText(getContext(),"Cart is Empty.",Toast.LENGTH_SHORT).show();
                                                        statusOfCart=false;
                                                    }
                                                    else {
                                                        while (res.moveToNext()) {
                                                            int id=res.getInt(0);
                                                            String itemname = res.getString(1).toString();  // 0 is id, 1 is name, 2 is qty, 3 price
                                                            String itemqty = Integer.toString(res.getInt(2));
                                                            String itemprice = Integer.toString(res.getInt(3)) ;
                                                        //    Toast.makeText(getContext(),itemname,Toast.LENGTH_SHORT).show();
                                                            listBillItems.add(new BillItems(id,itemname, itemqty, itemprice));
                                                            total=res.getInt(2)*res.getInt(3)+total;
                                                        }
                                                    }
                                                    if(listBillItems.size()!=1)
                                                        listBillItems.add(new BillItems(9,"",Integer.toString(total),"Total:"));

                                                    res.close();
                                                    db.close();

                                                    BillListAdapter billListAdapter = new BillListAdapter(getContext(), R.layout.billlist_layout, listBillItems);
                                                    billlistview.setAdapter(billListAdapter);


                                                    billDialog = new Dialog(getContext());
                                                    billDialog.setContentView(R.layout.fragment_mybill);
                                                    DisplayMetrics displaymetrics = new DisplayMetrics();
                                                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
                                                    int width = (int) ((int)displaymetrics.widthPixels * 0.95);
                                                    int height = (int) ((int)displaymetrics.heightPixels * 0.95);
                                                    billDialog.getWindow().setLayout(width,height);

                                                    billDialog.setTitle("ORDER SUMMARY");


                                                    TextView address1=(TextView) billDialog.findViewById(R.id.textView15);
                                                    address1.setText(address.getText());

                                                    billDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                                                    billDialog.setCanceledOnTouchOutside(true);
                                                    billDialog.show();

                                                }
                                            }
                                        }
                                );
                            }

                        }
                    }
            );

        }
        }
}
