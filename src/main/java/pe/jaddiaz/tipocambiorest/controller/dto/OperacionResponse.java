package pe.jaddiaz.tipocambiorest.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OperacionResponse {

	private Double montoOriginal;

	private Double montofinal;

	private String monedaOrigen;

	private String monedaDestino;

	private Double tipoCambio;

}
