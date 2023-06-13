package receta;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class RecetasStorage {

    private ArrayList<Receta> recetas;

    public RecetasStorage(){
        recetas = new ArrayList<Receta>();
    }

    public void agregarReceta(Receta receta){
        recetas.add(receta);
    }

    public Receta obtenerNuevaReceta(){
        return recetas.get(recetas.size() - 1);
    }

}
