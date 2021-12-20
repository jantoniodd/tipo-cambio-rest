package pe.jaddiaz.tipocambiorest.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIPO_CAMBIO")
public class TipoCambio {

	@EmbeddedId
	private TipoCambioId id;

	@Column(precision = 3)
	private Double importeCambio;

}
