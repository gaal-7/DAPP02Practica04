package org.uv.DAPP02Practica04;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author enigma
 */

@RestController
@RequestMapping("/api/detalle-venta")
public class ControllerDetalleVenta {
    
    @Autowired
    private RepositoryDetalleVenta detalleVentaRepository;
    
    @GetMapping
    public List<DetalleVenta> getAllDetallesVenta() {
        return detalleVentaRepository.findAll();
    }
    
    @PostMapping
    public DetalleVenta createDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVentaById(@PathVariable Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de venta no encontrado con id: " + id));
        return ResponseEntity.ok(detalleVenta);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> updateDetalleVenta(@PathVariable Long id, @RequestBody DetalleVenta detalleVentaDetails) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de venta no encontrado con id: " + id));
        
        detalleVenta.setIdProducto(detalleVentaDetails.getIdProducto());
        detalleVenta.setPrecio(detalleVentaDetails.getPrecio());
        detalleVenta.setCantidad(detalleVentaDetails.getCantidad());
        detalleVenta.setIdVenta(detalleVentaDetails.getIdVenta());
        detalleVenta.setDescripcion(detalleVentaDetails.getDescripcion());
        
        DetalleVenta updatedDetalleVenta = detalleVentaRepository.save(detalleVenta);
        return ResponseEntity.ok(updatedDetalleVenta);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetalleVenta(@PathVariable Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de venta no encontrado con id: " + id));
        
        detalleVentaRepository.delete(detalleVenta);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/venta/{idVenta}")
    public List<DetalleVenta> getDetallesByVenta(@PathVariable Long idVenta) {
        return detalleVentaRepository.findByIdVenta(idVenta);
    }
}
