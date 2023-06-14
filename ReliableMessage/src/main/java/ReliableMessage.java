import com.zeroc.Ice.*;
import controller.ReliableMessageAlarmaController;


import java.util.*;
import servicios.*;

public class ReliableMessage {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {

            AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(communicator.propertyToProxy("alarmas")).ice_twoway();

            BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
                    communicator.propertyToProxy("Broker")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessageAlarmas");
            ReliableMessageAlarmaController service = new ReliableMessageAlarmaController();
            ReliableMessageAlarmaService reliableMessageAlarmaService = service;
            ObjectPrx objectPrx = adapter.add(reliableMessageAlarmaService, Util.stringToIdentity("ReliableMessageAlarmas"));
            ReliableMessageAlarmaServicePrx reliableMessageAlarmaServicePrx = ReliableMessageAlarmaServicePrx.checkedCast(objectPrx);

            service.setAlarmaService(alarmaS);
            service.setBrokerServicePrx(brokerServicePrx);
            service.setReliableMessageAlarmaServicePrx(reliableMessageAlarmaServicePrx);
            service.start();

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
