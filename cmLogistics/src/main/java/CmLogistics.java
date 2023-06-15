import java.util.*;
import com.zeroc.Ice.*;
import controller.LogisticaController;
import servicios.BrokerServicePrx;
import servicios.ServiceLogistica;
import servicios.ServiceLogisticaPrx;
import servicios.ServicioComLogisticaPrx;

public class CmLogistics {
    public static void main(String[] args) {
        List<String> extArgs = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "CmLogistic.cfg", extArgs)) {
            ObjectAdapter adapter = communicator.createObjectAdapter("CmLogistic");

            ServicioComLogisticaPrx logisticaServerPrx = ServicioComLogisticaPrx.checkedCast(
                    communicator.propertyToProxy("logistica")).ice_twoway();

            LogisticaController service = new LogisticaController(logisticaServerPrx);

            ServiceLogistica serviceLogistica = service;
            ObjectPrx objectPrx = adapter.add(serviceLogistica, com.zeroc.Ice.Util.stringToIdentity("CmLogistic"));
            ServiceLogisticaPrx serviceLogisticaPrx = ServiceLogisticaPrx.checkedCast(objectPrx);
            service.attachServer(serviceLogisticaPrx);

            service.start();
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
