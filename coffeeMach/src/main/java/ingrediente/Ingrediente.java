package ingrediente;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ingrediente implements Serializable {

	private String nombre, codAlarma;
	private double minimo, critico, maximo;
	private double cantidad;
}
