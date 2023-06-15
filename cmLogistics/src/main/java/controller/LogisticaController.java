package controller;

import com.zeroc.Ice.Current;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import servicios.ServiceLogistica;
import servicios.ServiceLogisticaPrx;
import servicios.ServicioComLogisticaPrx;
import servicios.alarma.AlarmaLogistica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Setter
public class LogisticaController extends Thread implements ServiceLogistica {

    private ServicioComLogisticaPrx servicioComLogisticaPrx;
    private final static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void notificarNuevaAlarma(Current current) {
        System.out.println("Llegó una nueva alarma, revise las alarmas en la opción 1");
    }


    @SneakyThrows
    public void run(){
        int option = 0;
        do{
            System.out.println(getMenu());
            option = Integer.parseInt(READER.readLine());
            executeOption(option);
        }while (option != 0);
        READER.close();
    }

    private void executeOption(int option) {
        switch (option){
            case 1:
                verAlarmas();
                break;
            case 2:
                desactivarAlarma();
                break;
            case 0:
                System.out.println("Logistica abajo");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    private String getMenu(){
        return "Seleccione una opción: " + "\n"+
                "1. Ver Alarmas" + "\n"+
                "2. Desactivar Alarma" + "\n"+
                "0. Salir"+ "\n";
    }

    private void verAlarmas(){
        List<AlarmaLogistica> alarmas = servicioComLogisticaPrx.obtenerAlarmas();
        System.out.println("ID_ALARMA"+"\t"+"ID_MAQUINA"+"\t"+"FECHA_INICIAL"+"\t"+"FECHA_FINAL"+"\t"+"CONSECUTIVO"+"\t");
        for (AlarmaLogistica alarma : alarmas) {
            System.out.println(alarma.idAlarma+"\t"+alarma.idMaquina+"\t"+alarma.fechaInicial+"\t"+alarma.fechaFinal+"\t"+alarma.consecutivo+"\t");
        }

    }

    public void attachServer(ServiceLogisticaPrx proxy) {
        servicioComLogisticaPrx.attachServer(proxy);
    }

    public void desactivarAlarma() {

        int idMaquina, idAlarma;
        try {
            System.out.println("Ingrese el id de la máquina");
            idMaquina = Integer.parseInt(READER.readLine());
            System.out.println("Ingrese el id de la alarma");
            idAlarma = Integer.parseInt(READER.readLine());
            servicioComLogisticaPrx.desactivarAlarma(idMaquina, idAlarma);
        } catch (IOException e) {
            System.out.println("Id inválido");
        }
    }
}
