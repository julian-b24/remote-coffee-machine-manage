package alarma;

import java.util.Date;

import com.zeroc.Ice.Current;

import lombok.Setter;
import servicios.*;

@Setter
public class AlarmaGenerator implements AlarmaService {

    public static final int ALARMA_INGREDIENTE = 1;
    public static final int ALARMA_MONEDA_CIEN = 2;
    public static final int ALARMA_MONEDA_DOS = 3;
    public static final int ALARMA_MONEDA_QUI = 4;
    public static final int ALARMA_SUMINISTRO = 5;
    public static final int ALARMA_MAL_FUNCIONAMIENTO = 6;

    private AlarmasManager manager;
    private BrokerServicePrx brokerServicePrx;

    public AlarmaGenerator(AlarmasManager manager, BrokerServicePrx brokerServicePrx) {
        this.manager = manager;
        this.brokerServicePrx = brokerServicePrx;
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, String uuidACK, ReliableMessageAlarmaServicePrx proxy, Current current) {
        manager.alarmaMaquina(ALARMA_INGREDIENTE, idMaq, new Date());
        proxy.sendACK(uuidACK);
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, String uuidACK, ReliableMessageAlarmaServicePrx proxy, Current current) {
        switch (moneda) {
            case CIEN:
                manager.alarmaMaquina(ALARMA_MONEDA_CIEN, idMaq, new Date());
                proxy.sendACK(uuidACK);
                break;
            case DOCIENTOS:
                manager.alarmaMaquina(ALARMA_MONEDA_DOS, idMaq, new Date());
                proxy.sendACK(uuidACK);
                break;
            case QUINIENTOS:
                manager.alarmaMaquina(ALARMA_MONEDA_QUI, idMaq, new Date());
                proxy.sendACK(uuidACK);
                break;
            default:
                break;
        }
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, String uuidACK, ReliableMessageAlarmaServicePrx proxy, Current current) {
        // suministro
        manager.alarmaMaquina(ALARMA_SUMINISTRO, idMaq, new Date());
        proxy.sendACK(uuidACK);
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, String uuidACK, ReliableMessageAlarmaServicePrx proxy, Current current) {
        // TODO validar el insumo
        manager.desactivarAlarma(ALARMA_INGREDIENTE, idMaq, new Date());
        proxy.sendACK(uuidACK);
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, String uuidACK, ReliableMessageAlarmaServicePrx proxy, Current current) {
        manager.alarmaMaquina(ALARMA_MAL_FUNCIONAMIENTO, idMaq, new Date());
        proxy.sendACK(uuidACK);
    }

    public void subscribeToBroker(AlarmaServicePrx server){
        System.out.println(brokerServicePrx.subscribeServer(server));
    }

}
