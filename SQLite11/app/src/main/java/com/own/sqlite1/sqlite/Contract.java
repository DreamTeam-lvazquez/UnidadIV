package com.own.sqlite1.sqlite;

import java.util.UUID;

/**
 * Created by devtacho on 9/03/18.
 */

public class Contract {
    interface OrderHeaderColumns{
        String ID = "id";
        String DATE = "record_date";
        String ID_CUSTOMER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
    }

    interface OrderDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String PRICE = "price";
    }

    interface PurchaseHeaderColumns{
        String ID = "id";
        String DATE = "record_date";
        String ID_SUPPLIER = "id_customer";
        String ID_METHOD_PAYMENT = "id_method_payment";
        String TOTAL_AMOUNT = "total_amount";
    }

    interface PurchaseDetailColumns{
        String ID = "id";
        String SEQUENCE = "sequence";
        String ID_PRODUCT = "id_product";
        String QUANTITY = "quantity";
        String ID_PURCHASE_HEADER="id_purchase_header";
    }

    interface ProductColumns{
        String ID = "id";
        String NAME = "name";
        String PRICE = "price";
        String STOCK = "stock";
    }

    interface  CustomerColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String PHONE = "phone";
    }

    interface MethodPaymentColumns{
        String ID = "id";
        String NAME = "name";
    }

    //Song - start
    interface CityColumns{
        String ID = "id";
        String NAME = "name";
        String LOCATION = "location";
        String CODE = "code";
        String NUMBERHABIT = "numberHabi";
        String COUNTRY = "county";
        String TYPE = "type";
    }
    //Song - end

    interface  SupplierColumns{
        String ID = "id";
        String FIRSTNAME = "firstname";
        String LASTNAME = "lastname";
        String TYPE = "type";
    }

    public static class OrderHeaders implements OrderHeaderColumns{
        public static String generateIdOrderHeader(){
            return  "OH-"+ UUID.randomUUID().toString();
        }
    }
    public static class PurchaseHeaders implements PurchaseHeaderColumns{
        public static String generateIdPurchaseHeader(){
            return  "PH-"+ UUID.randomUUID().toString();
        }
    }


    public static class OrderDetails implements OrderDetailColumns{}
    public static class PurchaseDetails implements PurchaseDetailColumns{}

    public static class Products implements ProductColumns{
        public static String generateIdProduct(){
            return  "PR-"+ UUID.randomUUID().toString();
        }
    }

    public static class Customers implements CustomerColumns{
        public static String generateIdCustomer(){
            return  "CU-"+ UUID.randomUUID().toString();
        }
    }

    public static class MethodsPayment implements MethodPaymentColumns{
        public static String generateIdMethodPayment(){
            return  "MP-"+ UUID.randomUUID().toString();
        }
    }

    //Song method start
    public static class Cities implements CityColumns{
        public static String generateIdCity(){
            return  "CY-"+ UUID.randomUUID().toString();
        }
    }
    //Song method end

    public static class Suppliers implements SupplierColumns{
        public static String generateIdSupplier(){
            return  "SP-"+ UUID.randomUUID().toString();
        }
    }

    private Contract() {}

}//End
