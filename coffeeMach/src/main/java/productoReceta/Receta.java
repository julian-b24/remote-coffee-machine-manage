package productoReceta;

import java.io.Serializable;
import java.util.HashMap;

import ingrediente.Ingrediente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Receta implements Serializable {

	private String descripcion, id;
	private int valor;
	private HashMap<Ingrediente, Double> listaIngredientes;

}
