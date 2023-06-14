package gateway;

import lombok.AllArgsConstructor;
import servicios.*;

@AllArgsConstructor
public class Gateway {
    private ReliableMessageAlarmaServicePrx reliableMessageAlarmaServicePrx;
    private VentaServicePrx ventaServicePrx;
    private RecetaServicePrx recetaServicePrx;
    private PublisherServicePrx publisherServicePrx;
    private SubscriberServicePrx subscriberServicePrx;

    public void enviarNotificacionAbastecimiento(int codMaquina, String idAlarma, int cantidad) {
        reliableMessageAlarmaServicePrx.recibirNotificacionAbastesimiento(codMaquina, idAlarma, cantidad);
    }

    public void enviarNotificacionMalFuncionamiento(int codMaquina, String desc) {
        reliableMessageAlarmaServicePrx.recibirNotificacionMalFuncionamiento(codMaquina, desc);
    }
    
    public void enviarNotificacionEscasezIngredientes(String ingNombre, int codMaquina) {
        reliableMessageAlarmaServicePrx.recibirNotificacionEscasezIngredientes(ingNombre, codMaquina);
    }

    public void enviarNotificacionInsuficienciaMoneda(Moneda moneda, int codMaquina) {
        reliableMessageAlarmaServicePrx.recibirNotificacionInsuficienciaMoneda(moneda, codMaquina);
    }

    public void registrarVenta(int codMaquina, String[] arregloVentas) {
        ventaServicePrx.registrarVenta(codMaquina, arregloVentas);
    }

    public String[] consultarProductos() {
        return recetaServicePrx.consultarProductos();
    }

    public void attachPublisher(){
        publisherServicePrx.attach(subscriberServicePrx);
    }
}
