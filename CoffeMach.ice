
module servicios{

    sequence<string> StringArr;
    ["java:type:java.util.ArrayList<String>"] 
    sequence<string> StringSeq;
    ["java:serializable:java.util.Date"]
    sequence<byte> Date;

    ["java:serializable:receta.Receta"]
    sequence<byte> Receta;

    ["java:serializable:ingrediente.Ingrediente"]
    sequence<byte> Ingrediente;

    dictionary<string,int> MapStrInt;


    interface ServicioComLogistica{

      StringSeq asignacionMaquina(int codigoOperador);
	    StringSeq asignacionMaquinasDesabastecidas(int codigoOperador);
	    bool inicioSesion(int codigoOperador, string password);
    }

    interface ServicioAbastecimiento {
	   void abastecer(int codMaquina, int tipoAlarma);
    }

    enum Moneda{
      CIEN, DOCIENTOS, QUINIENTOS
    }
  


    interface AlarmaService{
      void recibirNotificacionEscasezIngredientes(string iDing, int idMaq, string uuidACK);
      void recibirNotificacionInsuficienciaMoneda(Moneda moneda, int idMaq, string uuidACK);
      void recibirNotificacionEscasezSuministro(string idSumin, int idMaq, string uuidACK);
      void recibirNotificacionAbastesimiento(int idMaq, string idInsumo, int cantidad, string uuidACK);
      void recibirNotificacionMalFuncionamiento(int idMaq, string descri, string uuidACK);

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

    //Publisher-Subscriber interfaces
    interface SubscriberService{
      void notifySub();
    }

    interface PublisherService{
      void attach(SubscriberService* subscriberProxy);
      void reportChange();
    }

     interface BrokerService{
            string subscribeServer(string serverId, AlarmaService* server);
            string subscribeClient(string clientId);
            AlarmaService* locateServer(string clientId);
      }

}