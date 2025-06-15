package edu.uisrael.poliza.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimuladorDto {

    private String id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 10, max = 15, message = "La cédula debe tener entre 10 y 15 dígitos")
    private String cedula;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(min = 6, max = 50, message = "El email debe tener entre 6 y 50 caracteres")
    private String email;

    @NotNull(message = "El monto no puede estar vacío")
    @DecimalMin(value = "100.00", message = "El monto debe ser mínimo 100")
    @DecimalMax(value = "999999.99", message = "El monto debe ser menor o igual a 999999.99")
    private BigDecimal monto;

    private BigDecimal montoFinal;

    @NotNull(message = "El periodo de capitalización no puede estar vacío")
    private Integer periodo;

    @NotNull(message = "El tiempo no puede estar vacío")
    @Min(value = 1, message = "El tiempo debe ser mínimo 1 año")
    private Integer anios;

    private BigDecimal tasaInteres;
}
