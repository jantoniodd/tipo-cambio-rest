package pe.jaddiaz.tipocambiorest.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TipoCambioDTO {

	private LocalDate fecha;

	private String monedaOrigen;

	private String monedaDestino;

	private Double importeCambio;

}
