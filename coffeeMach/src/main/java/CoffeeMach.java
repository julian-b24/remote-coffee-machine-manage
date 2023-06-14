import com.zeroc.Ice.*;

import McControlador.ControladorMQ;
import gateway.Gateway;

import java.util.*;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {

      VentaServicePrx ventas = VentaServicePrx.checkedCast(
          communicator.propertyToProxy("ventas")).ice_twoway();
      RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(
          communicator.propertyToProxy("receta")).ice_twoway();
      ReliableMessageAlarmaServicePrx alarmaReliableS = ReliableMessageAlarmaServicePrx.checkedCast(
              communicator.propertyToProxy("ReliableMessageAlarmas")).ice_twoway();
      PublisherServicePrx publisherServicePrx = PublisherServicePrx.checkedCast(
              communicator.propertyToProxy("Publisher")).ice_twoway();


      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");
      ControladorMQ service = new ControladorMQ();
      SubscriberServicePrx subscriberServicePrx = SubscriberServicePrx.uncheckedCast(
              adapter.createProxy(Util.stringToIdentity("Subscriber")).ice_twoway());
      service.setGateway(new Gateway(alarmaReliableS, ventas, recetaServicePrx, publisherServicePrx, subscriberServicePrx));
      service.attach();

      adapter.add(service, Util.stringToIdentity("CoffeeMach"));
      service.run();
      adapter.activate();
      communicator.waitForShutdown();
    }
  }
}
