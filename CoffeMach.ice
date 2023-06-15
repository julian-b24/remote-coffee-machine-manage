
module servicios{

    sequence<string> StringArr;
    ["java:type:java.util.ArrayList<String>"] 
    sequence<string> StringSeq;
    ["java:serializable:java.util.Date"]
    sequence<byte> Date;

    module alarma{
      class AlarmaLogistica{
        int idAlarma;
        int idMaquina;
        Date fechaInicial;
        Date fechaFinal;
        int consecutivo;
      }
    }

    ["java:serializable:servicios.alarma.AlarmaLogistica"]
    sequence<byte> AlarmaL;

    ["java:type:java.util.ArrayList<servicios.alarma.AlarmaLogistica>"]
    sequence<AlarmaL> AlarmasMaps;

    dictionary<string,int> MapStrInt;


    interface ServiceLogistica{
      void notificarNuevaAlarma();
    }

    interface ServicioComLogistica{

          StringSeq asignacionMaquina(int codigoOperador);
    	    StringSeq asignacionMaquinasDesabastecidas(int codigoOperador);
    	    bool inicioSesion(int codigoOperador, string password);
    	    void asignarOperador(int idMaquina, int idOperador);
    	    AlarmasMaps obtenerAlarmas();
    	    void attachServer(ServiceLogistica* proxy);
        }

    interface ServicioAbastecimiento {
	   void abastecer(int codMaquina, int tipoAlarma);
    }

    enum Moneda{
      CIEN, DOCIENTOS, QUINIENTOS
    }

    interface VentaService{
      void registrarVenta(int codMaq, StringArr ventas);
    }

    interface RecetaService{
      StringArr consultarIngredientes();
	    StringArr consultarRecetas();
      StringArr consultarProductos();
      void definirProducto(string nombre, int precio, MapStrInt ingredientes);

	    void borrarReceta(int cod);
	    void definirRecetaIngrediente(int idReceta, int idIngrediente,int valor);
	    string registrarReceta(string nombre, int precio);
	    string registrarIngrediente(string nombre);
    }


    //Reliable Message interface
    interface ReliableMessageAlarmaService{
      void sendACK(string uuidAlarma);
      void recibirNotificacionEscasezIngredientes(string iDing, int idMaq);
      void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq);
      void recibirNotificacionEscasezSuministro(string idSumin, int idMaq);
      void recibirNotificacionAbastesimiento(int idMaq, string idInsumo, int cantidad );
      void recibirNotificacionMalFuncionamiento(int idMaq, string descri);
    }

    interface AlarmaService{
      void recibirNotificacionEscasezIngredientes(string iDing, int idMaq, string uuidACK, ReliableMessageAlarmaService* proxy);
      void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, string uuidACK, ReliableMessageAlarmaService* proxy);
      void recibirNotificacionEscasezSuministro(string idSumin, int idMaq, string uuidACK, ReliableMessageAlarmaService* proxy);
      void recibirNotificacionAbastesimiento(int idMaq, string idInsumo, int cantidad, string uuidACK, ReliableMessageAlarmaService* proxy);
      void recibirNotificacionMalFuncionamiento(int idMaq, string descri, string uuidACK, ReliableMessageAlarmaService* proxy);
    }

    //Publisher-Subscriber interfaces
    interface SubscriberService{
      void notifySub();
    }

    interface PublisherService{
      void attach(SubscriberService* subscriberProxy);
      void reportChange();
    }

     interface BrokerService{
            string subscribeServer(AlarmaService* server);
            string subscribeClient(string clientId);
            AlarmaService* locateServer();
      }

}