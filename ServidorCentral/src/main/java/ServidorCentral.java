
import java.util.ArrayList;
import java.util.List;
import com.zeroc.Ice.*;
import comunicacion.*;
import interfaz.ControladorRecetas;
import receta.ProductoReceta;
import servicios.*;
import ventas.VentasManager;
import ServerControl.*;
import alarma.AlarmaGenerator;
import alarma.AlarmasManager;

public class ServidorCentral {

    public static void main(String[] args) {
        List<String> params = new ArrayList<>();
        try (Communicator communicator = Util.initialize(args, "server.cfg", params)) {

            BrokerServicePrx brokerServicePrx = BrokerServicePrx.checkedCast(
                    communicator.propertyToProxy("Broker")).ice_twoway();

            ObjectAdapter adapter = communicator.createObjectAdapter("Server");

            ServerControl control = new ServerControl(communicator);

            ControlComLogistica log = new ControlComLogistica(control);

            AlarmasManager alarmasManager = new AlarmasManager(communicator);
            log.setAlarmasManager(alarmasManager);

            AlarmaGenerator alarma = new AlarmaGenerator(alarmasManager, brokerServicePrx, log);

            ProductoReceta recetas = new ProductoReceta();
            recetas.setCommunicator(communicator);

            VentasManager ventas = new VentasManager();
            ventas.setCommunicator(communicator);

            adapter.add(alarma, Util.stringToIdentity("Alarmas"));
            adapter.add(ventas, Util.stringToIdentity("Ventas"));
            adapter.add(log, Util.stringToIdentity("logistica"));
            adapter.add(recetas, Util.stringToIdentity("Recetas"));

            PublisherServicePrx publisherServicePrx = PublisherServicePrx.checkedCast(
                    communicator.propertyToProxy("Publisher")).ice_twoway();


            AlarmaService alarmaService = alarma;
            ObjectPrx objectPrx = adapter.add(alarmaService, Util.stringToIdentity("AlarmaService"));
            AlarmaServicePrx alarmaServicePrx = AlarmaServicePrx.checkedCast(objectPrx);

            ControladorRecetas controladorRecetas = new ControladorRecetas();
            controladorRecetas.setRecetaService(recetas);
            recetas.setPublisherServicePrx(publisherServicePrx);
            //Add Broker
            alarma.subscribeToBroker(alarmaServicePrx);
            System.out.println("Server up");

            adapter.activate();
            controladorRecetas.run();
            communicator.waitForShutdown();

        }
    }
}
