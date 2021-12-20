package pe.jaddiaz.tipocambiorest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionDTO {

	private String monedaOrigen;

	private String monedaDestino;

	private Double tipocambio;

	private Double importeInicial;

	private Double importeFinal;

}
