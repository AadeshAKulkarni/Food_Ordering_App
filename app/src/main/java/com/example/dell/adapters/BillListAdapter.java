package com.example.dell.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.models.BillItems;
import com.example.dell.models.CartItems;
import com.example.dell.slidingmenu_tabhostviewpager.R;

import java.util.List;


public class BillListAdapter extends ArrayAdapter<BillItems> {
    Context context;
    int resLayout;
    List<BillItems> listBillItems;
    int pos;
    public BillListAdapter(Context context, int resLayout, List <BillItems> listCartItems) {
        super(context, resLayout, listCartItems);
        this.context=context;
        this.resLayout=resLayout;
        this.listBillItems=listCartItems;
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

        final BillItems billItems=listBillItems.get(position);
        name.setText(billItems.getItemname());
        qty.setText(String.valueOf(billItems.getQty()));
        price.setText(String.valueOf(billItems.getPrice()));

        return v;
    }

}
