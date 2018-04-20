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

import com.own.sqlite1.adapter.SupplierAdapter;
import com.own.sqlite1.model.Supplier;
import com.own.sqlite1.sqlite.DBOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 10/04/2018.
 */

public class SupplierActivity extends AppCompatActivity {
    private EditText edtFirstname;
    private EditText edtLastname;
    private EditText edtType;
    private Button btnSave;
    private DBOperations operations;
    private Supplier supplier = new Supplier();
    private RecyclerView rcvSuppliers;
    private List<Supplier> suppliers =new ArrayList<Supplier>();
    private SupplierAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        operations= DBOperations.getDBOperations(getApplicationContext());
        supplier.setIdSupplier("");
        initComponents();
    }
    private void initComponents(){
        rcvSuppliers = findViewById(R.id.rcv_suppliers);
        rcvSuppliers.setHasFixedSize(true);
        LinearLayoutManager layoutManeger=new LinearLayoutManager(this);
        rcvSuppliers.setLayoutManager(layoutManeger);
        getSuppliers();
        adapter=new SupplierAdapter(suppliers);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_delete:
                        operations.deleteSupplier(suppliers.get(rcvSuppliers.getChildPosition((View)v.getParent().getParent())).getIdSupplier());
                        getSuppliers();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_edit:

                        Toast.makeText(getApplicationContext(),"Editar", Toast.LENGTH_SHORT).show();
                        Cursor c = operations.getSuppliersById(suppliers.get(
                                rcvSuppliers.getChildPosition(
                                        (View)v.getParent().getParent())).getIdSupplier());
                        if (c!=null){
                            if (c.moveToFirst()){
                                supplier = new Supplier(c.getString(1),
                                        c.getString(2), c.getString(3), c.getString(4));
                            }
                            edtFirstname.setText(supplier.getFirstname());
                            edtLastname.setText(supplier.getLastname());
                            edtType.setText(supplier.getType());
                        }else{
                            Toast.makeText(getApplicationContext(),"Record not found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rcvSuppliers.setAdapter(adapter);

        edtFirstname =(EditText)findViewById(R.id.edt_firstname);
        edtLastname =(EditText)findViewById(R.id.edt_lastname);
        edtType = (EditText)findViewById(R.id.edt_type);
        btnSave = (Button)findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!supplier.getIdSupplier().equals("")) {
                    supplier.setFirstname(edtFirstname.getText().toString());
                    supplier.setLastname(edtLastname.getText().toString());
                    supplier.setType(edtType.getText().toString());
                    operations.updateSupplier(supplier);
                }else {
                    supplier = new Supplier("", edtFirstname.getText().toString(), edtLastname.getText().toString(), edtType.getText().toString());
                    operations.insertSupplier(supplier);
                }
                getSuppliers();
                clearData();
                adapter.notifyDataSetChanged();
            }
        });
    }
    private void getSuppliers(){
        Cursor c = operations.getSuppliers();
        suppliers.clear();
        if(c!=null){
            while (c.moveToNext()){
                suppliers.add(new Supplier(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }
    }

    private void clearData(){
        edtFirstname.setText("");
        edtLastname.setText("");
        edtType.setText("");
        supplier = null;
        supplier = new Supplier();
        supplier.setIdSupplier("");
    }

}//End


