package com.example.dell.fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

public class MyBillFragment extends Fragment {

    View view;
    TextView address;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_mybill,container,false);
        //this.getActivity().setTitle("ORDER SUMMARY");
        address=(TextView) view.findViewById(R.id.textView15);
        address.setText(MyCartFragment.address.getText());
        return view;
    }
}
