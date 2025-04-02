package org.uv.DAPP02Practica04;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author enigma
 */
public interface RepositoryDetalleVenta extends JpaRepository<DetalleVenta, Long>{
    List<DetalleVenta> findByIdVenta(Long idVenta);
}
