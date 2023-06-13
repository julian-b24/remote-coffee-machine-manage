import com.zeroc.Ice.*;
import controller.ReliableMessageAlarmaController;


import java.util.*;
import servicios.*;

public class ReliableMessage {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "ReliableMessage.cfg", extPar)) {

            AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(communicator.propertyToProxy("alarmas")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessage");
            ReliableMessageAlarmaController service = new ReliableMessageAlarmaController();
            service.setAlarmaService(alarmaS);
            service.run();

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
