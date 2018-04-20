package com.own.sqlite1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.own.sqlite1.adapter.PurchaseAdapter;
import com.own.sqlite1.model.MethodPayment;
import com.own.sqlite1.model.PurchaseHeader;
import com.own.sqlite1.model.Supplier;
import com.own.sqlite1.sqlite.Contract;
import com.own.sqlite1.sqlite.DBOperations;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by vazquez on 08/04/2018.
 */

public class PurchaseActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private Spinner spiSupplier;
    private Spinner spiMethod;
    private EditText edtDate;
    private EditText edtTotalAmount;
    private ImageButton btnDate;
    private Button btnSave;
    private DBOperations operations;
    private PurchaseHeader header = new PurchaseHeader();
    private RecyclerView rcvHeaders;
    private List<PurchaseHeader> headers =new ArrayList<PurchaseHeader>();
    private List<Supplier> suppliers =new ArrayList<Supplier>();
    private List<MethodPayment> methods =new ArrayList<MethodPayment>();
    private PurchaseAdapter purchaseAdapter;
    private SimpleCursorAdapter supplierAdapter;
    private SimpleCursorAdapter methodAdapter;
    private Supplier supplier;
    private MethodPayment method;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        operations= DBOperations.getDBOperations(getApplicationContext());
        header.setIdPurchaseHeader("");
        initComponents();
    }
    private void initComponents(){
        rcvHeaders = findViewById(R.id.rcv_headers);
        rcvHeaders.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rcvHeaders.setLayoutManager(layoutManager);
        getPurchases();
        getSuppliers();
        getMethods();
        purchaseAdapter =new PurchaseAdapter(headers, operations);

        purchaseAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_detail:
                        Intent intent = new Intent(PurchaseActivity.this,
                                PurchaseDetailActivity.class);
                        Bundle bundle = new Bundle();
                        header = headers.get(rcvHeaders.getChildPosition((View)v.getParent().getParent()));
                        bundle.putSerializable("header", (Serializable) header);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    case R.id.btn_delete:
                        operations.deletePurchaseHeader(headers.get(rcvHeaders.getChildPosition((View)v.getParent().getParent())).getIdPurchaseHeader());
                        getPurchases();
                        purchaseAdapter.notifyDataSetChanged();
                        clearData();
                        break;
                    case R.id.btn_edit:
                        Cursor c = operations.getOrderById(headers.get(
                                rcvHeaders.getChildPosition((View)v.getParent().getParent())).getIdPurchaseHeader());
                        if (c!=null) {

                            if (c.moveToFirst()) {
                                header = new PurchaseHeader(c.getString(0),
                                        c.getString(1), c.getString(2), c.getString(3), c.getString(4));
                                c = operations.getSuppliersById(header.getIdSupplier());
                                c.moveToFirst();
                                for (Supplier custom: suppliers) {
                                    if(c.getString(1).equals(custom.getIdSupplier())){
                                        supplier = custom;
                                        break;
                                    }
                                }

                                c = operations.getMethodsPaymentById(header.getIdMethodPayment());
                                c.moveToFirst();
                                for (MethodPayment met: methods) {
                                    if(c.getString(1).equals(met.getIdMethodPayment())){
                                        method = met;
                                        break;
                                    }
                                }

                            }

                            spiSupplier.setSelection(suppliers.indexOf(supplier));
                            spiMethod.setSelection(methods.indexOf(method));
                            edtDate.setText(header.getDate());


                        }else{
                            Toast.makeText(getApplicationContext(),"Record not found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }

            }
        });
        rcvHeaders.setAdapter(purchaseAdapter);

        spiSupplier =(Spinner) findViewById(R.id.spi_customer);
        spiMethod =(Spinner) findViewById(R.id.spi_method);

        methodAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item,
                operations.getMethodsPayment(),
                new String[]{Contract.MethodsPayment.NAME},
                new int[]{android.R.id.text1},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spiMethod.setAdapter(methodAdapter);

        supplierAdapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item,
                operations.getSuppliers(),
                new String[]{Contract.Suppliers.FIRSTNAME, Contract.Suppliers.LASTNAME},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        spiSupplier.setAdapter(supplierAdapter);

        edtDate = findViewById(R.id.edt_date);
        btnDate = findViewById(R.id.btn_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderActivity.DatePickerFragment fragment =
                        new OrderActivity.DatePickerFragment();
                Bundle bundle = new Bundle();
                bundle.putString("date", edtDate.getText().toString());
                fragment.setArguments(bundle);
                fragment.show(getFragmentManager(), "date");
            }
        });

        btnSave =(Button)findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supplier = suppliers.get(spiSupplier.getSelectedItemPosition());
                method = methods.get(spiMethod.getSelectedItemPosition());
                if (!header.getIdPurchaseHeader().equals("")){
                    header.setIdSupplier(supplier.getIdSupplier());
                    header.setIdMethodPayment(method.getIdMethodPayment());
                    header.setDate(edtDate.getText().toString());
                    header.setDate(edtTotalAmount.getText().toString());
                    operations.updatePurchaseHeader(header);

                }else {
                    header = new PurchaseHeader("", supplier.getIdSupplier(),
                            method.getIdMethodPayment(), edtDate.getText().toString(), edtTotalAmount.getText().toString());
                    operations.insertPurchaseHeader(header);
                }
                getPurchases();
                clearData();
                purchaseAdapter.notifyDataSetChanged();
            }
        });

    }
    private void getPurchases(){
        Cursor c =operations.getPurchases();
        headers.clear();
        if(c!=null){
            while (c.moveToNext()){

                headers.add(new PurchaseHeader(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }
    }
    private void getMethods(){
        Cursor c =operations.getMethodsPayment();
        methods.clear();
        if (c!=null){
            while(c.moveToNext()){
                methods.add(new MethodPayment(c.getString(1),c.getString(2)));
            }
        }
    }
    private void getSuppliers(){
        Cursor c =operations.getSuppliers();
        suppliers.clear();
        if(c!=null){
            while (c.moveToNext()){
                suppliers.add(new Supplier(c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }
    }

    private void clearData(){
        spiSupplier.setSelection(0);
        spiMethod.setSelection(0);
        edtDate.setText("");
        header =null;
        header = new PurchaseHeader;
        header.setIdPurchaseHeader("");
    }

    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        edtDate.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker datePicker,
                          int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        setDate(calendar);
    }



    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year, month, day;
            bundle = getArguments();
            if(!bundle.getString("date").equals("")){
                DateFormat format = DateFormat.getDateInstance();
                try {
                    Date dateEdit = format.parse(
                            bundle.getString("date"));
                    calendar.setTime(dateEdit);
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener) getActivity(),
                    year, month, day);
        }

    }
}
