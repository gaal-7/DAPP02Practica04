package org.uv.DAPP02Practica04;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author enigma
 */

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "detalle_venta_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "detalle_venta_id_seq",
            sequenceName = "detalle_venta_id_seq", initialValue = 1,
            allocationSize = 1)
    private Long clave;
    
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;
    
    @Column(name = "precio", nullable = false, columnDefinition = "MONEY")
    private BigDecimal precio;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    @Column(name = "id_venta", nullable = false)
    private Long idVenta;
    
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_venta", insertable = false, updatable = false)
    private Venta venta;

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}