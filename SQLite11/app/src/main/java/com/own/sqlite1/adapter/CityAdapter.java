package com.own.sqlite1.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.own.sqlite1.R;
import com.own.sqlite1.model.City;

import java.util.List;

/**
 * Created by vazquez on 08/04/2018.
 */

public class CityAdapter extends RecyclerView.Adapter <CityAdapter.CityViewHolder>
        implements View.OnClickListener {

    List<City> cities;
    View.OnClickListener listener;
    //Constructor
    public CityAdapter(List<City> cities){
        this.cities =cities;
    }
    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_cell_layout,parent,false);
        CityViewHolder holder=new CityViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.txvName.setText(cities.get(position).getNameCity());
        holder.txvLocation.setText(cities.get(position).getLocation());
        holder.txvCode.setText(cities.get(position).getCodePostal());
        holder.txvCountry.setText(cities.get(position).getCountry());
        holder.txvHabit.setText(cities.get(position).getNumberInhabitants());
        holder.txvType.setText(cities.get(position).getType());
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class CityViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        CardView crvCity;
        TextView txvName;
        TextView txvLocation;
        TextView txvCode;
        TextView txvCountry;
        TextView txvHabit;
        TextView txvType;
        ImageButton btnEdit;
        ImageButton btnDelete;
        View.OnClickListener listener;




        public CityViewHolder(View itemView) {
            super(itemView);
            crvCity=(CardView) itemView.findViewById(R.id.crv_city);
            txvName=(TextView)itemView.findViewById(R.id.txv_name);
            txvLocation =(TextView)itemView.findViewById(R.id.txv_location);
            txvCode =(TextView)itemView.findViewById(R.id.txv_code);
            txvCountry=(TextView)itemView.findViewById(R.id.txv_country);
            txvHabit =(TextView)itemView.findViewById(R.id.txv_habit);
            txvType =(TextView)itemView.findViewById(R.id.txv_type);
            btnEdit =(ImageButton) itemView.findViewById(R.id.btn_edit);
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

        public void setListener(View.OnClickListener listener){
            this.listener=listener;

        }
    }

}
