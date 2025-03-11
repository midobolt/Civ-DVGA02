//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.util.Timer;
//
//import javax.swing.JPanel;
//
//public class GamePlay extends JPanel implements KeyListener, ActionListener{
//	private boolean play = false;
//	private int score = 0;
//	
//	private int totalBricks = 21;
//	
//	private Timer time;
//	private int delay = 8;
//	
//	private int playerX = 310;
//	
//	private int ballposX = 120;
//	private int ballposY = 350;
//	private int ballXdir = -1;
//	private int ballYdir = -2;
//	
//	
//	public GamePlay() {
//		addKeyListener(this);
//		setFocusable(true);
//		setFocusTraversalKeysEnabled(false);
//		timer = new Timer(delay, this);
//		timer.start();
//	}
//	
//	public void paint(Graphics g) {
//		// background
//		g.setColor(Color.black);
//		g.fillRect(1, 1, 692, 592);
//		
//		// borders
//		g.setColor(Color.yellow);
//		g.fillRect(0, 0, 3, 592);
//		g.fillRect(0, 0, 692, 3);
//		g.fillRect(691, 0, 3, 592);
//		
//		// the paddle
//		g.setColor(Color.green);
//		g.fillRect(playerX, 550, 100, 8);
//		
//		// the ball
//		g.setColor(Color.yellow);
//		g.fillRect(ballposX, ballposY, 20, 20);
//	}
//	
//	
//	
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
