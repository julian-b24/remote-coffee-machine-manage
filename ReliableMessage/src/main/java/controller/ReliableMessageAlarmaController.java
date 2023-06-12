package controller;

import alarma.Alarma;
import alarma.AlarmaStorage;
import com.zeroc.Ice.Current;
import com.zeroc.Ice.OutputStream;
import com.zeroc.Ice.UserException;
import com.zeroc.IceInternal.Incoming;
import lombok.Setter;
import servicios.AlarmaService;
import servicios.AlarmaServicePrx;
import servicios.Moneda;
import servicios.ReliableMessageService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@Setter
public class ReliableMessageAlarmaController implements ReliableMessageService, AlarmaService {

    private AlarmaServicePrx alarmaService; //TODO: Agregar uuid a sus métodos para realizar ACK
    private AlarmaStorage alarmaStorage;

    @Override
    public void sendACK(String uuidAlarma, Current current) {
        alarmaStorage.deleteAlarm(uuidAlarma);
    }

    @Override
    public void recibirNotificacionEscasezIngredientes(String iDing, int idMaq, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("IdIng", iDing);
        Alarma alarma = new Alarma(Alarma.ALARMA_INGREDIENTE, idMaq, new Date(), extraInfo);
        alarmaStorage.saveAlarm(alarma);
        alarmaService.recibirNotificacionEscasezIngredientes(iDing, idMaq);
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
        alarmaStorage.saveAlarm(alarma);
        alarmaService.recibirNotificacionInsuficienciaMoneda(moneda, idMaq);
    }

    @Override
    public void recibirNotificacionEscasezSuministro(String idSumin, int idMaq, Current current) {
        Alarma alarma = new Alarma(Alarma.ALARMA_SUMINISTRO, idMaq, new Date(), null);
        alarmaStorage.saveAlarm(alarma);
        alarmaService.recibirNotificacionEscasezSuministro(idSumin, idMaq);
    }

    @Override
    public void recibirNotificacionAbastesimiento(int idMaq, String idInsumo, int cantidad, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("Cantidad", String.valueOf(cantidad));
        Alarma alarma = new Alarma(Alarma.ALARMA_INGREDIENTE, idMaq, new Date(), extraInfo);
        alarmaStorage.saveAlarm(alarma);
        alarmaService.recibirNotificacionAbastesimiento(idMaq, idInsumo, cantidad);
    }

    @Override
    public void recibirNotificacionMalFuncionamiento(int idMaq, String descri, Current current) {
        Map<String, String> extraInfo = new HashMap<>();
        extraInfo.put("descripcion", descri);
        Alarma alarma = new Alarma(Alarma.ALARMA_MAL_FUNCIONAMIENTO, idMaq, new Date(), extraInfo);
        alarmaStorage.saveAlarm(alarma);
        alarmaService.recibirNotificacionMalFuncionamiento(idMaq, descri);
    }


    @Override
    public String[] ice_ids(Current current) {
        return ReliableMessageService.super.ice_ids(current);
    }

    @Override
    public String ice_id(Current current) {
        return ReliableMessageService.super.ice_id(current);
    }

    @Override
    public CompletionStage<OutputStream> _iceDispatch(Incoming in, Current current) throws UserException {
        return ReliableMessageService.super._iceDispatch(in, current);
    }
}
