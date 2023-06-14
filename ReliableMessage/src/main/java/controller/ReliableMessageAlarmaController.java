package controller;

import alarma.ReliableAlarma;
import alarma.AlarmaStorage;
import com.zeroc.Ice.ConnectFailedException;
import com.zeroc.Ice.Current;
import lombok.Setter;
import servicios.*;

import java.util.HashMap;
import java.util.UUID;

@Setter
public class ReliableMessageAlarmaController extends Thread implements ReliableMessageAlarmaService {

    private AlarmaServicePrx alarmaService;
    private ReliableMessageAlarmaServicePrx reliableMessageAlarmaServicePrx;
    private AlarmaStorage alarmaStorage;
    private BrokerServicePrx brokerServicePrx;

    public ReliableMessageAlarmaController(){
        alarmaStorage = new AlarmaStorage();
    }

    public synchronized void setAlarmaService(AlarmaServicePrx alarmaService){
        this.alarmaService= alarmaService;
    }

    public synchronized AlarmaServicePrx getAlarmaService(){
        return alarmaService;
    }

    //@Override
    public void run() {

        while(true){
            try {
                while(!alarmaStorage.getStorage().isEmpty()){
                    System.out.println("Enviando alarmas pendientes...");
                    setAlarmaService(brokerServicePrx.locateServer());
                    System.out.println(getAlarmaService());
                    for (UUID uuid: alarmaStorage.getStorage().keySet()) {
                        ReliableAlarma alarma = alarmaStorage.getStorage().get(uuid);
                        switch (alarma.getTipo()){
                            case ReliableAlarma.ALARMA_INGREDIENTE:
                                getAlarmaService().recibirNotificacionEscasezIngredientes(alarma.getExtraInfo().get("IdIng"), alarma.getIdMaquina(), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_MONEDA_CIEN:
                                getAlarmaService().recibirNotificacionInsuficienciaMoneda(Moneda.CIEN, alarma.getIdMaquina(), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_MONEDA_DOS:
                                getAlarmaService().recibirNotificacionInsuficienciaMoneda(Moneda.DOCIENTOS, alarma.getIdMaquina(), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_MONEDA_QUI:
                                getAlarmaService().recibirNotificacionInsuficienciaMoneda(Moneda.QUINIENTOS, alarma.getIdMaquina(), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_SUMINISTRO:
                                getAlarmaService().recibirNotificacionEscasezSuministro(alarma.getExtraInfo().get("IdSumin"), alarma.getIdMaquina(), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_NOTIFICACION_ABASTECIMIENTO:
                                getAlarmaService().recibirNotificacionAbastesimiento(alarma.getIdMaquina(), alarma.getExtraInfo().get("IdInsumo"), Integer.parseInt(alarma.getExtraInfo().get("Cantidad")), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                            case ReliableAlarma.ALARMA_MAL_FUNCIONAMIENTO:
                                getAlarmaService().recibirNotificacionMalFuncionamiento(alarma.getIdMaquina(), alarma.getExtraInfo().get("descripcion"), uuid.toString(), reliableMessageAlarmaServicePrx);
                                break;
                        }
                    }
                }
            } catch (ConnectFailedException e){
                System.out.println("No es posible conectar con servidor central, se reintentará el envío de alarmas en 1 min");
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    @Override
    public void sendACK(String uuidAlarma, Current current) {
        System.out.println("Alarma eliminada: " + uuidAlarma);
        alarmaStorage.deleteAlarm(uuidAlarma);
        alarmaStorage.saveStorageToFile();
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdIng", iDing);
        ReliableAlarma alarma = new ReliableAlarma(ReliableAlarma.ALARMA_INGREDIENTE, idMaq,   extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaStorage.saveStorageToFile();
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, Current current) {
        ReliableAlarma alarma = new ReliableAlarma(-1, idMaq,   null);
        switch (moneda) {
            case CIEN:
                alarma.setTipo(ReliableAlarma.ALARMA_MONEDA_CIEN);
                break;
            case DOCIENTOS:
                alarma.setTipo(ReliableAlarma.ALARMA_MONEDA_DOS);
                break;
            case QUINIENTOS:
                alarma.setTipo(ReliableAlarma.ALARMA_MONEDA_QUI);
                break;
            default:
                break;
        }
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaStorage.saveStorageToFile();
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdSumin", idSumin);
        ReliableAlarma alarma = new ReliableAlarma(ReliableAlarma.ALARMA_SUMINISTRO, idMaq,  null);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaStorage.saveStorageToFile();
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("Cantidad", String.valueOf(cantidad));
        extraInfo.put("IdInsumo", String.valueOf(idInsumo));
        ReliableAlarma alarma = new ReliableAlarma(ReliableAlarma.ALARMA_NOTIFICACION_ABASTECIMIENTO, idMaq, extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaStorage.saveStorageToFile();
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        HashMap<String, String> extraInfo = new HashMap<String, String>();
        extraInfo.put("descripcion", descri);
        ReliableAlarma alarma = new ReliableAlarma(ReliableAlarma.ALARMA_MAL_FUNCIONAMIENTO, idMaq,  extraInfo);//
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaStorage.saveStorageToFile();
    }

}
