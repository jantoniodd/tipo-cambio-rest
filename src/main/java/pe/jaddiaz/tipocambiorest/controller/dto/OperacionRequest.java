package pe.jaddiaz.tipocambiorest.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperacionRequest {

	private Double importe;

	private String monedaOrigen;

	private String monedaDestino;

}
