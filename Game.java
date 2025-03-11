
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;

public class Game {
    private GameBoard board;
    private Ball Ball;
    private Bat bat;
    private ArrayList<Brick> bricks;
    private int score;
    private int lives;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isGameStarted;
    private float brickAlpha = 1.0f; // Opacity starts at 100%


    public Game(GameBoard board) {
        this.board = board;
        this.Ball = new Ball(400, 300, 20, 20);
        this.bat = new Bat(350, 550, 100, 20);
        this.bricks = new ArrayList<>();
        this.score = 0;
        this.lives = 3;
        this.isGameOver = false;
        this.isGameWon = false;
        this.isGameStarted = false; // Pause


        Color[] brickColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};

        for (int row = 0; row < 6; row++) {  
            for (int col = 0; col < 7; col++) {  
                int xOffset = (row % 2 == 0) ? 50 : 0;  
                int x = xOffset + col * 110;  
                int y = 50 + row * 30;  
                
                int strength = 1 + (row / 2);  
                Brick brick = new Brick(x, y, 100, 20, strength);
                brick.setColor(brickColors[row % brickColors.length]); 
                bricks.add(brick);
            }
        }
    }

    public void update(Keyboard keyboard) {
        if (!isGameStarted) {
            if (keyboard.isKeyDown(Key.Left) || keyboard.isKeyDown(Key.Right)) {
                isGameStarted = true;
            }
            return;
        }

        if (isGameOver || isGameWon) {
            // Gradually fade out bricks when game is over
            if (brickAlpha > 0) {
                brickAlpha = Math.max(brickAlpha - 0.01f, 0);
            }
            return;
        }

        Ball.update(keyboard);
        bat.update(keyboard);

        if (Ball.collidesWith(bat)) {
            Ball.bounceOffBat(bat);
        }

        ArrayList<Brick> toRemove = new ArrayList<>();
        for (Brick brick : bricks) {
            if (Ball.collidesWith(brick)) {
                Ball.bounceOffBrick();
                score += brick.getPoints();
                toRemove.add(brick);
            }
        }
        bricks.removeAll(toRemove);

        if (Ball.getY() > board.getHeight()) {
            lives--;
            if (lives <= 0) {
                isGameOver = true;
            } else {
                Ball.reset(bat);
            }
        }

        if (bricks.isEmpty()) {
            isGameWon = true;
        }
    }

    public void draw(Graphics2D graphics) {
        Ball.draw(graphics);
        bat.draw(graphics);

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, brickAlpha));

        
        for (Brick brick : bricks) {
            brick.draw(graphics);
        }
        
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Reset transparency


        // score and lives
        graphics.setColor(Color.WHITE);
        graphics.drawString("Score: " + score, 10, 10);
        graphics.drawString("Lives: " + lives, 10, 30);

        if (!isGameStarted) {
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            graphics.setColor(Color.WHITE);
            String message = "Press < or > arrow to start!";
            int textWidth = graphics.getFontMetrics().stringWidth(message);
            int centerX = (board.getWidth() - textWidth) / 2;
            int centerY = board.getHeight() / 2;
            graphics.drawString(message, centerX, centerY);
            return; 
        }
        
        if (isGameOver) {
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            graphics.setColor(Color.RED);
            String message = "GAME OVER";
            int textWidth = graphics.getFontMetrics().stringWidth(message);
            int centerX = (board.getWidth() - textWidth) / 2;
            int centerY = board.getHeight() / 2;
            graphics.drawString(message, centerX, centerY);
        }
        
        if (isGameOver || isGameWon) {
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            if (isGameOver) {
                graphics.setColor(Color.RED);

            } else if(isGameWon) {
                graphics.setColor(Color.GREEN);

            }
            String message = isGameOver ? "GAME OVER" : "YOU WIN!";
            int textWidth = graphics.getFontMetrics().stringWidth(message);
            int centerX = (board.getWidth() - textWidth) / 2;
            int centerY = board.getHeight() / 2;
            graphics.drawString(message, centerX, centerY);
        }
    }
}
