package control;

import servicios.ServicioProxyCoffeMachine;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.JFrame;
import org.osoa.sca.annotations.Reference;
import servicios.ServicioAbastecimiento;
import servicios.ServicioCom_MQCafe;
import vista.*;
import modelo.*;
import componentes.*;

public class Controlador implements Runnable, ServicioAbastecimiento {

	@Reference
	private ServicioCom_MQCafe servicioCom_MQCafe;

	public void setServicioCom_MQCafe(ServicioCom_MQCafe servicioCom_MQCafe) {
		this.servicioCom_MQCafe = servicioCom_MQCafe;
	}

	@Reference
	private ServicioProxyCoffeMachine servicioMQProxy;

	public void setServicioMQProxy(ServicioProxyCoffeMachine servicioMQProxy) {
		this.servicioMQProxy = servicioMQProxy;
	}
	
	private Interfaz frame;

	// Componentes
	private DepositoMonedas deposito100, deposito200, deposito500;
	private Ingredientario ing;
	private Recetario rec;
	private Alarmario alar;
	private ManejadorDatos md;
	private Ventario vn;
	// Entidades
	private Ingrediente agua, cafe, azucar, vaso;
	private Receta tinto, tintoCargado;

	// Variables

	private int codMaquina;
	private int monedas100, monedas200, monedas500, suma;

	/**
	 * Launch the application.
	 */
	public void run() {

		// public void main(String[] args) {
		try {
			frame = new Interfaz();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		arrancarMaquina();
		eventos();
	}

	public void inicializarArchivoPlano() {

		// Codigo desde Archivo Plano
		codMaquina = quemarCodMaquina();

		// Ingredientes (Del Plano)
		agua = new Ingrediente("Agua", "8", (double) 500, (double) 50,
				(double) 1000);
		cafe = new Ingrediente("Cafe", "9", (double) 500, (double) 50,
				(double) 1000);
		azucar = new Ingrediente("Azucar", "10", (double) 500, (double) 50,
				(double) 1000);
		vaso = new Ingrediente("Vaso", "11", (double) 50, (double) 5,
				(double) 200);

		// Recetas (Del Plano)
		HashMap<Ingrediente, Double> ingTinto = new HashMap<Ingrediente, Double>();

		ingTinto.put(agua, (double) 100);
		ingTinto.put(cafe, (double) 10);
		ingTinto.put(azucar, (double) 10);
		ingTinto.put(vaso, (double) 1);

		tinto = new Receta("Tinto", "1", 800, ingTinto);

		HashMap<Ingrediente, Double> ingTintoCargado = new HashMap<Ingrediente, Double>();

		ingTintoCargado.put(agua, (double) 100);
		ingTintoCargado.put(cafe, (double) 30);
		ingTintoCargado.put(azucar, (double) 10);
		ingTintoCargado.put(vaso, (double) 1);

		tintoCargado = new Receta("Tinto Cargado", "2", 1000, ingTintoCargado);

		// Listas

		ArrayList<Receta> listaRecetas = new ArrayList<Receta>();
		listaRecetas.add(tinto);
		listaRecetas.add(tintoCargado);

		// Modificar el Manejador de Datos
		md.setListaRecetas(listaRecetas);

		HashMap<Ingrediente, Double> listaIngredientes = new HashMap<Ingrediente, Double>();
		listaIngredientes.put(cafe, (double) 600);
		listaIngredientes.put(agua, (double) 6000);
		listaIngredientes.put(azucar, (double) 600);
		listaIngredientes.put(vaso, (double) 200);

		// Modificar el Manejador de Datos
		md.setListaIngredientes(listaIngredientes);

		// DepositoMonedas (Del Plano)
		deposito100 = new DepositoMonedas("100", 12, 10, 5, 30);
		deposito200 = new DepositoMonedas("200", 12, 10, 5, 30);
		deposito500 = new DepositoMonedas("500", 12, 10, 5, 30);

		// Modificar Manejador
		md.setDeposito100(deposito100);
		md.setDeposito200(deposito200);
		md.setDeposito500(deposito500);
		md.setCodigoMaquina(codMaquina);

		md.setListaVentas(new ArrayList<Venta>());

		// Componentes
		ing = new Ingredientario(listaIngredientes);
		rec = new Recetario(listaRecetas);
		alar = new Alarmario();

		// serializar
		md.guardarDatos();

	}

	private int quemarCodMaquina() {
		int retorno = -2;

		FileInputStream fstream;
		try {
			String path = "codMaquina.cafe";
			File file = new File(path);

			fstream = new FileInputStream(file);

			DataInputStream entrada = new DataInputStream(fstream);

			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					entrada));

			retorno = Integer.parseInt(buffer.readLine());

			entrada.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return retorno;
	}

	public void respaldarMaq() {
		md.setDeposito100(deposito100);
		md.setDeposito200(deposito200);
		md.setDeposito500(deposito500);
		md.setListaIngredientes(ing.getListaIngredientes());
		md.setListaVentas(vn.getListaVentas());

		md.guardarDatos();
	}

	public void arrancarMaquina() {

		codMaquina = quemarCodMaquina();

		// Otras Variables

		monedas100 = 0;
		monedas200 = 0;
		monedas500 = 0;

		md = new ManejadorDatos();
		try {
			md.cargarDatos();

		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			inicializarArchivoPlano();
		}

		deposito100 = md.getDeposito100();
		deposito200 = md.getDeposito200();
		deposito500 = md.getDeposito500();

		ing = new Ingredientario(md.getListaIngredientes());
		rec = new Recetario(md.getListaRecetas());
		alar = new Alarmario();
		vn = new Ventario(md.getListaVentas());

		// Interfaz
		actualizarRecetasCombo();
		actualizarRecetasGraf();
		actualizarInsumosGraf();
		actualizarAlarmasGraf();

	}

	public void eventos() {

		frame.getBtnIngresar100().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.getTextAreaSaldo().setText(
						String.valueOf(Integer.parseInt(frame
								.getTextAreaSaldo().getText()) + 100));
				suma += 100;
				deposito100.setCantidad(deposito100.getCantidad() + 1);
				actualizarInsumosGraf();

			}
		});

		frame.getBtnIngresar200().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getTextAreaSaldo().setText(
						String.valueOf(Integer.parseInt(frame
								.getTextAreaSaldo().getText()) + 200));
				suma += 200;
				deposito200.setCantidad(deposito200.getCantidad() + 1);
				actualizarInsumosGraf();

			}
		});

		frame.getBtnIngresar500().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.getTextAreaSaldo().setText(
						String.valueOf(Integer.parseInt(frame
								.getTextAreaSaldo().getText()) + 500));
				suma += 500;
				deposito500.setCantidad(deposito500.getCantidad() + 1);
				actualizarInsumosGraf();

			}
		});

		frame.getBtnCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getTextAreaSaldo().setText("0");

				if (suma > 0) {

					frame.getTextAreaDevuelta().setText(
							frame.getTextAreaDevuelta().getText()
									+ "Se devolvio: " + suma + "\n");

					devolverMonedas();
					verificarMonedas();

					suma = 0;
					monedas100 = 0;
					monedas200 = 0;
					monedas500 = 0;

				}

			}
		});

		frame.getBtnVerificar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int precio = 0;

				for (int i = 0; i < rec.getListaRecetas().size(); i++) {

					if (frame
							.getComboBoxProducto()
							.getSelectedItem()
							.equals(rec.getListaRecetas().get(i)
									.getDescripcion())) {
						precio = rec.getListaRecetas().get(i).getValor();
					}

				}

				frame.getTextAreaInfo().setText(
						frame.getTextAreaInfo().getText()
								+ "El producto cuesta: " + precio + "\n");
				frame.getTextAreaInfo().repaint();
			}
		});

		frame.getBtnOrdenar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int precio = 0;
				Receta temp = null;

				for (int i = 0; i < rec.getListaRecetas().size(); i++) {

					temp = rec.getListaRecetas().get(i);

					if (frame.getComboBoxProducto().getSelectedItem()
							.equals(temp.getDescripcion())) {
						precio = rec.getListaRecetas().get(i).getValor();

						if (Integer.valueOf(frame.getTextAreaSaldo().getText()) >= precio) {

							frame.getTextAreaInfo().setText(
									frame.getTextAreaInfo().getText()
											+ "Se ordeno: "
											+ frame.getComboBoxProducto()
													.getSelectedItem() + "\n");

							frame.getTextAreaSaldo().setText(
									Integer.valueOf(frame.getTextAreaSaldo()
											.getText()) - precio + "");

							suma -= precio;

							
							disminuirInsumos(temp, frame.getComboBoxProducto()
									.getSelectedItem().toString());

							devolverMonedas();
							verificarMonedas();
							verificarProductos();

							vn.getListaVentas().add(
									new Venta(frame.getComboBoxProducto()
											.getSelectedItem().toString(), rec
											.getListaRecetas().get(i).getId(),
											precio, new Date()));

							respaldarMaq();

							monedas100 = 0;
							monedas200 = 0;
							monedas500 = 0;

							frame.getTextAreaSaldo().setText("0");

						} else {
							frame.getTextAreaInfo().setText(
									frame.getTextAreaInfo().getText()
											+ "Saldo insuficiente \n");

						}

					}

				}

			}

		});

		frame.getBtnMantenimiento().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Enviar Alarma por SCA

				Alarma temp = new Alarma("1", "Se requiere mantenimiento",
						new Date());

				frame.getTextAreaAlarmas().setText(
						frame.getTextAreaAlarmas().getText()
								+ "Se genero una alarma de: Mantenimiento"
								+ "\n");

				servicioCom_MQCafe.alarma_maquina(
						Integer.parseInt(temp.getTipo()), codMaquina,
						new Date());
				
				alar.getListaAlarmas().add(temp);

				frame.interfazDeshabilitada();

			}
		});

		frame.getBtnEnviarReporte().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] arregloVentas = new String[vn.getListaVentas().size()];

				for (int i = 0; i < vn.getListaVentas().size(); i++) {

					arregloVentas[i] = vn.getListaVentas().get(i).getId() + "#"
							+ vn.getListaVentas().get(i).getValor();

				}

				servicioCom_MQCafe.reporte_ventas(codMaquina, new Date(),
						new Date(), arregloVentas);

				vn.getListaVentas().clear();

				respaldarMaq();

			}
		});

		frame.getComboBoxProducto().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

			}
		});

		frame.getBtnActualizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cargarReceta_to_Maquinas();

			}
		});
	}

	public void devolverMonedas() {
		// Metodo para devolver monedas

		if (suma / 500 > 0) {
			monedas500 += (int) suma / 500;
			deposito500.setCantidad(deposito500.getCantidad() - monedas500);
			suma -= 500 * ((int) suma / 500);

		}

		if (suma / 200 > 0) {

			monedas200 += (int) suma / 200;
			deposito200.setCantidad(deposito200.getCantidad() - monedas200);
			suma -= 200 * ((int) suma / 200);

		}
		if (suma / 100 > 0) {
			monedas100 += (int) suma / 100;
			deposito100.setCantidad(deposito100.getCantidad() - monedas100);
			suma -= 100 * ((int) suma / 100);

		}

		frame.getTextAreaDevuelta().setText(
				frame.getTextAreaDevuelta().getText() + "Se devolvieron: "
						+ monedas500 + " monedas de 500, " + monedas200
						+ " monedas de 200 y " + monedas100
						+ " monedas de 100 \n");

		actualizarInsumosGraf();
		verificarMonedas();

	}

	public void actualizarRecetasGraf() {

		frame.getTextAreaRecetas().setText("");

		// Llenar Recetas

		Iterator<Receta> it2 = rec.getListaRecetas().iterator();
		while (it2.hasNext()) {

			Receta temp = it2.next();
			frame.getTextAreaRecetas().setText(
					frame.getTextAreaRecetas().getText()
							+ temp.getDescripcion() + "\n");

		}

	}

	public void actualizarInsumosGraf() {
		
		frame.getTextAreaInsumos().setText("");

		// LLenar Insumos
		Iterator<Entry<Ingrediente, Double>> it = ing.getListaIngredientes()
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Ingrediente, Double> pareja = (Map.Entry<Ingrediente, Double>) it
					.next();

			frame.getTextAreaInsumos().setText(
					frame.getTextAreaInsumos().getText()
							+ pareja.getKey().getNombre() + ": "
							+ pareja.getValue() + "\n");

		}

		frame.getTextAreaInsumos().setText(
				frame.getTextAreaInsumos().getText() + "Deposito "
						+ deposito100.getTipo() + ": "
						+ deposito100.getCantidad() + "\n");
		frame.getTextAreaInsumos().setText(
				frame.getTextAreaInsumos().getText() + "Deposito "
						+ deposito200.getTipo() + ": "
						+ deposito200.getCantidad() + "\n");
		frame.getTextAreaInsumos().setText(
				frame.getTextAreaInsumos().getText() + "Deposito "
						+ deposito500.getTipo() + ": "
						+ deposito500.getCantidad() + "\n");

	}

	public void actualizarAlarmasGraf() {

		frame.getTextAreaAlarmas().setText("");

	}

	public void disminuirInsumos(Receta r, String nombreR) {
		
		

		Iterator<Entry<Ingrediente, Double>> it = r.getListaIngredientes()
				.entrySet().iterator();
		
	

		while (it.hasNext()) {
			Map.Entry<Ingrediente, Double> pareja = (Map.Entry<Ingrediente, Double>) it
					.next();
			
			
			Iterator<Entry<Ingrediente, Double>> itIng = ing
					.getListaIngredientes().entrySet().iterator();

			while (itIng.hasNext()) {
				Map.Entry<Ingrediente, Double> pareja2 = (Map.Entry<Ingrediente, Double>) itIng
						.next();
				

				

				if (pareja.getKey().getNombre().equals(pareja2.getKey().getNombre())) {

					
					
					pareja2.setValue(pareja2.getValue() - pareja.getValue());
					
					
					
					
				}
			}

		}
		// Modificar XML
		actualizarInsumosGraf();

	}

	public void verificarMonedas() {

		// Alarma (Generada por Uso)

		if (deposito100.getCantidad() <= deposito100.getMinimo()
				&& deposito100.getCantidad() > deposito100.getCritico()) {

			Alarma alMon = new Alarma("2", "Faltan monedas de 100", new Date());
			if (!alar.getListaAlarmas().contains(alMon)) {
				alar.getListaAlarmas().add(alMon);

				servicioCom_MQCafe.alarma_maquina(
						Integer.parseInt(alMon.getTipo()), codMaquina,
						new Date());

				frame.getTextAreaAlarmas().setText(
						frame.getTextAreaAlarmas().getText()
								+ "Se genero una alarma de: Monedas de 100"
								+ "\n");

			}
		}

		if (deposito100.getCantidad() <= deposito100.getCritico()) {

			Alarma alMon = new Alarma("3",
					"ESTADO CRITICO: Faltan monedas de 100", new Date());
			alar.getListaAlarmas().add(alMon);

			// Enviar SCA
			servicioCom_MQCafe.alarma_maquina(
					Integer.parseInt(alMon.getTipo()), codMaquina, new Date());

			frame.getTextAreaAlarmas().setText(
					frame.getTextAreaAlarmas().getText()
							+ "Se genero una alarma de: Critica Monedas de 100"
							+ "\n");

			frame.interfazDeshabilitada();

		}

		if (deposito200.getCantidad() <= deposito200.getMinimo()
				&& deposito200.getCantidad() > deposito200.getCritico()) {

			Alarma alMon = new Alarma("4", "Faltan monedas de 200", new Date());

			if (!alar.getListaAlarmas().contains(alMon)) {
				alar.getListaAlarmas().add(alMon);

				// Enviar SCA
				servicioCom_MQCafe.alarma_maquina(
						Integer.parseInt(alMon.getTipo()), codMaquina,
						new Date());

				frame.getTextAreaAlarmas().setText(
						frame.getTextAreaAlarmas().getText()
								+ "Se genero una alarma de: Mondedas de 200"
								+ "\n");

			}
		}

		if (deposito200.getCantidad() <= deposito200.getCritico()) {

			Alarma alMon = new Alarma("5",
					"ESTADO CRITICO: Faltan monedas de 200", new Date());
			alar.getListaAlarmas().add(alMon);

			// Enviar SCA
			servicioCom_MQCafe.alarma_maquina(
					Integer.parseInt(alMon.getTipo()), codMaquina, new Date());

			frame.getTextAreaAlarmas()
					.setText(
							frame.getTextAreaAlarmas().getText()
									+ "Se genero una alarma de: Critica de Monedas de 200"
									+ "\n");

			frame.interfazDeshabilitada();

		}

		if (deposito500.getCantidad() <= deposito500.getMinimo()
				&& deposito500.getCantidad() > deposito500.getCritico()) {

			Alarma alMon = new Alarma("6", "Faltan monedas de 500", new Date());
			if (!alar.getListaAlarmas().contains(alMon)) {
				alar.getListaAlarmas().add(alMon);

				// Enviar SCA
				servicioCom_MQCafe.alarma_maquina(
						Integer.parseInt(alMon.getTipo()), codMaquina,
						new Date());

				frame.getTextAreaAlarmas().setText(
						frame.getTextAreaAlarmas().getText()
								+ "Se genero una alarma de: Monedas de 500"
								+ "\n");

			}
		}
		if (deposito500.getCantidad() <= deposito500.getCritico()) {

			Alarma alMon = new Alarma("7",
					"ESTADO CRITICO: Faltan monedas de 500", new Date());
			alar.getListaAlarmas().add(alMon);

			// Enviar SCA
			servicioCom_MQCafe.alarma_maquina(
					Integer.parseInt(alMon.getTipo()), codMaquina, new Date());

			frame.getTextAreaAlarmas().setText(
					frame.getTextAreaAlarmas().getText()
							+ "Se genero una alarma de: Critica Monedas de 500"
							+ "\n");

			frame.interfazDeshabilitada();

		}

	}

	public void verificarProductos() {

		Iterator<Entry<Ingrediente, Double>> itIng = ing.getListaIngredientes()
				.entrySet().iterator();

		while (itIng.hasNext()) {
			Map.Entry<Ingrediente, Double> pareja2 = (Map.Entry<Ingrediente, Double>) itIng
					.next();

			if (pareja2.getValue() <= pareja2.getKey().getMinimo()
					&& pareja2.getValue() > pareja2.getKey().getCritico()) {

				Alarma alIng = new Alarma(pareja2.getKey().getCodAlarma(),
						pareja2.getKey().getNombre(), new Date());

				if (!alar.getListaAlarmas().contains(alIng)) {

					alar.getListaAlarmas().add(alIng);

					// Enviar SCA
					servicioCom_MQCafe.alarma_maquina(
							Integer.parseInt(alIng.getTipo()), codMaquina,
							new Date());

					frame.getTextAreaAlarmas().setText(
							frame.getTextAreaAlarmas().getText()
									+ "Se genero una alarma de Ingrediente: "
									+ alIng.getMensaje() + "\n");

				}
			}

			if (pareja2.getValue() <= pareja2.getKey().getCritico()) {

				int codAlarma = Integer.parseInt(pareja2.getKey()
						.getCodAlarma()) + 4;

				Alarma alIng = new Alarma(codAlarma + "", pareja2.getKey()
						.getNombre(), new Date());

				alar.getListaAlarmas().add(alIng);

				// Enviar SCA
				servicioCom_MQCafe.alarma_maquina(
						Integer.parseInt(alIng.getTipo()), codMaquina,
						new Date());

				frame.getTextAreaAlarmas().setText(
						frame.getTextAreaAlarmas().getText()
								+ "Se genero una alarma de: Critico de "
								+ alIng.getMensaje() + "\n");

				frame.interfazDeshabilitada();
			}

		}
	}

	public void actualizarRecetasCombo() {

		// Reestablece Combobox
		frame.getComboBoxProducto().removeAllItems();

		// LLenar Combo
		for (int i = 0; i < rec.getListaRecetas().size(); i++) {

			frame.getComboBoxProducto().addItem(
					rec.getListaRecetas().get(i).getDescripcion());
		}
	}

	public void cargarReceta_to_Maquinas() {

		rec.getListaRecetas().clear();

		String[] recetas = servicioMQProxy.notificar();

		for (int i = 0; i < recetas.length; i++) {

			String[] splitInicial = recetas[i].split("#");

			String[] receta = splitInicial[0].split("-");

			HashMap<Ingrediente, Double> listaIngredientes = new HashMap<Ingrediente, Double>();

			for (int i2 = 1; i2 < splitInicial.length; i2++) {

				String[] splitdeIng = splitInicial[i2].split("-");

				Ingrediente ingred = new Ingrediente(splitdeIng[1],
						splitdeIng[2], 500, 50, 1000);
				//
				// for (int i4 = 0; i4 < ing.getListaIngredientes().size();
				// i4++) {
				//
				// if (!ing.getListaIngredientes().containsKey(ingred)) {
				//
				// ing.getListaIngredientes().put(ingred,
				// Double.parseDouble(splitdeIng[4]));
				//
				// }
				//
				// }
				//
				listaIngredientes
						.put(ingred, Double.parseDouble(splitdeIng[4]));

			}

			Receta r = new Receta(receta[1], receta[0],
					Integer.parseInt(receta[2]), listaIngredientes);
		
			rec.getListaRecetas().add(r);

//			for (int i3 = 0; i3 < rec.getListaRecetas().size(); i3++) {
//
//				if (!rec.getListaRecetas().contains(r)) {
//
//					rec.getListaRecetas().add(r);
//
//				}
//
//			}

		}

		// Actualizar Archivo Plano
		md.setListaRecetas(rec.getListaRecetas());
		md.guardarDatos();
		actualizarInsumosGraf();
		actualizarRecetasGraf();
		actualizarRecetasCombo();
	}

	@Override
	public void abastecer(int codMaquina, int idAlarma) {
		// TODO Auto-generated method stub
		
		System.out.println("Entra a abastecer");
		
		System.out.println(codMaquina+"-"+idAlarma);
		
		

		if (codMaquina == this.codMaquina) {
			
			System.out.println("Entra al primer if");

			if (idAlarma == 1) {
				// Habilita Interfaz
			}

			else if (idAlarma == 2 | idAlarma == 3) {
				// Depositos Monedas
				deposito100.setCantidad(20);

				if (idAlarma == 3) {

				}

			} else if (idAlarma == 4 | idAlarma == 5) {
				// Depositos Monedas
				deposito200.setCantidad(20);
			}

			else if (idAlarma == 6 | idAlarma == 7) {
				// Depositos Monedas
				deposito500.setCantidad(20);
			}

			else if (idAlarma == 8 | idAlarma == 12) {
				recargarIngredienteEspecifico("Agua");
			}

			else if (idAlarma == 9 | idAlarma == 13) {
				recargarIngredienteEspecifico("Cafe");
			}

			else if (idAlarma == 10 | idAlarma == 14) {
				recargarIngredienteEspecifico("Azucar");
			}

			else if (idAlarma == 11 | idAlarma == 15) {
				recargarIngredienteEspecifico("Vaso");
			}

			
			quitarAlarma(idAlarma + "");

			if (alar.getListaAlarmas().isEmpty()) {
				frame.setEnabled(true);
				frame.interfazHabilitada();
				
				System.out.println("Entra al if de habilitacion");
			}

			// Respaldo
			respaldarMaq();
			actualizarRecetasGraf();
			actualizarInsumosGraf();
			actualizarAlarmasGraf();

			// ResetAlarmas

			// Envio a Servidor
			servicioCom_MQCafe.desactivarAlarma(codMaquina, idAlarma,
					new Date());
			
			

		}
	}

	public void recargarIngredienteEspecifico(String ingrediente) {

		// Ingredientes
		Iterator<Entry<Ingrediente, Double>> itIng = ing.getListaIngredientes()
				.entrySet().iterator();

		while (itIng.hasNext()) {
			Map.Entry<Ingrediente, Double> pareja2 = (Map.Entry<Ingrediente, Double>) itIng
					.next();

			if (ingrediente.endsWith(pareja2.getKey().getNombre())) {
				pareja2.setValue(pareja2.getKey().getMaximo());
			}

		}
	}

	public void quitarAlarma(String tipo) {

		for (int i = 0; i < alar.getListaAlarmas().size(); i++) {

			if (alar.getListaAlarmas().get(i).getTipo().equals(tipo)) {
				alar.getListaAlarmas().remove(i);
			}

		}

	}
}
