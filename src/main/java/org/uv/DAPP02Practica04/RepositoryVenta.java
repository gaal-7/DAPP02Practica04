package org.uv.DAPP02Practica04;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author enigma
 */

public interface RepositoryVenta extends JpaRepository<Venta, Long> {
    List<Venta> findByIdCliente(Long idCliente);
    List<Venta> findByFechaBetween(Date fechaInicio, Date fechaFin);
}
