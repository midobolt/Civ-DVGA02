import javax.swing.DefaultListModel;

public class LatestRunsList {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private static final int MAX_ENTRIES = 3;

    public DefaultListModel<String> getModel() { return model; }

    public void addScore(int score) {
        model.add(0, String.valueOf(score));
        if (model.size() > MAX_ENTRIES) model.removeElementAt(MAX_ENTRIES);
    }
}