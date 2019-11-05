package es.weruleapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;

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
		
		JCheckBox checkBoxFederaciones = new JCheckBox("");
		checkBoxFederaciones.setEnabled(false);
		checkBoxFederaciones.setBounds(143, 60, 97, 23);
		frame.getContentPane().add(checkBoxFederaciones);
		
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
		                ArrayList<Federacion> feds = new ArrayList<Federacion>();
		                ArrayList<JCheckBox> checks = selector.checks;
		                for (int i = 0; i < checks.size(); i++) {
							if(checks.get(i).isSelected()) {
								feds.add(selector.federaciones.get(i));
							}
						}
		                if(feds.size()>0) {
		                	checkBoxFederaciones.setSelected(true);
		                }else {
		                	checkBoxFederaciones.setSelected(false);
		                }
		                System.out.println(feds);
		                e.getWindow().dispose();
		            }
		        });
			}
		});
		btnFederaciones.setBounds(10, 60, 127, 23);
		frame.getContentPane().add(btnFederaciones);
		
	}
}
