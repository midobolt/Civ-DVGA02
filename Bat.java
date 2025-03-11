
import java.awt.Color;
import java.awt.Graphics2D;

public class Bat extends Sprite {
    private int speed;

    public Bat(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = 5;
    }

    @Override
    public void update(Keyboard keyboard) {
        if (keyboard.isKeyDown(Key.Left) && getX() > 0) {
            setX(getX() - speed);
        }
        if (keyboard.isKeyDown(Key.Right) && getX() < 800 - getWidth()) {
            setX(getX() + speed);
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
