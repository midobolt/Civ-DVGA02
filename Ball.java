
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics2D;

public class Ball extends Sprite {
    private int velocityX, velocityY;
    private int speed;
    private Random random = new Random();


    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.velocityX = 4;
        this.velocityY = 4;
        this.speed = 5;
    }

    @Override
    public void update(Keyboard keyboard) {
        setX(getX() + velocityX);
        setY(getY() + velocityY);

        if (getX() <= 0 || getX() >= 800 - getWidth()) {
            velocityX = -velocityX;
        }

        if (getY() <= 0) {
            velocityY = -velocityY;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(getX(), getY(), getWidth(), getHeight());
    }

//    public void bounceOffBat(Bat bat) {
//        velocityY = -velocityY; 
//    }
    public void bounceOffBat(Bat bat) {
        int batCenter = bat.getX() + bat.getWidth() / 2;
        int ballCenter = getX() + getWidth() / 2;
        
        // Calculate offset (-1 to 1) based on hit position
        double offset = (double) (ballCenter - batCenter) / (bat.getWidth() / 2);
        
        velocityX = (int) (offset * speed); // Control horizontal bounce
        velocityY = -velocityY; // Always bounce upwards
    }



    public void bounceOffBrick() {
    	velocityY = -velocityY;
        
        // random change to X-direction
        velocityX += random.nextInt(3) - 1; 
//        velocityY = -velocityY;
            
            // Increase speed slightly
            if (Math.abs(velocityX) < 10) velocityX *= 1.05;
            if (Math.abs(velocityY) < 10) velocityY *= 1.05;
    }

    public boolean collidesWith(Sprite sprite) {
        return getX() < sprite.getX() + sprite.getWidth() &&
               getX() + getWidth() > sprite.getX() &&
               getY() < sprite.getY() + sprite.getHeight() &&
               getY() + getHeight() > sprite.getY();
    }

    public void reset(Bat bat) {
        setX(400);
        setY(300);
        
        setX(bat.getX() + bat.getWidth() / 2 - getWidth() / 2);
        setY(bat.getY() - getHeight() - 5);
            
        velocityX = (random.nextBoolean() ? speed : -speed);
        velocityY = -speed;

    }
}
