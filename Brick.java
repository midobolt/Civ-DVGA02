import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Sprite {
    private int durability;
    private int points;
    private Color color; // Brick color

    public Brick(int x, int y, int width, int height, int durability) {
        super(x, y, width, height);
        this.durability = durability;
        this.points = 100 * durability; // More durable bricks give higher points
        this.color = Color.RED; // Default color
    }

    @Override
    public void update(Keyboard keyboard) {
        
    }

    @Override
    public void draw(Graphics2D graphics) {
        if (durability > 0) {
            graphics.setColor(color);
            graphics.fillRect(getX(), getY(), getWidth(), getHeight());

            // Draw brick border
            graphics.setColor(Color.BLACK);
            graphics.drawRect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public void hit() {
        if (durability > 0) {
            durability--;
            points = Math.max(10, points - 10); // Reduce points after hit

            // Darken the color when hit
            if (durability == 2) color = Color.ORANGE;
            else if (durability == 1) color = Color.YELLOW;
        }
    }

    public int getPoints() {
        return points;
    }
    

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDestroyed() {
        return durability <= 0;
    }
}
