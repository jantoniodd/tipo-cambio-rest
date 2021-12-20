package pe.jaddiaz.tipocambiorest.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.jaddiaz.tipocambiorest.controller.dto.OperacionRequest;
import pe.jaddiaz.tipocambiorest.daos.ITipoCambioRepository;
import pe.jaddiaz.tipocambiorest.dto.OperacionDTO;
import pe.jaddiaz.tipocambiorest.dto.TipoCambioDTO;
import pe.jaddiaz.tipocambiorest.models.TipoCambio;
import pe.jaddiaz.tipocambiorest.models.TipoCambioId;

@Service("tipoCambioService")
public class TipoCambioServiceImpl implements TipoCambioService {

	private ITipoCambioRepository repository;

	@Autowired
	public TipoCambioServiceImpl(ITipoCambioRepository repository) {
		this.repository = repository;
	}

	@Override
	public Single<TipoCambioDTO> guardar(TipoCambioDTO dto) {

		return Single.create(single -> {

			TipoCambioId id = new TipoCambioId();
			id.setFecha(dto.getFecha());
			id.setMonedaOrigen(dto.getMonedaOrigen());
			id.setMonedaDestino(dto.getMonedaDestino());

			Optional<TipoCambio> obj = this.repository.findById(id);

			if (obj.isEmpty()) {

				TipoCambio entity = new TipoCambio();

				entity.setId(id);
				entity.setImporteCambio(dto.getImporteCambio());

				entity = repository.save(entity);

				single.onSuccess(dto);

			} else {
				single.onError(
						new DuplicateKeyException("Ya existe el registro"));
			}
		});

	}

	@Override
	public Single<List<TipoCambio>> obtener() {

		return Single.create(single -> {
			List<TipoCambio> cambio = repository.findAll();
			single.onSuccess(cambio);
		});

	}

	@Override
	public Single<OperacionDTO> operacion(OperacionRequest req) {

		return Single.create(single -> {

			String tipo = "1";

			TipoCambioId id = new TipoCambioId();

			id.setFecha(LocalDate.now());

			id.setMonedaOrigen(req.getMonedaOrigen());
			id.setMonedaDestino(req.getMonedaDestino()); // COMPRA

			Optional<TipoCambio> optObj = this.repository.findById(id);

			if (optObj.isEmpty()) {

				tipo = "2";

				id.setMonedaOrigen(req.getMonedaDestino());
				id.setMonedaDestino(req.getMonedaOrigen());

				optObj = this.repository.findById(id);

			}

			TipoCambio cambio = optObj.orElseThrow(
					() -> new EntityNotFoundException("No encontrado"));

			OperacionDTO op = new OperacionDTO();

			op.setImporteInicial(req.getImporte());
			op.setTipocambio(cambio.getImporteCambio());
			op.setMonedaOrigen(req.getMonedaOrigen());
			op.setMonedaDestino(req.getMonedaDestino());

			switch (tipo) {

				case "1" :

					op.setImporteFinal(Math.round(req.getImporte()
							/ cambio.getImporteCambio() * 100.0) / 100.0);

					break;

				case "2" :

					op.setImporteFinal(
							((req.getImporte() * cambio.getImporteCambio())
									* 100.0) / 100.0);
					break;

			};

			single.onSuccess(op);

		});

	}

}
