package com.rentacar.rentacar.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.rentacar.rentacar.Model.Auto;

/**
 * CAPA DE REPOSITORIO: 
 * Es la encargada de simular la persistencia de datos (como una Base de Datos).
 * Aquí definimos las operaciones CRUD (Create, Read, Update, Delete).
 */
@Repository // Indica a Spring que esta clase es un componente de acceso a datos (Inyección de Dependencias).
public class AutoRepository {

    // Simulamos la tabla de la BD usando una lista en memoria (Volátil: se borra al reiniciar).
    private List<Auto> flota = new ArrayList<>(); 

    public AutoRepository() {
        // "Seed" o Semilla: Insertamos datos iniciales para poder probar la API de inmediato.
        flota.add(new Auto(1L, "Toyota", "Yaris", "AA-BB-11", true, 25000));
        flota.add(new Auto(2L, "Hyundai", "Accent", "CC-DD-22", false, 30000));
        flota.add(new Auto(3L, "Suzuki", "Swift", "EE-FF-33", true, 20000));
    }

    /**
     * SELECT * FROM autos
     * Retorna la lista completa de vehículos.
     */
    public List<Auto> findAll() {
        return flota;
    }

    /**
     * SELECT * FROM autos WHERE id = ?
     * @param id Identificador único del vehículo.
     * @return Un Optional que puede contener el Auto o estar vacío (evita el temido NullPointerException).
     */
    public Optional<Auto> findById(Long id) {
        // Usamos Streams para filtrar: "De la flota, busca el primero cuyo ID coincida".
        return flota.stream()
                    .filter(a -> a.getId().equals(id))
                    .findFirst();
    }

    /**
     * INSERT INTO autos (id, marca, ...) VALUES (...)
     * @param auto Objeto que viene desde el Servicio para ser guardado.
     */
    public void save(Auto auto) {
        flota.add(auto); // Agrega el objeto al final de nuestra lista (BD en memoria).
    }

    /**
     * DELETE FROM autos WHERE id = ?
     * @return true si encontró el ID y lo borró, false si no existía.
     */
    public boolean delete(Long id) {
        // Predicado lógico: "Remover si el ID coincide".
        return flota.removeIf(a -> a.getId().equals(id));
    }

    /**
     * UPDATE autos SET disponible = false WHERE id = ?
     * Lógica de actualización manual recorriendo la lista.
     */
    public Auto updateEstado(Long id) {
        int posicion = 0;
        
        for (int i = 0; i < flota.size(); i++) {
            // Importante: Comparamos el ID del auto en la posición actual con el ID recibido.
            if (flota.get(i).getId().equals(id)) { 
                flota.get(i).setDisponible(false); // Cambiamos el estado (Negocio: Arrendar).
                posicion = i;
                break; // Detenemos el ciclo una vez encontrado para ahorrar recursos.
            }
        }
        return flota.get(posicion);
    }
}