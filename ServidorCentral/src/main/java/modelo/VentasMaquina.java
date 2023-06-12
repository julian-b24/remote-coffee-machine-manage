package modelo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentasMaquina {

	private int consecutivo;
	private int idMaquina;
	private Date fechaInicial;
	private Date fechaFinal;
	private List<VentasReceta> detalle;
	private double valor;
		
}
