package org.uv.DAPP02Practica04;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author enigma
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "productos_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "productos_id_seq",
            sequenceName = "productos_id_seq", initialValue = 1,
            allocationSize = 1)
    private Long clave;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false, columnDefinition = "MONEY")
    private BigDecimal precio;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}

