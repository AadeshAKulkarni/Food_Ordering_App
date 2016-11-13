package com.example.dell.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.adapters.BackgroundWorker;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by Dell on 10/16/2016.
 */

public class MyLoginDialog extends Fragment {
    EditText email,password;
    Button login,register;
    EditText e12,e13,e14,e15;
    public String emailcol,passwordcol,type,nameR,passwordR,contactR,emailR;
    String res;
    public static boolean inSession=false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loginprofile,container,false);
        MainActivity.setInSession(inSession);
        this.getActivity().setTitle("SATVIK");
        // Step 2: initiating elements
        email=(EditText)view.findViewById(R.id.editText4);
        password=(EditText)view.findViewById(R.id.editText2);
        login=(Button)view.findViewById(R.id.button2);
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emailcol=email.getText().toString();
                        passwordcol=password.getText().toString();
                        type="login";
                        BackgroundWorker backgroundWorker=new BackgroundWorker(getContext());
                        // (Name,password,email,contact,address,type
                        try {
                            res=backgroundWorker.execute(null,passwordcol,emailcol,null,null,type).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();

                        }
                        if(res.equals("Login Successful.")){
                            android.support.v4.app.FragmentManager fragmentManager= getFragmentManager();
                            inSession=true;
                            MainActivity.setInSession(inSession);
                            // Insert the fragment by replacing any existing fragment
                            MainActivity.listFragments.set(2,new MyLoggedProfile());
                           // NavListAdapter navListAdapter=new NavListAdapter(getContext(),R.layout.item_nav_list,MainActivity.listNavItems);
                            //MainActivity.lvNav.setAdapter(navListAdapter);
                            fragmentManager.beginTransaction().replace(R.id.main_content,new MyMenu()).commit();
                        }
                    }
                }
        );
        register=(Button)view.findViewById(R.id.button3);
        final View v1=view;
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister(v1);
            }
        };
        register.setOnClickListener(onClickListener);
        return view;
    }

    public void onRegister(View v){
        e12=(EditText)v.findViewById(R.id.editText12);
        e13=(EditText)v.findViewById(R.id.editText13);
        e14=(EditText)v.findViewById(R.id.editText14);
        e15=(EditText)v.findViewById(R.id.editText15);
        String type="register";
        BackgroundWorker backgroundWorker=new BackgroundWorker(getContext());
        nameR=e12.getText().toString();
        passwordR=e13.getText().toString();
        emailR=e14.getText().toString();
        contactR=e15.getText().toString();
        backgroundWorker.execute(nameR,passwordR,emailR,contactR,"",type);
        android.support.v4.app.FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, new MyLoginDialog()).commit();
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
