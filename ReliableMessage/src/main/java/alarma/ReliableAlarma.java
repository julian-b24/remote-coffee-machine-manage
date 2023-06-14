package alarma;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ReliableAlarma implements java.io.Serializable{

    public static final int ALARMA_INGREDIENTE = 1;
    public static final int ALARMA_MONEDA_CIEN = 2;
    public static final int ALARMA_MONEDA_DOS = 3;
    public static final int ALARMA_MONEDA_QUI = 4;
    public static final int ALARMA_SUMINISTRO = 5;
    public static final int ALARMA_MAL_FUNCIONAMIENTO = 6;
    public static final int ALARMA_NOTIFICACION_ABASTECIMIENTO = 7;

    private int tipo;
    private int idMaquina;
    //private Date fecha;
    private HashMap<String, String> extraInfo;

    public ReliableAlarma(int tipo, int idMaquina, HashMap<String, String> extraInfo){
        this.tipo = tipo;
        this.idMaquina = idMaquina;
        this.extraInfo = extraInfo;
    }

}
