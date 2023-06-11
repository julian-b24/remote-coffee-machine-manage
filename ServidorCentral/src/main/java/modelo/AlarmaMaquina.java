package modelo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmaMaquina {

	private int IdAlarma;
	private int IdMaquina;
	private Date fechaInicialAlarma;
	
}
