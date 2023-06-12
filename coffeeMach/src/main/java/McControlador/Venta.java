package McControlador;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Venta implements Serializable {

	private String nombre, id;
	private int valor;
	private Date fecha;

}
