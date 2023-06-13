package controller;

import alarma.Alarma;
import alarma.AlarmaStorage;
import com.zeroc.Ice.Current;
import lombok.Setter;
import servicios.AlarmaServicePrx;
import servicios.Moneda;
import servicios.ReliableMessageAlarmaService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Setter
public class ReliableMessageAlarmaController implements ReliableMessageAlarmaService {

    private AlarmaServicePrx alarmaService;
    private AlarmaStorage alarmaStorage;

    public static void main(String[] args) {

    }

    @Override
    public void sendACK(String uuidAlarma, Current current) {
        alarmaStorage.deleteAlarm(uuidAlarma);
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdIng", iDing);
        Alarma alarma = new Alarma(Alarma.ALARMA_INGREDIENTE, idMaq, new Date(), extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionEscasezIngredientes(iDing, idMaq, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, Current current) {
        Alarma alarma = new Alarma(-1, idMaq, new Date(), null);
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
        Alarma alarma = new Alarma(Alarma.ALARMA_SUMINISTRO, idMaq, new Date(), null);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionEscasezSuministro(idSumin, idMaq, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("Cantidad", String.valueOf(cantidad));
        Alarma alarma = new Alarma(Alarma.ALARMA_INGREDIENTE, idMaq, new Date(), extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionAbastesimiento(idMaq, idInsumo, cantidad, uuidACK.toString());
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("descripcion", descri);
        Alarma alarma = new Alarma(Alarma.ALARMA_MAL_FUNCIONAMIENTO, idMaq, new Date(), extraInfo);
        UUID uuidACK = UUID.randomUUID();
        alarmaStorage.saveAlarm(alarma, uuidACK);
        alarmaService.recibirNotificacionMalFuncionamiento(idMaq, descri, uuidACK.toString());
    }

}
