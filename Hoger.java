

import java.awt.*;
import javax.swing.*;


public class Hoger extends JPanel{
	private JLabel label;
	private JList<Integer> lista;
	private DefaultListModel<Integer> dlm;
	
	public Hoger(){
		label = new JLabel("Latest runs:");
		dlm = new DefaultListModel<Integer>();
		lista = new JList<Integer>(dlm);
		setLayout(new GridBagLayout());
		
		GridBagConstraints GBC = new GridBagConstraints();	
		GBC.weighty = 0.9;
		GBC.gridx = 0;
		GBC.gridy = 0;
		add(label, GBC);
		GBC.gridx = 0;
		GBC.gridy = 1;
		add(lista, GBC);
		dlm.addElement(123);
		lista.setFocusable(false);
		
	}
}
