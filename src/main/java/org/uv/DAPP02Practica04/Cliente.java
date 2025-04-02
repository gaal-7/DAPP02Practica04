package org.uv.DAPP02Practica04;

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
@Table(name="clientes")
public class Cliente { 
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "clientes_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clientes_id_seq",
            sequenceName = "clientes_id_seq", initialValue = 1,
            allocationSize = 1)
    private Long clave;

    @Column(name = "nombre", nullable = false)
    private String nombre;

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
}
