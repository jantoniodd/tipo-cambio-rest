package pe.jaddiaz.tipocambiorest.services;

import java.util.List;

import io.reactivex.Single;
import pe.jaddiaz.tipocambiorest.controller.dto.OperacionRequest;
import pe.jaddiaz.tipocambiorest.dto.OperacionDTO;
import pe.jaddiaz.tipocambiorest.dto.TipoCambioDTO;
import pe.jaddiaz.tipocambiorest.models.TipoCambio;

public interface TipoCambioService {

	Single<TipoCambioDTO> guardar(TipoCambioDTO dto);

	Single<List<TipoCambio>> obtener();

	Single<OperacionDTO> operacion(OperacionRequest req);

}
