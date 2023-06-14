import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import controller.BrokerController;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "Broker.cfg", extPar)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("Broker");
            BrokerController brokerController = new BrokerController();
            //com.zeroc.Ice.Object object = new BrokerController();
            adapter.add( brokerController, com.zeroc.Ice.Util.stringToIdentity("Broker"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
