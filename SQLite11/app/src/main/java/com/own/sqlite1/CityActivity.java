package com.own.sqlite1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.own.sqlite1.adapter.CityAdapter;

import com.own.sqlite1.model.City;

import com.own.sqlite1.model.Product;
import com.own.sqlite1.sqlite.DBOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devtacho on 15/03/18.
 */

public class CityActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtLocation;
    private EditText edtCode;
    private EditText edtHabit;
    private EditText edtCountry;
    private EditText edtType;
    private Button btnSave;
    private DBOperations operations;
    private City city = new City();
    private RecyclerView rcvCity;
    private List<City> cities =new ArrayList<City>();
    private CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        //iniciacion de la instancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        city.setIdCity("");


        initComponents();
    }
    private void initComponents(){
        rcvCity = findViewById(R.id.rcv_cities);
        rcvCity.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rcvCity.setLayoutManager(layoutManeger);
        //
        getCities();
        adapter=new CityAdapter(cities);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete:
                        operations.deleteCity(cities.get(rcvCity.getChildPosition((View)v.getParent().getParent())).getIdCity());
                        getCities();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_edit:

                        Toast.makeText(getApplicationContext(),"Editar",Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getCityById(cities.get(
                                rcvCity.getChildPosition(
                                        (View)v.getParent().getParent())).getIdCity());
                        if (c!=null){
                            if (c.moveToFirst()){
                                city = new City(c.getString(1),c.getString(2), c.getString(3), c.getString(4), c.getInt(5),c.getInt(6),c.getString(7));
                            }
                            edtName.setText(city.getNameCity());
                            edtLocation.setText(city.getLocation());
                            edtCountry.setText(city.getCountry());
                            edtCode.setText(String.valueOf(city.getCodePostal()));
                            edtHabit.setText(String.valueOf(city.getNumberInhabitants()));
                            edtType.setText(city.getType());
                        }else{
                            Toast.makeText(getApplicationContext(),"Record not found",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rcvCity.setAdapter(adapter);



        edtName =(EditText)findViewById(R.id.edt_name);
        edtCode =(EditText)findViewById(R.id.edt_firstname);
        edtLocation =(EditText)findViewById(R.id.edt_code);
        edtCountry =(EditText)findViewById(R.id.edt_lastname);
        edtType =(EditText)findViewById(R.id.edt_county);
        edtHabit =(EditText)findViewById(R.id.edt_phone);
        btnSave =(Button)findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!city.getIdCity().equals("")){
                    city.setNameCity(edtName.getText().toString());
                    city.setCodePostal(Integer.parseInt(
                            edtCode.getText().toString()));
                    city.setLocation(edtLocation.getText().toString());
                    city.setCountry(edtCountry.getText().toString());
                    city.setNumberInhabitants(Integer.parseInt(
                            edtHabit.getText().toString()));
                    city.setType(edtType.getText().toString());
                    operations.updateCity(city);

                }else {
                    city = new City("", edtName.getText().toString(), edtLocation.getText().toString(),edtCountry.getText().toString(),Integer.parseInt(edtCode.getText().toString()),Integer.parseInt(edtHabit.getText().toString()),edtType.getText().toString());
                    operations.insertCity(city);
                }
                getCities();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getCities(){
        Cursor c =operations.getCities();
        cities.clear();
        if(c!=null){
            while (c.moveToNext()){
                cities.add(new City(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5),c.getInt(6), c.getString(7)));
            }
        }

    }

    private void clearData(){
        edtName.setText("");
        edtLocation.setText("");
        edtCode.setText("");
        edtHabit.setText("");
        edtCountry.setText("");
        edtType.setText("");
        city =null;
        city = new City();
        city.setIdCity("");
    }

}
