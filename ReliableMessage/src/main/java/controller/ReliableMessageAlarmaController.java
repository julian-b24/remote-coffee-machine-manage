package controller;

import com.zeroc.Ice.Current;
import servicios.AlarmaService;
import servicios.AlarmaServicePrx;
import servicios.Moneda;
import servicios.ReliableMessageService;

import java.util.Map;
import java.util.UUID;

public class ReliableMessageAlarmaController implements ReliableMessageService {

    private AlarmaServicePrx alarmaService;

    private Map<UUID, Object> alarmaMap;


    @Override
    public void sendACK(String uuidAlarma, Current current) {

    }
}
