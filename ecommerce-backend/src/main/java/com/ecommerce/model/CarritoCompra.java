package com.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarritoCompra {

    @Id
    private Long idArticulo;
    private int cantidad;

    // Getters y Setters
    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
