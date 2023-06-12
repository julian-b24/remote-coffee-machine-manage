package modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecetaIngrediente {

	private Receta receta;
	private Ingrediente ingrediente;
	private int cantidad;
		
}
