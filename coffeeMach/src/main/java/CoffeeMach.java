import com.zeroc.Ice.*;

import McControlador.ControladorMQ;
import gateway.Gateway;

import java.util.*;
import servicios.*;

public class CoffeeMach {
  public static void main(String[] args) {
    List<String> extPar = new ArrayList<>();
    try (Communicator communicator = Util.initialize(args, "coffeMach.cfg", extPar)) {

      AlarmaServicePrx alarmaS = AlarmaServicePrx.checkedCast(
          communicator.propertyToProxy("alarmas")).ice_twoway();
      VentaServicePrx ventas = VentaServicePrx.checkedCast(
          communicator.propertyToProxy("ventas")).ice_twoway();
      RecetaServicePrx recetaServicePrx = RecetaServicePrx.checkedCast(
          communicator.propertyToProxy("recetas")).ice_twoway();

      ObjectAdapter adapter = communicator.createObjectAdapter("CoffeMach");
      ControladorMQ service = new ControladorMQ();
      service.setGateway(new Gateway(alarmaS, ventas, recetaServicePrx));

      service.run();
      adapter.add((ServicioAbastecimiento) service, Util.stringToIdentity("abastecer"));
      adapter.activate();
      communicator.waitForShutdown();
    }
  }
}
