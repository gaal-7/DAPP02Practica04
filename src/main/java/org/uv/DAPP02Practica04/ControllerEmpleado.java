package org.uv.DAPP02Practica04;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author enigma
 */

@RestController
@RequestMapping("/api/empleados")
public class ControllerEmpleado {
    
    private final RepositoryEmpleado empleadoRepository;

    @Autowired
    public ControllerEmpleado(RepositoryEmpleado empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empleado> createEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoRepository.save(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(
            @PathVariable Long id, 
            @Valid @RequestBody Empleado empleadoDetails) {
        
        return empleadoRepository.findById(id)
                .map(empleadoExistente -> {
                    empleadoExistente.setNombre(empleadoDetails.getNombre());
                    empleadoExistente.setDireccion(empleadoDetails.getDireccion());
                    empleadoExistente.setTelefono(empleadoDetails.getTelefono());
                    
                    Empleado empleadoActualizado = empleadoRepository.save(empleadoExistente);
                    return ResponseEntity.ok(empleadoActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Long id) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    empleadoRepository.delete(empleado);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrió un error: " + ex.getMessage());
    }
}