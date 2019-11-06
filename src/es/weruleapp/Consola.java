package es.weruleapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Consola extends JFrame{

	private JPanel contentPane;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consola frame = new Consola();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Consola() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(1, 1, 833, 259);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(0, 0, 834, 561);
		contentPane.add(scrollPane);
	}

	public void appendText(String string) {
		textPane.setText(textPane.getText()+string+'\n');
	}

}
