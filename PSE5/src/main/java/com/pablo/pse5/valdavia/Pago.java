package com.pablo.pse5.valdavia;

import java.io.Serializable;

public class Pago implements Serializable {
    private String email;
    private String pago;

    public Pago(String email, String pago) {
        this.email = email;
        this.pago = pago;
    }

    public Pago() {

    }    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }
    
}
