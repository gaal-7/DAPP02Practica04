package org.uv.DAPP02Practica04;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author enigma
 */
public interface RepositoryProducto extends JpaRepository<Producto, Long> {
    
}
