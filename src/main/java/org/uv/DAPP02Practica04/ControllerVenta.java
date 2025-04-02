package org.uv.DAPP02Practica04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author enigma
 */

@RestController
@RequestMapping("/api/ventas")
public class ControllerVenta {

    @Autowired
    private RepositoryVenta ventaRepository;

    @Autowired
    private RepositoryCliente clienteRepository;

    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        if (!clienteRepository.existsById(venta.getIdCliente())) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + venta.getIdCliente());
        }
        
        venta.setFecha(new Date()); 
        Venta nuevaVenta = ventaRepository.save(venta);
        return ResponseEntity.ok(nuevaVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));
        return ResponseEntity.ok(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));

        if (!clienteRepository.existsById(ventaDetails.getIdCliente())) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + ventaDetails.getIdCliente());
        }

        venta.setFecha(ventaDetails.getFecha());
        venta.setMonto(ventaDetails.getMonto());
        venta.setIdCliente(ventaDetails.getIdCliente());

        Venta updatedVenta = ventaRepository.save(venta);
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));

        ventaRepository.delete(venta);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Venta> getVentasByCliente(@PathVariable Long idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + idCliente);
        }
        return ventaRepository.findByIdCliente(idCliente);
    }

    @GetMapping("/fechas")
    public List<Venta> getVentasByFechaRange(
            @RequestParam Date fechaInicio,
            @RequestParam Date fechaFin) {
        return ventaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
}
