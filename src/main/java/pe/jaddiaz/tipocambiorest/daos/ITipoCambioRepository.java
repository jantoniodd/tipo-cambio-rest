package pe.jaddiaz.tipocambiorest.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.jaddiaz.tipocambiorest.models.TipoCambio;
import pe.jaddiaz.tipocambiorest.models.TipoCambioId;

public interface ITipoCambioRepository
		extends
			JpaRepository<TipoCambio, TipoCambioId> {

}
