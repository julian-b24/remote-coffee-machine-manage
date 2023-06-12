package monedero;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepositoMonedas implements Serializable {

	private String tipo;
	private int cantidad, minimo, critico, recarga;

}
