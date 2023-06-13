import com.zeroc.Ice.*;
import controller.PublisherController;


import java.util.*;
import servicios.*;

public class Publisher {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "Publisher.cfg", extPar)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("ReliableMessage");
            PublisherController service = new PublisherController();
            //service.setAlarmaService(alarmaS);

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
