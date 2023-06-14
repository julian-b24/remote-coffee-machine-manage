package controller;

import alarma.Alarma;
import alarma.AlarmaStorage;
import com.zeroc.Ice.Current;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import servicios.AlarmaServicePrx;
import servicios.Moneda;
import servicios.ReliableMessageAlarmaService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
public class ReliableMessageAlarmaController implements ReliableMessageAlarmaService, Runnable {

    private AlarmaServicePrx alarmaService;
    private AlarmaStorage alarmaStorage;

    public ReliableMessageAlarmaController(){
        alarmaStorage = new AlarmaStorage();
    }

    @Override
    public void run() {
        /*
        try {
            while(!alarmaStorage.getStorage().isEmpty()){
                for (UUID uuid: alarmaStorage.getStorage().keySet()) {
                    Alarma alarma = alarmaStorage.getStorage().get(uuid);
                    switch (alarma.getTipo()){
                        case Alarma.ALARMA_INGREDIENTE:
                            alarmaService.recibirNotificacionEscasezIngredientes(alarma.getExtraInfo().get("IdIng"), alarma.getIdMaquina(), uuid.toString());
                            break;
                        case Alarma.ALARMA_MONEDA_CIEN:
                            alarmaService.recibirNotificacionInsuficienciaMoneda(Moneda.CIEN, alarma.getIdMaquina(), uuid.toString());
                            break;
                        case Alarma.ALARMA_MONEDA_DOS:
                            alarmaService.recibirNotificacionInsuficienciaMoneda(Moneda.DOCIENTOS, alarma.getIdMaquina(), uuid.toString());
                            break;
                        case Alarma.ALARMA_MONEDA_QUI:
                            alarmaService.recibirNotificacionInsuficienciaMoneda(Moneda.QUINIENTOS, alarma.getIdMaquina(), uuid.toString());
                            break;
                        case Alarma.ALARMA_SUMINISTRO:
                            alarmaService.recibirNotificacionEscasezSuministro(alarma.getExtraInfo().get("IdSumin"), alarma.getIdMaquina(), uuid.toString());
                            break;
                        case Alarma.ALARMA_NOTIFICACION_ABASTECIMIENTO:
                            alarmaService.recibirNotificacionAbastesimiento(alarma.getIdMaquina(), alarma.getExtraInfo().get("IdInsumo"), Integer.parseInt(alarma.getExtraInfo().get("Cantidad")), uuid.toString());
                            break;
                        case Alarma.ALARMA_MAL_FUNCIONAMIENTO:
                            alarmaService.recibirNotificacionMalFuncionamiento(alarma.getIdMaquina(), alarma.getExtraInfo().get("descripcion"), uuid.toString());
                            break;
                    }
                }
            }
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void sendACK(String uuidAlarma, Current current) {
        alarmaStorage.deleteAlarm(uuidAlarma);
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdIng", iDing);
        Alarma alarma = new Alarma(Alarma.ALARMA_INGREDIENTE, idMaq,   extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionEscasezIngredientes(iDing, idMaq, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, Current current) {
        Alarma alarma = new Alarma(-1, idMaq,   null);
        switch (moneda) {
            case CIEN:
                alarma.setTipo(Alarma.ALARMA_MONEDA_CIEN);
                break;
            case DOCIENTOS:
                alarma.setTipo(Alarma.ALARMA_MONEDA_DOS);
                break;
            case QUINIENTOS:
                alarma.setTipo(Alarma.ALARMA_MONEDA_QUI);
                break;
            default:
                break;
        }
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionInsuficienciaMoneda(moneda, idMaq, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdSumin", idSumin);
        Alarma alarma = new Alarma(Alarma.ALARMA_SUMINISTRO, idMaq,  null);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionEscasezSuministro(idSumin, idMaq, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("Cantidad", String.valueOf(cantidad));
        extraInfo.put("IdInsumo", String.valueOf(idInsumo));
        Alarma alarma = new Alarma(Alarma.ALARMA_NOTIFICACION_ABASTECIMIENTO, idMaq, extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionAbastesimiento(idMaq, idInsumo, cantidad, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        HashMap<String, String> extraInfo = new HashMap<>();
        extraInfo.put("descripcion", descri);
        Alarma alarma = new Alarma(Alarma.ALARMA_MAL_FUNCIONAMIENTO, idMaq,  extraInfo);//
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionMalFuncionamiento(idMaq, descri, uuidACK.toString());
    }

}
