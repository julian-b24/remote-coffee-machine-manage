import com.zeroc.Ice.*;

import McControlador.ControladorMQ;
import com.zeroc.Ice.Object;
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

      BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
              communicator.propertyToProxy("Broker")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");
      ControladorMQ service = new ControladorMQ();
      SubscriberService subscriberService = service;
      ObjectPrx objectPrx = adapter.add(subscriberService, Util.stringToIdentity("Subscriber"));
      SubscriberServicePrx subscriberServicePrx = SubscriberServicePrx.checkedCast(objectPrx);

      service.setGateway(new Gateway(alarmaReliableS, ventas, recetaServicePrx, publisherServicePrx, subscriberServicePrx, brokerServicePrx));
      service.attach();
      service.setCodMaquina(service.quemarCodMaquina());
      service.subscribeToBroker();

      adapter.activate();
      service.run();
      communicator.waitForShutdown();
    }
  }
}
