package es.weruleapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Principal {

	private JFrame frame;
	private JCheckBox checkBoxFederaciones;
	private JCheckBox checkBoxDirectorio;
	private JButton btnLetsGo;
	private JButton btnNewButton;
	private JCheckBox checkBoxDestino;
	ArrayList<Federacion> feds = new ArrayList<Federacion>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		checkBoxFederaciones = new JCheckBox("");
		checkBoxFederaciones.setEnabled(false);
		checkBoxFederaciones.setBounds(143, 60, 97, 23);
		frame.getContentPane().add(checkBoxFederaciones);
		
		checkBoxDirectorio = new JCheckBox("");
		checkBoxDirectorio.setFont(new Font("Tahoma", Font.PLAIN, 8));
		checkBoxDirectorio.setEnabled(false);
		checkBoxDirectorio.setBounds(143, 96, 285, 23);
		frame.getContentPane().add(checkBoxDirectorio);
		
		btnLetsGo = new JButton("LET'S GO");
		btnLetsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Updater updater = new Updater();
				updater.setFederaciones(feds);
				updater.setDestino(checkBoxDestino.getText());
				updater.setDirectorioBase(checkBoxDirectorio.getText());
				int exito = -1;
				try {
					exito = updater.update();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(exito == 0) {
					JOptionPane.showMessageDialog(frame, "APLICACIÓN ACTUALIZADA CON ÉXITO");
				}else {
					JOptionPane.showMessageDialog(frame, "ERROR");
				}
			}
		});
		btnLetsGo.setEnabled(false);
		btnLetsGo.setBounds(187, 214, 89, 23);
		frame.getContentPane().add(btnLetsGo);
		
		checkBoxDestino = new JCheckBox("");
		checkBoxDestino.setFont(new Font("Tahoma", Font.PLAIN, 8));
		checkBoxDestino.setEnabled(false);
		checkBoxDestino.setBounds(143, 130, 285, 23);
		frame.getContentPane().add(checkBoxDestino);
		
		JButton btnFederaciones = new JButton("Federaciones");
		btnFederaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectorFederaciones selector = new SelectorFederaciones();
				selector.setVisible(true);
				selector.addWindowListener(new WindowAdapter()
		        {
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		                ArrayList<JCheckBox> checks = selector.checks;
		                for (int i = 0; i < checks.size(); i++) {
							if(checks.get(i).isSelected()) {
								feds.add(selector.federaciones.get(i));
							}
						}
		                //System.out.println(feds);
		                if(feds.size()>0) {
		                	checkBoxFederaciones.setSelected(true);
		                }else {
		                	checkBoxFederaciones.setSelected(false);
		                }
		                checkButtons();
		                e.getWindow().dispose();
		            }
		        });
			}
		});
		btnFederaciones.setBounds(10, 60, 127, 23);
		frame.getContentPane().add(btnFederaciones);
		
		JButton btnDirectorioRaz = new JButton("Modelo base");
		btnDirectorioRaz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("choosertitle");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	String directory = chooser.getSelectedFile().getAbsolutePath();
			    	checkBoxDirectorio.setSelected(true);
			    	checkBoxDirectorio.setText(directory);
			    	//System.out.println(directory);
			    }else {
			    	checkBoxDirectorio.setSelected(false);
			    	checkBoxDirectorio.setText("");
			    }
			    checkButtons();
			}
		});
		btnDirectorioRaz.setBounds(10, 96, 127, 23);
		frame.getContentPane().add(btnDirectorioRaz);
		
		btnNewButton = new JButton("Guardar en");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("choosertitle");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			    	String directory = chooser.getSelectedFile().getAbsolutePath();
			    	checkBoxDestino.setSelected(true);
			    	checkBoxDestino.setText(directory);
			    	//System.out.println(directory);
			    }else {
			    	checkBoxDestino.setSelected(false);
			    	checkBoxDestino.setText("");
			    }
			    checkButtons();
			}
		});
		btnNewButton.setBounds(10, 130, 127, 23);
		frame.getContentPane().add(btnNewButton);
		
	}
	
	public void checkButtons() {
		if(checkBoxDirectorio.isSelected() && checkBoxFederaciones.isSelected() && checkBoxDestino.isSelected()) {
	    	btnLetsGo.setEnabled(true);
	    }else {
	    	btnLetsGo.setEnabled(false);
	    }
	}
}
