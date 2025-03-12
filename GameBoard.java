import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameBoard extends JComponent {
    private final int FPS = 40; 
    private Game game;
    private Keyboard keyboard;

    public GameBoard(Program program) {
        keyboard = new Keyboard();
        game = new Game(this, program);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);
        Graphics2D graphics = (Graphics2D)arg0;
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        game.draw(graphics);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        if (e.getID() == KeyEvent.KEY_PRESSED)
            keyboard.processKeyEvent(e.getKeyCode(), true);
        else if (e.getID() == KeyEvent.KEY_RELEASED)
            keyboard.processKeyEvent(e.getKeyCode(), false);
    }

    public void start() {
        Timer timer = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update(keyboard);
                repaint();
            }
        });
        timer.start();
    }
}