import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Program extends JFrame {
    private HighscoreList highscores = new HighscoreList();
    private LatestRunsList latestRuns = new LatestRunsList();
    
    GameBoard board;
    
    public Program() {
        board = new GameBoard(this);
        setLayout(new BorderLayout());
        add(board, BorderLayout.CENTER);

        JList<String> highscoreList = new JList<>(highscores.getModel());
        highscoreList.setFocusable(false); // Prevent JList from stealing focus
        JScrollPane highscoreScrollPane = new JScrollPane(highscoreList);
        highscoreScrollPane.setPreferredSize(new Dimension(150, 0)); // fixed width
        add(highscoreScrollPane, BorderLayout.WEST);

        JList<String> latestRunsList = new JList<>(latestRuns.getModel());
        latestRunsList.setFocusable(false); // Prevent JList from stealing focus
        JScrollPane latestRunsScrollPane = new JScrollPane(latestRunsList);
        latestRunsScrollPane.setPreferredSize(new Dimension(200, 0)); // fixed width
        add(latestRunsScrollPane, BorderLayout.EAST);
        
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
        new Program();
    }
    
    public HighscoreList getHighscores() {
        return highscores;
    }

    public LatestRunsList getLatestRuns() {
        return latestRuns;
    }
}