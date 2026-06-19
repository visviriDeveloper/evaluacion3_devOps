package com.rentacar.rentacar.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la entidad Vehículo en el sistema.
 * Se utilizan anotaciones de Lombok para reducir el código repetitivo (Boilerplate).
 * Se utilizan validaciones de Jakarta para asegurar la integridad de los datos.
 */
@Data // Genera automáticamente Getters, Setters, toString, equals y hashCode.
@AllArgsConstructor // Crea un constructor con todos los atributos (útil para pruebas rápidas).
@NoArgsConstructor // Crea un constructor sin parámetros (obligatorio para frameworks como Spring/JPA).
public class Auto {

    private Long id;

    @NotBlank(message = "La marca no puede estar vacía o solo con espacios")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotBlank(message = "La patente es obligatoria para el registro")
    private String patente;

    @NotNull // Garantiza que el campo booleano siempre tenga un valor (true/false)
    private boolean disponible;

    @Min(value = 1, message = "El precio diario debe ser al menos de $1")
    private double precioDiario;
}