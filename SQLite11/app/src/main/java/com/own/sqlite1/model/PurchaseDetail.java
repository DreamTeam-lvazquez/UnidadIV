package com.own.sqlite1.model;

/**
 * Created by DLira on 11/04/2018.
 */

public class PurchaseDetail {
    private String idPurchase;
    private String idPurchaseHeader;
    private String idProducto;
    private int quantity;
    private int sequence;

    public PurchaseDetail(String idPurchase, String idPurchaseHeader, String idProducto, int quantity, int sequence) {
        this.idPurchase = idPurchase;
        this.idPurchaseHeader = idPurchaseHeader;
        this.idProducto = idProducto;
        this.quantity = quantity;
        this.sequence = sequence;
    }

    public PurchaseDetail() {
        this("", "", "", 00,00);
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdPurchaseHeader() {
        return idPurchaseHeader;
    }

    public void setIdPurchaseHeader(String idPurchaseHeader) {
        this.idPurchaseHeader = idPurchaseHeader;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "PurchaseDetail{" +
                "idPurchase='" + idPurchase + '\'' +
                ", idPurchaseHeader='" + idPurchaseHeader + '\'' +
                ", idProducto='" + idProducto + '\'' +
                ", quantity=" + quantity +
                ", sequence=" + sequence +
                '}';
    }
}//End
