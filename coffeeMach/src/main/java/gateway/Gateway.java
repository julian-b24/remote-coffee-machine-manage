package gateway;

import lombok.AllArgsConstructor;
import servicios.AlarmaServicePrx;
import servicios.Moneda;
import servicios.RecetaServicePrx;
import servicios.VentaServicePrx;

@AllArgsConstructor
public class Gateway {
    private AlarmaServicePrx alarmaServicePrx;
    private VentaServicePrx ventaServicePrx;
    private RecetaServicePrx recetaServicePrx;

    public void enviarNotificacionAbastecimiento(int codMaquina, String idAlarma, int cantidad) {
        alarmaServicePrx.recibirNotificacionAbastesimiento(codMaquina, idAlarma, cantidad);
    }

    public void enviarNotificacionMalFuncionamiento(int codMaquina, String desc) {
        alarmaServicePrx.recibirNotificacionMalFuncionamiento(codMaquina, desc);
    }
    
    public void enviarNotificacionEscasezIngredientes(String ingNombre, int codMaquina) {
        alarmaServicePrx.recibirNotificacionEscasezIngredientes(ingNombre, codMaquina);
    }

    public void enviarNotificacionInsuficienciaMoneda(Moneda moneda, int codMaquina) {
        alarmaServicePrx.recibirNotificacionInsuficienciaMoneda(moneda, codMaquina);
    }

    public void registrarVenta(int codMaquina, String[] arregloVentas) {
        ventaServicePrx.registrarVenta(codMaquina, arregloVentas);
    }

    public String[] consultarProductos() {
        return recetaServicePrx.consultarProductos();
    }
}
