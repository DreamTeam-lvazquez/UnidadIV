package com.own.sqlite1.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.own.sqlite1.R;
import com.own.sqlite1.model.Supplier;

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter <SupplierAdapter.SupplierViewHolder> implements View.OnClickListener {
    List<Supplier> suppliers;
    View.OnClickListener listener;
    //Constructor
    public SupplierAdapter(List<Supplier> suppliers){
        this.suppliers = suppliers;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public SupplierAdapter.SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_cell_layout,parent,false);
        SupplierAdapter.SupplierViewHolder holder = new SupplierAdapter.SupplierViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(SupplierAdapter.SupplierViewHolder holder, int position) {
        holder.txvFirstName.setText(suppliers.get(position).getFirstname());
        holder.txvLastName.setText(suppliers.get(position).getLastname());
        holder.txvType.setText(suppliers.get(position).getType());
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class SupplierViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvSupplier;
        TextView txvFirstName;
        TextView txvLastName;
        TextView txvType;
        ImageButton btnEdit;
        ImageButton btnDelete;
        View.OnClickListener listener;

        public SupplierViewHolder(View itemView) {
            super(itemView);
            crvSupplier = (CardView) itemView.findViewById(R.id.crv_supplier);
            txvFirstName = (TextView)itemView.findViewById(R.id.txv_firstname);
            txvLastName = (TextView)itemView.findViewById(R.id.txv_lastname);
            txvType = (TextView)itemView.findViewById(R.id.txv_type_supplier);
            btnEdit = (ImageButton) itemView.findViewById(R.id.btn_edit);
            btnDelete =(ImageButton) itemView.findViewById(R.id.btn_delete);
            btnEdit.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener!=null){
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener){this.listener=listener;}
    }

}//End
