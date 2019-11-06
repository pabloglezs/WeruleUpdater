package es.weruleapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.JCheckBox;
import java.awt.Color;

public class SelectorFederaciones extends JFrame {

	private JPanel contentPane;
	public ArrayList<Federacion> federaciones = new ArrayList<Federacion>();
	public ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();

	/**
	 * Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorFederaciones frame = new SelectorFederaciones();
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
	public SelectorFederaciones() {
		setTitle("Seleccione las federaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			readJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = federaciones.size()*100;
		setBounds(100, 100, 450, height);
		createCheckBoxes();

	}

	public void readJSON() throws IOException {
		File file = new File("resources/federaciones.json");
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st;
		String json = "";
		while ((st = br.readLine()) != null) {
			json+=st+'\n';
		}

		JSONObject obj = new JSONObject(json);
		JSONArray array = obj.getJSONArray("federaciones");
		for(int i = 0 ; i < array.length() ; i++){
			Federacion federacion = new Federacion(
					array.getJSONObject(i).getString("nombre"),
					array.getJSONObject(i).getString("logoconletras"),
					array.getJSONObject(i).getString("logosinletras"),
					array.getJSONObject(i).getString("atras"),
					array.getJSONObject(i).getString("color")
					);
			federaciones.add(federacion);
		}
	}

	public void createCheckBoxes() {
		for (int i = 0; i < federaciones.size(); i++) {
			JCheckBox chckbxNewCheckBox = new JCheckBox(federaciones.get(i).getNombre());
			chckbxNewCheckBox.setBounds(52, 60*i, 200, 100);
			contentPane.add(chckbxNewCheckBox);
			chckbxNewCheckBox.setSelected(true);
			checks.add(chckbxNewCheckBox);
		}
	}



}
