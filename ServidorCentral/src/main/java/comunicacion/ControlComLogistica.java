package comunicacion;

import ServerControl.*;
import lombok.Getter;
import servicios.ServiceLogisticaPrx;
import servicios.ServicioComLogistica;
import com.zeroc.Ice.*;
import servicios.alarma.AlarmaLogistica;

import java.util.*;

@Getter
public class ControlComLogistica implements ServicioComLogistica{
 
	private ServerControl control;
	private ServiceLogisticaPrx serviceLogisticaPrx;

	public ControlComLogistica(ServerControl con) {
		control = con;
	}

    @Override
	public List<String> asignacionMaquina(int codigoOperador, Current current) {

		return control.listaAsignaciones(codigoOperador);
	}

	// Funciona correctamente
	@Override
	public List<String> asignacionMaquinasDesabastecidas(int codigoOperador, Current current) {

		return control.listaAsignacionesMDanada(codigoOperador);
	}

	@Override
	public boolean inicioSesion(int codigoOperador, String password, Current current) {
		return control.existeOperador(codigoOperador, password);
	}

	@Override
	public void asignarOperador(int idMaquina, int idOperador, Current current) {
		control.asignarOperador(idMaquina, idOperador);
	}

	@Override
	public List<AlarmaLogistica> obtenerAlarmas(Current current) {
		return control.obtenerAlarmas();
	}

	@Override
	public void attachServer(ServiceLogisticaPrx proxy, Current current){
		this.serviceLogisticaPrx = proxy;
		System.out.println("Bodega conectada");
	}


}
