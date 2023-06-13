package receta;

import ingrediente.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class Receta implements Serializable {

	private String descripcion, id;
	private int valor;
	private HashMap<Ingrediente, Double> listaIngredientes;

}
