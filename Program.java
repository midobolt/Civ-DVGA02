import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Program extends JFrame {
//	private Vanster vanster;
//	private JList<Integer> lista;
	GameBoard board;
	
	public Program() {
		board = new GameBoard();
		setLayout(new BorderLayout());
//		add(vanster);
//		add(lista);
		add(board, BorderLayout.CENTER);
		add(new JLabel("Highscore"), BorderLayout.WEST);
		add(new JLabel("Latest runs"), BorderLayout.EAST);
		setResizable(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		board.start();
	}

	@Override
	protected void processKeyEvent(KeyEvent e) {
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}

	public static void main(String[] args) {
		Program program = new Program();
//		JFrame obj = new JFrame();
//		GamePlay gamePlay = new GamePlay();
//		obj.setBounds(10, 10, 700, 600);
//		obj.setTitle("Breakout Spel");
//		obj.setResizable(false);
//		obj.setVisible(true);
//		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		obj.add(gamePlay);
	}
	

}
