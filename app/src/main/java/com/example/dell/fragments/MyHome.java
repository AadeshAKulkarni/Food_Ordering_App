package com.example.dell.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.dell.adapters.MyFragmentPagerAdapter;
import com.example.dell.slidingmenu_tabhostviewpager.MainActivity;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class MyHome extends Fragment {
    Button menuBtn;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        this.getActivity().setTitle("SATVIK");


       /* menuBtn=(Button)view.findViewById(R.id.button);
        menuBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_content, (Fragment) listFragments.get(0)).commit();
                        setTitle(listNavItems.get(0).getTitle());
                        lvNav.setItemChecked(0,true);
                        drawerLayout.closeDrawer(drawerPane);

                        Fragment mymenu = new MyMenu();
                        // Insert the fragment by replacing any existing fragment
                        android.support.v4.app.FragmentManager fragmentManager= getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_content, mymenu).commit();

                    }
                }
        ); */
        return view;
    }

/*
    private void initTabHost(View v) {
        tabHost=(TabHost)v.findViewById(android.R.id.tabhost);
        tabHost.setup();

        String tabNames[]={"HOME","MENU","CART","LOCATIONS"};
        int tab[]={R.drawable.menu,R.drawable.menu,R.drawable.menu,R.drawable.menu};
        for(int i=0;i<tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec=tabHost.newTabSpec(tabNames[i]);
            //  tabSpec.setIndicator("",getResources().getDrawable(tab[i]));
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new MainActivity.fakeContent(getContext()));
            tabHost.addTab(tabSpec);
        }
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
        }
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem=tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);

        HorizontalScrollView hScrollView=(HorizontalScrollView)view.findViewById(R.id.h_scroll_view);
        View tabView=tabHost.getCurrentTabView();
        int scrollpos=tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollpos,0);



        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#125678")); // unselected
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#ffffff"));
        }

        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#125689")); // selected
        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#efefef"));


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int selectedItem) {
        tabHost.setCurrentTab(selectedItem);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class fakeContent implements TabHost.TabContentFactory{
        Context context;
        public fakeContent(Context context){
            this.context=context;
        }
        @Override
        public View createTabContent(String tag) {
            View fakeView=new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumWidth(0);

            return fakeView;
        }
    }
    private void initViewPager(View v) {
        viewPager=(ViewPager)v.findViewById(R.id.view_pager);

        List<Fragment> listFragments=new ArrayList<Fragment>();
        listFragments.add(new MyHome());
        listFragments.add(new MyMenu());
        listFragments.add(new MyCart());
        listFragments.add(new MyProfile());


        MyFragmentPagerAdapter myFragmentPagerAdapter=new MyFragmentPagerAdapter(getFragmentManager(),listFragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);

    }
*/
}
