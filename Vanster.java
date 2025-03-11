import java.awt.*;
import javax.swing.*;

public class Vanster extends JPanel {
    private JLabel label;
    private JList<Integer> lista;
    private DefaultListModel<Integer> dlm;

    public Vanster() {
        label = new JLabel("Highscores");
        dlm = new DefaultListModel<>();
        lista = new JList<>(dlm);

        setLayout(new GridBagLayout());
        GridBagConstraints GBC = new GridBagConstraints();
        
        GBC.gridx = 0;
        GBC.gridy = 0;
        add(label, GBC);

        GBC.gridx = 0;
        GBC.gridy = 1;
        add(new JScrollPane(lista), GBC); // Add scrolling for better usability

        lista.setFocusable(false);
    }

    public void addHighscore(int score) {
       /* SwingUtilities.invokeLater(() -> {
            if (dlm.contains(score)) return; // Optional: Remove to allow duplicates
            if (dlm.getSize() >= 3) {
                dlm.remove(0);
            }
            dlm.addElement(score);
        });*/
    	dlm.addElement(22);
    }
}
