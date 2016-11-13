package com.example.dell.slidingmenu_tabhostviewpager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.adapters.BackgroundWorker;
import com.example.dell.adapters.DatabaseHelper;
import com.example.dell.adapters.MyFragmentPagerAdapter;
import com.example.dell.adapters.NavListAdapter;
import com.example.dell.fragments.MyAbout;
import com.example.dell.fragments.MyCart;
import com.example.dell.fragments.MyCartFragment;
import com.example.dell.fragments.MyHome;
import com.example.dell.fragments.MyLoggedProfile;
import com.example.dell.fragments.MyLoginProfile;
import com.example.dell.fragments.MyMenu;
import com.example.dell.fragments.MyPastOrders;
import com.example.dell.fragments.MyProfile;
import com.example.dell.models.NavItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,TabHost.OnTabChangeListener  {
    DrawerLayout drawerLayout;
    private Dialog myDialog;
    public static RelativeLayout drawerPane;
    public static ListView lvNav;
    public static List<NavItem> listNavItems;
    public static List<Fragment>listFragments;
    ActionBarDrawerToggle actionBarDrawerToggle;
    public static TextView name,emailaddress;
    public static boolean inSessionMain;
    ViewPager viewPager;
    TabHost tabHost;
    public static boolean isInSession() {
        return inSessionMain;
    }

    public static void setInSession(boolean inSession) {
        inSessionMain = inSession;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initTabHost();
        initViewPager();

        // Profile Box data - IMP - Dont delete
        name=(TextView)findViewById(R.id.text1);
        emailaddress=(TextView)findViewById(R.id.text2);

        // Action bar Menu Button  - IMP - Dont Delete
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MyProfile myProfile=new MyProfile();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerPane=(RelativeLayout)findViewById(R.id.drawer_pane);

        lvNav=(ListView)findViewById(R.id.nav_list);

        // Icons and Names of Items in the Floatig Menu

        listNavItems=new ArrayList<NavItem>();
        listNavItems.add(new NavItem(R.drawable.profile,"Profile"));
        listNavItems.add(new NavItem(R.drawable.settings_icon,"Orders"));
        listNavItems.add(new NavItem(R.drawable.info_icon,"Terms and Conditions"));

        NavListAdapter navListAdapter=new NavListAdapter(getApplicationContext(),R.layout.item_nav_list,listNavItems);
        lvNav.setAdapter(navListAdapter);

        // Listening to the items in the Floating Menu

        listFragments=new ArrayList<Fragment>();
        if(myProfile.inSession==false)
            listFragments.add(new MyLoginProfile());
        else
            listFragments.add(new MyLoggedProfile());
        listFragments.add(new MyPastOrders());
        listFragments.add(new MyAbout());

        drawerLayout.closeDrawer(drawerPane);

        //set Listener for Navigation Items
        lvNav.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //replace the item with selection accordingly
                        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_content, (Fragment) listFragments.get(position)).commit();
                        setTitle(listNavItems.get(position).getTitle());
                        lvNav.setItemChecked(position,true);
                        drawerLayout.closeDrawer(drawerPane);

                    }
                }
        );

        //create listener for drawer layout
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(actionBarDrawerToggle.onOptionsItemSelected(item))
           return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        DatabaseHelper db=new DatabaseHelper(this);
        db.onAppStop();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean isinsession=savedInstanceState.getBoolean("isinsession");

        if((savedInstanceState != null)||(isinsession)){
            boolean inSession = savedInstanceState.getBoolean("loggedin");
            if (MainActivity.isInSession()) {
                String email = savedInstanceState.getString("email");
                String pass = savedInstanceState.getString("password");
                String type = "login";
                BackgroundWorker bw = new BackgroundWorker(this);
                //(Name,password,email,contact,address,type
                bw.execute(null, pass, email, null, null, type);
            }
        }
    }

    /// TABHOST DATA STARTS FROM HERE

    private void initTabHost() {
        tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();

        String tabNames[]={"Home","Menu","Cart","Contact Us"};
        for(int i=0;i<tabNames.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec=tabHost.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new fakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }
        tabHost.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId) {
        int selectedItem=tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);


        HorizontalScrollView  hScrollView=(HorizontalScrollView)findViewById(R.id.h_scroll_view);
        View tabView=tabHost.getCurrentTabView();
        int scrollpos=tabView.getLeft()-(hScrollView.getWidth()-tabView.getWidth())/2;
        hScrollView.smoothScrollTo(scrollpos,0);

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
    private void initViewPager() {
        viewPager=(ViewPager)findViewById(R.id.view_pager);

        List<Fragment> listFragments1=new ArrayList<Fragment>();
        listFragments1.add(new MyHome());
        listFragments1.add(new MyMenu());
        listFragments1.add(new MyCartFragment());
        listFragments1.add(new MyAbout());

        MyFragmentPagerAdapter myFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),listFragments1);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);

    }
    private void callLoginDialog()
    {
        myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.layout_logindialog);
        myDialog.setCancelable(false);
        Button login = (Button) myDialog.findViewById(R.id.button);

        EditText emailaddr = (EditText) myDialog.findViewById(R.id.editText16);
        EditText password = (EditText) myDialog.findViewById(R.id.editText17);
        myDialog.show();

        login.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }
        });
    }
}