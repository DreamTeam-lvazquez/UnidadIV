package com.own.sqlite1.model;

/**
 * Created by Marili Arevalo on 2/7/2017.
 */

public class Supplier {
    private String idSupplier;
    private String firstname;
    private String lastname;
    private String type;

    public Supplier(String idSupplier, String firstname, String lastname, String type) {
        this.idSupplier = idSupplier;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    public Supplier() {
        this("","","","");
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "idSupplier='" + idSupplier + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}//End
