package com.own.sqlite1.adapter;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.own.sqlite1.R;
import com.own.sqlite1.model.Customer;
import com.own.sqlite1.model.MethodPayment;

import com.own.sqlite1.model.Supplier;
import com.own.sqlite1.sqlite.DBOperations;

import java.util.List;

/**
 * Created by vazquez on 05/04/2018.
 */

public class PurchaseAdapter
        extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>
        implements View.OnClickListener {

    List<PurchaseAdapter> purchaseAdapters;
    Supplier supplier;
    MethodPayment method;
    DBOperations operations;
    View.OnClickListener listener;
    //Constructor
    public PurchaseAdapter(List<PurchaseAdapter> orders, DBOperations operations){
        this.purchaseAdapters = orders;
        this.operations = operations;

    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public PurchaseAdapter.PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla el cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_cell_layout,parent,false);
        PurchaseAdapter.PurchaseViewHolder holder=new PurchaseAdapter.PurchaseViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PurchaseAdapter.PurchaseViewHolder holder, int position) {
        Cursor c = operations.getSuppliersById(purchaseAdapters.get(position).getSupplier());
        if(c.moveToNext()){
            supplier = new Supplier(c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4));
        }
        c=operations.getMethodsPaymentById(purchaseAdapters.get(position).getIdMethodPayment());
        if(c.moveToNext()){
            method = new MethodPayment(c.getString(1), c.getString(2));
        }
        holder.txvSupplier.setText(supplier.getFirstname()+" "+ supplier.getLastname());
        holder.txvMethod.setText(method.getName());
        holder.txvDate.setText(purchaseAdapters.get(position).getDate());

        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return purchaseAdapters.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class PurchaseViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvOrder;
        TextView txvSupplier;
        TextView txvMethod;
        TextView txvDate;
        ImageButton btnEdit;
        ImageButton btnDelete;
        ImageButton btnDetail;
        View.OnClickListener listener;




        public PurchaseViewHolder(View itemView) {
            super(itemView);
            crvOrder=(CardView) itemView.findViewById(R.id.crv_order);
            txvSupplier=(TextView)itemView.findViewById(R.id.txv_product);
            txvMethod=(TextView)itemView.findViewById(R.id.txv_quantity);
            txvDate=(TextView)itemView.findViewById(R.id.txv_price);
            btnEdit =(ImageButton) itemView.findViewById(R.id.btn_edit);
            btnDelete =(ImageButton) itemView.findViewById(R.id.btn_delete);
            btnDetail =(ImageButton) itemView.findViewById(R.id.btn_detail);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
            btnDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }
}
