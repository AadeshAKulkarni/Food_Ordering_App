package com.example.dell.slidingmenu_tabhostviewpager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.adapters.BackgroundCartWorker;
import com.example.dell.adapters.CartListAdapter;
import com.example.dell.adapters.DatabaseHelper;
import com.example.dell.adapters.MyExpandableListAdapter;
import com.example.dell.fragments.MyCart;
import com.example.dell.fragments.MyCartFragment;
import com.example.dell.fragments.MyMenu;
import com.example.dell.models.CartItems;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartDialog extends DialogFragment {
    TextView iname,t11;
    EditText qty;
    List listCartItems;
    boolean statusOfCart;
    LayoutInflater inflater;
    long success;
    ListView cartlistview;
    View view;
    public static int sprice[]={40,50,60,25,30,30,25,25,20,20,20,15,15};
    DatabaseHelper databaseHelper;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater=getActivity().getLayoutInflater();
        view=inflater.inflate(R.layout.addtocart_layout,null);
        databaseHelper=new DatabaseHelper(this.getContext());
        iname=(TextView)view.findViewById(R.id.textView7);
        iname.setText(MyMenu.item1);
        int id=Integer.parseInt(MyMenu.childid);
        qty=(EditText)view.findViewById(R.id.editText6);
        t11=(TextView)view.findViewById(R.id.textView11);
        t11.setText(Integer.toString(Integer.parseInt(qty.getText().toString())*sprice[id]));
        qty.addTextChangedListener(
                new TextWatcher() {
                    int id=Integer.parseInt(MyMenu.childid);
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String qty_value=qty.getText().toString();
                        if(!qty_value.equals(""))
                            t11.setText(Integer.toString(Integer.parseInt(qty_value)*sprice[id]));
                        else
                            t11.setText("");

                    }
                }
        );

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view).setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               onAddToCart();

                //FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.detach(getParentFragment()).attach(getParentFragment()).commit();
                //Fragment frg = null;
                //frg = getFragmentManager().findFragmentByTag("fragment_mycart");
                //final FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.detach(frg);
                //ft.attach(frg);
                //ft.commit();

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
    public void onAddToCart(){
        databaseHelper.onCreate();
        success=databaseHelper.onInsert(iname.getText().toString(),qty.getText().toString(),t11.getText().toString());
        Toast.makeText(getContext(),"Row id affected : "+success,Toast.LENGTH_SHORT).show();

      /* LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mcfview=inflater.inflate(R.layout.fragment_mycart,null,false);
        cartlistview=(ListView)mcfview.findViewById(R.id.listView1);

        Cursor res=databaseHelper.onView();
        int len=res.getCount();
        listCartItems = new ArrayList<CartItems>();
        listCartItems.add(new CartItems(9,"Item Name", "Quantity", "Price","Delete"));

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
                Toast.makeText(this.getContext(),itemname,Toast.LENGTH_SHORT).show();
                listCartItems.add(new CartItems(id,itemname, itemqty, itemprice,"X"));
            }
        }
        res.close();
        databaseHelper.close();


        CartListAdapter cartListAdapter = new CartListAdapter(getContext(), R.layout.cartlist_layout, listCartItems);
        cartlistview.setAdapter(cartListAdapter);
        */


    }
}
