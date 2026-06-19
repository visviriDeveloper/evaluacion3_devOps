package com.rentacar.rentacar.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentacar.rentacar.Model.Auto;
import com.rentacar.rentacar.Repository.AutoRepository;

/**
 * CAPA DE SERVICIO: El "Cerebro" de la aplicación.
 * Aquí se implementan las Reglas de Negocio. El servicio decide SI se puede 
 * o NO se puede realizar una acción antes de tocar los datos.
 */
@Service // Indica a Spring que esta clase maneja la lógica de negocio (Bean de Servicio).
public class AutoService {

    @Autowired // Inyectamos el repositorio para comunicarnos con la "Base de Datos" en memoria.
    private AutoRepository repository;

    /**
     * Obtiene la lista completa sin filtros.
     */
    public List<Auto> listarTodos() {
        return repository.findAll();
    }

    /**
     * REGLA: Solo mostrar vehículos listos para arrendar.
     * Filtra la lista original usando Streams.
     */
    public List<Auto> listarDisponibles() {
        return repository.findAll().stream()
                .filter(Auto::isDisponible) // Solo deja pasar los que tengan disponible == true
                .collect(Collectors.toList());
    }

    /**
     * REGLA DE NEGOCIO: No pueden existir dos autos con la misma patente.
     * @return true si se guardó, false si la patente ya existía.
     */
    public boolean guardar(Auto nuevoAuto) {
        // Buscamos si ya existe algún auto con esa patente (ignorando mayúsculas)
        boolean existe = repository.findAll().stream()
                .anyMatch(a -> a.getPatente().equalsIgnoreCase(nuevoAuto.getPatente()));

        if (existe) {
            return false; // Detenemos el proceso: violación de regla de negocio.
        }

        repository.save(nuevoAuto); // Si no existe, procedemos a guardar.
        return true;
    }

    /**
     * REGLA DE NEGOCIO: Gestión de estados de arriendo.
     * @return 0: No existe el ID | 1: Ya está arrendado | 2: Arriendo exitoso.
     */
    public int arrendarVehiculo(Long id) {
        // 1. Intentamos buscar el vehículo por su ID
        Optional<Auto> autoOpt = repository.findById(id);

        if (autoOpt.isEmpty()) {
            return 0; // Error: Vehículo no encontrado en el sistema.
        }

        // 2. Si existe, extraemos el objeto del Optional
        Auto auto = autoOpt.get();

        // 3. Verificamos si realmente se puede arrendar
        if (!auto.isDisponible()) {
            return 1; // Error: El vehículo ya está ocupado por otro cliente.
        }

        // 4. Si pasa todas las validaciones, actualizamos el estado en el repositorio
        repository.updateEstado(auto.getId());
        return 2; // Todo correcto.
    }

    /**
     * Elimina un vehículo del sistema por su ID.
     */
    public boolean eliminar(Long id) {
        return repository.delete(id);
    }
}