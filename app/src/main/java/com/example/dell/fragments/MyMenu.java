package com.example.dell.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.dell.adapters.MyExpandableListAdapter;
import com.example.dell.models.MenuItems;
import com.example.dell.slidingmenu_tabhostviewpager.CartDialog;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.TextView;

/**
 * Created by Dell on 10/16/2016.
 */

public class MyMenu extends Fragment {
    ExpandableListView expandableListView;
    Button carty;
    TextView item;
    List<MenuItems> content;
    public static String item1,childid;
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container,false);

        this.getActivity().setTitle("SATVIK");

        expandableListView=(ExpandableListView) view.findViewById(R.id.exp_listview);

      //  content=new ArrayList<MenuItems>();

        List<String> Headings=new ArrayList<String>();

        List<String> L1=new ArrayList<String>();
        List<String> L2=new ArrayList <String>();
        List<String> L3=new ArrayList<String>();
        List<String> L4=new ArrayList<String>();
        List<String> L5=new ArrayList<String>();
        List<String> L6=new ArrayList<String>();

        List<String> L1price=new ArrayList<String>();
        List<String> L2price=new ArrayList <String>();
        List<String> L3price=new ArrayList<String>();
        List<String> L4price=new ArrayList<String>();
        List<String> L5price=new ArrayList<String>();
        List<String> L6price=new ArrayList<String>();

        HashMap<String, MenuItems> ChildList=new HashMap<String, MenuItems>();

        String heading_items[]=getResources().getStringArray(R.array.menu_title);

        String l1[]=getResources().getStringArray(R.array.snacks);
        String l2[]=getResources().getStringArray(R.array.sabzi);
        String l3[]=getResources().getStringArray(R.array.rice);
        String l4[]=getResources().getStringArray(R.array.dal);
        String l5[]=getResources().getStringArray(R.array.extras);
        String l6[]=getResources().getStringArray(R.array.drinks);

        String l1_price[]=getResources().getStringArray(R.array.snacksprice);
        String l2_price[]=getResources().getStringArray(R.array.sabziprice);
        String l3_price[]=getResources().getStringArray(R.array.riceprice);
        String l4_price[]=getResources().getStringArray(R.array.dalprice);
        String l5_price[]=getResources().getStringArray(R.array.extrasprice);
        String l6_price[]=getResources().getStringArray(R.array.drinksprice);


        for(String title:heading_items){
            Headings.add(title);

        }

        for(String title:l1){
            L1.add(title);
        }

        for(String title:l2){
            L2.add(title);
        }

        for(String title:l3){
            L3.add(title);
        }

        for(String title:l4){
            L4.add(title);
        }

        for(String title:l5){
            L5.add(title);
        }

        for(String title:l6){
            L6.add(title);
        }

        for(String title:l1_price){
            L1price.add(title);
        }

        for(String title:l2_price){
            L2price.add(title);
        }

        for(String title:l3_price){
            L3price.add(title);
        }

        for(String title:l4_price){
            L4price.add(title);
        }

        for(String title:l5_price){
            L5price.add(title);
        }

        for(String title:l6_price){
            L6price.add(title);
        }


        ChildList.put(Headings.get(0),new MenuItems(L1,L1price));
        ChildList.put(Headings.get(1),new MenuItems(L2,L2price));
        ChildList.put(Headings.get(2),new MenuItems(L3,L3price));
        ChildList.put(Headings.get(3),new MenuItems(L4,L4price));
        ChildList.put(Headings.get(4),new MenuItems(L5,L5price));
        ChildList.put(Headings.get(5),new MenuItems(L6,L6price));

        final MyExpandableListAdapter myExpandableListAdapter=new MyExpandableListAdapter(this.getContext(),Headings,ChildList);


        expandableListView.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        item1=myExpandableListAdapter.getChild(groupPosition,childPosition).toString();
                        childid=Integer.toString(childPosition);
                        CartDialog cd=new CartDialog();
                     //   String itemName=(String)expandableListView.getSelectedItem().toString();
                        cd.show(getFragmentManager(),"MyDialog");
                        return true;
                    }
                }
        );
        expandableListView.setAdapter(myExpandableListAdapter);
        carty=(Button)view.findViewById(R.id.cart_btn);
        carty.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBtnClick();
                    }
                }
        );
        return view;
    }
    public void onBtnClick(){
                android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_content,new MyCartFragment()).commit();

    }

}
