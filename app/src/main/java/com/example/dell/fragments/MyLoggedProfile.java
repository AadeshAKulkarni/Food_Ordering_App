package com.example.dell.fragments;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.dell.adapters.BackgroundWorker;
import com.example.dell.adapters.MyExpandableListProfileAdapter;
import com.example.dell.adapters.NavListAdapter;
import com.example.dell.models.ProfileItems;
import com.example.dell.slidingmenu_tabhostviewpager.CartDialog;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dell on 10/16/2016.
 */

public class MyLoggedProfile extends DialogFragment {
    EditText name,email,pass,contact,address;
    public static String sname,semail,spass,scontact,saddress;
    Button logout;
    List content;
    public static boolean inSession=false;
    static Dialog profileDialog;
    ExpandableListView expandableListView1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.layout_profileinfo,container,false);
    //    Toast.makeText(getContext()," LoggedProfile is working",Toast.LENGTH_SHORT).show();
        profileDialog = new Dialog(this.getContext());
        profileDialog.setContentView(R.layout.layout_profileinfo);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = (int) ((int)displaymetrics.widthPixels * 0.9);
        int height = (int) ((int)displaymetrics.heightPixels * 0.9);
        profileDialog.getWindow().setLayout(width,height);
        profileDialog.setTitle("SATVIK");
        profileDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        profileDialog.setCanceledOnTouchOutside(true);

        expandableListView1=(ExpandableListView) profileDialog.findViewById(R.id.profileinfo);
        content=new ArrayList<String>();

        List<String> Headings=new ArrayList<String>();
        Headings.add("Account Information");
        Headings.add("Contact Information");

        List<String> Account_caption=new ArrayList<String>();
        Account_caption.add("Name:");
        Account_caption.add("Email:");
        Account_caption.add("Password:");

        List<String> Account_output=new ArrayList<String>();
        final String MY_PREFS_NAME = "MyPrefsFile";
        MyLoginProfile myLoginProfile=new MyLoginProfile();
        SharedPreferences editor =view.getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name1 = editor.getString(getString(R.string.name),"");
        String email1 = editor.getString(getString(R.string.email),"");
        String password1 = editor.getString(getString(R.string.password),"");

        String contact1 = editor.getString(getString(R.string.contact),"");
        String address1 = editor.getString(getString(R.string.address),"");

        if (name1 != null) {
            name1 = editor.getString("name", "No name defined");//"No name defined" is the default value.
        }
        Account_output.add(BackgroundWorker.getName());
        Account_output.add(BackgroundWorker.getEmail());
        Account_output.add(BackgroundWorker.getPass());

        List<String> contact_caption=new ArrayList <String>();
        contact_caption.add("Email:");
        contact_caption.add("Phone:");
        contact_caption.add("Address:");


        List<String> contact_output=new ArrayList <String>();
        contact_output.add(BackgroundWorker.getEmail());
        contact_output.add(BackgroundWorker.getContact());
        contact_output.add(BackgroundWorker.getAddress());

        HashMap<String, ProfileItems> ChildList=new HashMap<String, ProfileItems>();
        ChildList.put(Headings.get(0),new ProfileItems(Account_caption,Account_output));
        ChildList.put(Headings.get(1),new ProfileItems(contact_caption,contact_output));
        final MyExpandableListProfileAdapter myExpandableListProfileAdapter=new MyExpandableListProfileAdapter(this.getContext(),Headings,ChildList);
        expandableListView1.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        return true;
                    }
                }
        );
        expandableListView1.setAdapter(myExpandableListProfileAdapter);

        /*
        name=(EditText)view.findViewById(R.id.editText7);
        pass=(EditText)view.findViewById(R.id.editText8);
        email=(EditText)view.findViewById(R.id.editText9);
        contact=(EditText)view.findViewById(R.id.editText10);
        address=(EditText)view.findViewById(R.id.editText11);
        BackgroundWorker backgroundWorker=new BackgroundWorker(this.getContext());
        name.setText(backgroundWorker.name);
        pass.setText(backgroundWorker.pass);
        email.setText(backgroundWorker.email);
        contact.setText(backgroundWorker.contact);
        address.setText(backgroundWorker.address);
        sname=name.getText().toString();
        spass=pass.getText().toString();
        semail=email.getText().toString();
        scontact=contact.getText().toString();
        saddress=address.getText().toString();*/

        logout=(Button)profileDialog.findViewById(R.id.button5);
        logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLogout();
                        profileDialog.dismiss();
                        Toast.makeText(getContext(),"Logout Successful.",Toast.LENGTH_SHORT).show();

                    }
                }
        );
        profileDialog.show();
        return view;
    }
    public void onLogout(){
        MainActivity.listFragments.set(0,new MyLoginProfile());
        NavListAdapter navListAdapter=new NavListAdapter(getContext(),R.layout.item_nav_list,MainActivity.listNavItems);
        MainActivity.lvNav.setAdapter(navListAdapter);
        inSession=false;
        BackgroundWorker.result=new String("");
        MainActivity.name.setText("Log in.");
        MainActivity.emailaddress.setText("");
        MyProfile.inSession=false;
    }
}
