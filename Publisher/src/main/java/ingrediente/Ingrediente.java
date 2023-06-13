package ingrediente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Ingrediente implements Serializable {

	private String nombre, codAlarma;
	private double minimo, critico, maximo;
	private double cantidad;
}
