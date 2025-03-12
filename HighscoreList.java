import javax.swing.DefaultListModel;
import javax.swing.table.*;

public class HighscoreList {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private static final int MAX_ENTRIES = 10;

    public DefaultListModel<String> getModel() { return model; }

    public void addScore(int score, String initials) {
        String entry = String.format("%3s %d", initials.toUpperCase(), score);
        
        int insertPos = 0;
        for (; insertPos < model.size(); insertPos++) {
            int existingScore = getScoreFromEntry(model.get(insertPos));
            if (score > existingScore) break;
        }
        
        if (insertPos < MAX_ENTRIES) {
            model.add(insertPos, entry);
            if (model.size() > MAX_ENTRIES) model.removeElementAt(MAX_ENTRIES);
        }
    }

    private int getScoreFromEntry(String entry) {
        return Integer.parseInt(entry.split(" ")[1]);
    }
}