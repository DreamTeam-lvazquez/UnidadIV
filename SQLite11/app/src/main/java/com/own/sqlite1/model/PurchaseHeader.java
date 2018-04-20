package com.own.sqlite1.model;



/**
 * Created by vazquez on 05/04/2018.
 */

public class PurchaseHeader  {
    private String idPurchaseHeader;
    private String idSupplier;
    private String idMethodPayment;
    private String date;
    private String totalAmount;

    public PurchaseHeader(String idPurchaseHeader, String idSupplier, String idMethodPayment, String date, String totalAmount) {
        this.idPurchaseHeader = idPurchaseHeader;
        this.idSupplier = idSupplier;
        this.idMethodPayment = idMethodPayment;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getIdPurchaseHeader() {
        return idPurchaseHeader;
    }

    public void setIdPurchaseHeader(String idPurchaseHeader) {
        this.idPurchaseHeader = idPurchaseHeader;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getIdMethodPayment() {
        return idMethodPayment;
    }

    public void setIdMethodPayment(String idMethodPayment) {
        this.idMethodPayment = idMethodPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PurchaseHeader{" +
                "idPurchaseHeader='" + idPurchaseHeader + '\'' +
                ", idSupplier='" + idSupplier + '\'' +
                ", idMethodPayment='" + idMethodPayment + '\'' +
                ", date='" + date + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
