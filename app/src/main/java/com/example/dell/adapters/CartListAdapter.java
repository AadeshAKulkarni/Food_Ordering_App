package com.example.dell.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.models.CartItems;
import com.example.dell.models.NavItem;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.List;


public class CartListAdapter extends ArrayAdapter<CartItems> {
    Context context;
    int resLayout;
    List<CartItems> listCartItems;
    int pos;
    public CartListAdapter(Context context,int resLayout,List <CartItems> listCartItems) {
        super(context, resLayout, listCartItems);
        this.context=context;
        this.resLayout=resLayout;
        this.listCartItems=listCartItems;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,resLayout,null);
        pos=position;
        TextView name=(TextView)v.findViewById(R.id.t1);
        TextView qty=(TextView)v.findViewById(R.id.t2);
        TextView price=(TextView)v.findViewById(R.id.t3);
        Button delete=(Button)v.findViewById(R.id.button6);

        final CartItems cartItems=listCartItems.get(position);
        name.setText(cartItems.getItemname());
        qty.setText(String.valueOf(cartItems.getQty()));
        price.setText(String.valueOf(cartItems.getPrice()));
        delete.setText(String.valueOf(cartItems.getDel()));

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //delete from database too
                        if(position>0) {
                            listCartItems.remove(position);
                            DatabaseHelper db=new DatabaseHelper(v.getContext());
                            db.onDelete(Integer.toString(cartItems.getId()));
                            notifyDataSetChanged();
                            db.close();
                        }
                    }
                }
        );

        return v;
    }
    public void deleteItemCart(){

    }
}
