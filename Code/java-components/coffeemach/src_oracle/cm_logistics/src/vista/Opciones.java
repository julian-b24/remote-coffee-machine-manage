package vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Opciones extends JFrame{
	
	private JButton btn_reabastecer;
	private JButton btn_refrescar;
	private JTextArea txt_asignaciones;
	private JTextArea txt_asignacionesRep;
	private JComboBox comboReparar;
	private JPanel panel;
	
	public Opciones(){
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		this.setResizable(false);
		
		JLabel labtit1=new JLabel("Maquinas asignadas");
		JLabel labtit2=new JLabel("Maquinas que requieren atencion");
		JLabel labTit3=new JLabel("Seleccione maquina a reparar");
		
		txt_asignaciones=new JTextArea(10,10);
		txt_asignaciones.setEditable(false);
		
		JScrollPane scroll1=new JScrollPane(txt_asignaciones);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        txt_asignacionesRep=new JTextArea(10,10);
        txt_asignacionesRep.setEditable(false);
        
        JScrollPane scroll2=new JScrollPane(txt_asignacionesRep);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        comboReparar=new JComboBox();
        
        btn_reabastecer=new JButton("Reabastecer");
        btn_refrescar=new JButton("Refrescar");
        
        panel.add(labtit1);
        labtit1.setBounds(120,15,200,30);
        
        panel.add(scroll1);
        scroll1.setBounds(10,60,380,150);
        
        panel.add(labtit2);
        labtit2.setBounds(80, 230, 240, 30);
        
        panel.add(scroll2);
        scroll2.setBounds(10, 275, 380, 150);
        
        panel.add(labTit3);
        labTit3.setBounds(80, 435, 240, 30);
        
        panel.add(comboReparar);
        comboReparar.setBounds(10, 470, 380, 30);
        
        panel.add(btn_reabastecer);
        btn_reabastecer.setBounds(50, 510, 150, 30);
        
        panel.add(btn_refrescar);
        btn_refrescar.setBounds(200, 510, 150, 30);
	}

	public JButton getBtn_reabastecer() {
		return btn_reabastecer;
	}

	public void setBtn_reabastecer(JButton btn_reabastecer) {
		this.btn_reabastecer = btn_reabastecer;
	}

	public JTextArea getTxt_asignaciones() {
		return txt_asignaciones;
	}

	public void setTxt_asignaciones(JTextArea txt_asignaciones) {
		this.txt_asignaciones = txt_asignaciones;
	}

	public JTextArea getTxt_asignacionesRep() {
		return txt_asignacionesRep;
	}

	public void setTxt_asignacionesRep(JTextArea txt_asignacionesRep) {
		this.txt_asignacionesRep = txt_asignacionesRep;
	}

	public JComboBox getComboReparar() {
		return comboReparar;
	}

	public void setComboReparar(JComboBox comboReparar) {
		this.comboReparar = comboReparar;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getBtn_refrescar() {
		return btn_refrescar;
	}

	public void setBtn_refrescar(JButton btn_refrescar) {
		this.btn_refrescar = btn_refrescar;
	}
	
	
	

}
