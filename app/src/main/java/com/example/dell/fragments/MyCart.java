package com.example.dell.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dell.adapters.CartListAdapter;
import com.example.dell.adapters.DatabaseHelper;
import com.example.dell.adapters.NavListAdapter;
import com.example.dell.models.CartItems;
import com.example.dell.models.NavItem;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.ArrayList;
import java.util.List;


public class MyCart extends Fragment {
    View mView;
    ListView cartlistview;
    DatabaseHelper db;
    boolean statusOfCart=true;
    public static List listCartItems;
    RelativeLayout rl;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart,container,false);
        rl = (RelativeLayout)inflater.inflate(R.layout.fragment_cart, container, false);
        return rl;
    }

    public View getView() {
        return mView;
    }

}
