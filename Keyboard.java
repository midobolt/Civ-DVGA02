import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.Map;

public class Keyboard {
    // Define an enum for keys
    public enum Key {
        Up, Down, Left, Right, Escape, Enter, Space
    }

    // Use EnumMap for better performance and type safety
    private Map<Key, Boolean> state;

    public Keyboard() {
        state = new EnumMap<>(Key.class);
        // Initialize all keys to false (not pressed)
        for (Key key : Key.values()) {
            state.put(key, false);
        }
    }

    // Check if a key is pressed
    public boolean isKeyDown(Key key) {
        return state.getOrDefault(key, false);
    }

    // Check if a key is not pressed
    public boolean isKeyUp(Key key) {
        return !isKeyDown(key);
    }

    // Update the state of a key based on the key event
    public void processKeyEvent(int keyCode, boolean isPressed) {
        Key key = getKeyFromKeyCode(keyCode);
        if (key != null) {
            state.put(key, isPressed);
        }
    }

    // Map KeyEvent key codes to our Key enum
    private Key getKeyFromKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:     return Key.Up;
            case KeyEvent.VK_DOWN:   return Key.Down;
            case KeyEvent.VK_LEFT:   return Key.Left;
            case KeyEvent.VK_RIGHT:  return Key.Right;
            case KeyEvent.VK_ESCAPE: return Key.Escape;
            case KeyEvent.VK_ENTER:  return Key.Enter;
            case KeyEvent.VK_SPACE:  return Key.Space;
            default:                 return null; // Ignore unsupported keys
        }
    }
}