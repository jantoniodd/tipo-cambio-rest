package pe.jaddiaz.tipocambiorest.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.jaddiaz.tipocambiorest.controller.dto.BaseResponse;
import pe.jaddiaz.tipocambiorest.controller.dto.OperacionRequest;
import pe.jaddiaz.tipocambiorest.dto.OperacionDTO;
import pe.jaddiaz.tipocambiorest.dto.TipoCambioDTO;
import pe.jaddiaz.tipocambiorest.models.TipoCambio;
import pe.jaddiaz.tipocambiorest.services.TipoCambioService;

@RestController
@RequestMapping("v1/tipo-cambio")
public class TipoCambioController {

	private TipoCambioService service;

	@Autowired
	public TipoCambioController(TipoCambioService service) {
		this.service = service;
	}

	@PostMapping
	public Single<ResponseEntity<TipoCambioDTO>> guardar(
			@RequestBody TipoCambioDTO dto) {

		return service.guardar(dto).subscribeOn(Schedulers.io())
				.map(s -> ResponseEntity
						.created(URI.create("/tipo-cambio/" + s)).body(s));
	}

	@GetMapping
	public Single<ResponseEntity<List<TipoCambioDTO>>> obtener() {

		return service.obtener().subscribeOn(Schedulers.io()).map(
				cambio -> ResponseEntity.ok(toTipoCambioResponseList(cambio)));

	}

	@PostMapping("operacion")
	public Single<ResponseEntity<BaseResponse<OperacionDTO>>> operacion(
			@RequestBody OperacionRequest req) {

		return service.operacion(req).subscribeOn(Schedulers.io())
				.map(s -> ResponseEntity.ok(BaseResponse.successWithData(s)));

	}

	private List<TipoCambioDTO> toTipoCambioResponseList(
			List<TipoCambio> bookResponseList) {
		return bookResponseList.stream().map(this::toTipoCambioResponse)
				.collect(Collectors.toList());
	}

	private TipoCambioDTO toTipoCambioResponse(TipoCambio e) {
		TipoCambioDTO cambio = new TipoCambioDTO();

		cambio.setFecha(e.getId().getFecha());
		cambio.setMonedaOrigen(e.getId().getMonedaOrigen());
		cambio.setMonedaDestino(e.getId().getMonedaDestino());

		cambio.setImporteCambio(e.getImporteCambio());

		return cambio;
	}

}
