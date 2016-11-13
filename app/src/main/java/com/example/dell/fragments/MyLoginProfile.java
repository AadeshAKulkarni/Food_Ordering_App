package com.example.dell.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.adapters.BackgroundWorker;
import com.example.dell.adapters.MyExpandableListAdapter;
import com.example.dell.adapters.NavListAdapter;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

public class MyLoginProfile extends DialogFragment {
    EditText email,password;
    Button login,register;
    EditText e12,e13,e14,e15;
    public String emailcol,passwordcol,type,nameR,passwordR,contactR,emailR;
    String res="";
    static Dialog myDialog,registerdialog;
    public static boolean inSession=false;

    Context ctx;

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_logindialog,container,false);
        setCtx(view.getContext());
        MainActivity.setInSession(inSession);
        //callLoginDialog(view);

        // Login Dialog Code Starts here

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
                            inSession=true;
                            Toast.makeText(getContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                             MainActivity.setInSession(inSession);

                            // Setting the name in Profile Box
                            MainActivity.name.setText(backgroundWorker.name);
                            MainActivity.emailaddress.setText(backgroundWorker.email);

                     /*       final String MY_PREFS_NAME = "MyPrefsFile";
                            SharedPreferences.Editor editor =getCtx().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString(getString(R.string.name),backgroundWorker.name);
                            editor.putString(getString(R.string.contact),backgroundWorker.contact);
                            editor.putString(getString(R.string.email),backgroundWorker.email);
                            editor.putString(getString(R.string.address),backgroundWorker.address);
                            editor.putString(getString(R.string.password),backgroundWorker.pass);
                            editor.commit();
*/

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
                                        onRegister(v);

                                    }
                                }
                        );
                    }
                }
        );
        // Login Dialog Code ends here
        return view;
    }
    private double getScreenHeight(FragmentActivity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public void onRegister(View v){
        EditText name = (EditText) registerdialog.findViewById(R.id.editText19);
        EditText emailaddr = (EditText) registerdialog.findViewById(R.id.editText16);
        TextInputLayout password1 = (TextInputLayout) registerdialog.findViewById(R.id.pass1);
        TextInputLayout password2 = (TextInputLayout) registerdialog.findViewById(R.id.pass2);
        if(password1.getEditText().getText().toString().equals(password2.getEditText().getText().toString())) {
            String type = "register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
            nameR = name.getText().toString();
            passwordR = password1.getEditText().getText().toString();
            emailR = emailaddr.getText().toString();
            contactR = "97572470";
            backgroundWorker.execute(nameR, passwordR, emailR, contactR, "", type);
            // Insert the Info Tab
            MainActivity.listFragments.set(0,new MyLoginProfile());
            NavListAdapter navListAdapter=new NavListAdapter(getContext(),R.layout.item_nav_list,MainActivity.listNavItems);
            MainActivity.lvNav.setAdapter(navListAdapter);

            registerdialog.dismiss();
        }
        else
        {
            password2.setError("Passwords dont match.");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

            if(MainActivity.isInSession()) {
                savedInstanceState.putBoolean("loggedin", true);
                savedInstanceState.putString("email", emailcol);
                savedInstanceState.putString("password", passwordcol);
                savedInstanceState.putString("type", "login");
            } else {
                savedInstanceState.putBoolean("loggedin", false);
            }
        savedInstanceState.putBoolean("isinsession",MainActivity.isInSession());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            boolean isinsession=savedInstanceState.getBoolean("isinsession");
            boolean inSession = savedInstanceState.getBoolean("loggedin");
            if ((MainActivity.isInSession())||(isinsession)) {
                String email = savedInstanceState.getString("email");
                String pass = savedInstanceState.getString("password");
                String type = "login";
                BackgroundWorker bw = new BackgroundWorker(this.getContext());
                //(Name,password,email,contact,address,type
                bw.execute(null, pass, email, null, null, type);
            }
        }
        super.onViewStateRestored(savedInstanceState);
    }

}
