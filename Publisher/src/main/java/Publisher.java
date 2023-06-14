import com.zeroc.Ice.*;
import controller.PublisherController;


import java.util.*;
import servicios.*;

public class Publisher {
    public static void main(String[] args) {
        List<String> extPar = new ArrayList<>();

        try (Communicator communicator = Util.initialize(args, "Publisher.cfg", extPar)) {

            ObjectAdapter adapter = communicator.createObjectAdapter("Publisher");
            PublisherController service = new PublisherController(new ArrayList<>());

            adapter.add(service, Util.stringToIdentity("Publisher"));
            adapter.activate();
            System.out.println("Publisher up");
            communicator.waitForShutdown();
        }
    }
}
