import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Game {
    private Program program;
    private boolean processedEndGame = false; 
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


    public Game(GameBoard board, Program program) {
        this.program = program;

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
            if (keyboard.isKeyDown(Keyboard.Key.Left) || keyboard.isKeyDown(Keyboard.Key.Right)) {
                isGameStarted = true;
            }
            return;
        }

        if ((isGameOver || isGameWon) && keyboard.isKeyDown(Keyboard.Key.Space)) {
            resetGame(); // Reset the game state
            return;
        }
        
        // Stop updating the game if it's over or won
        if (isGameOver || isGameWon) {
            if (!processedEndGame) {
                program.getLatestRuns().addScore(score);

                if (program.getHighscores().getModel().size() < 10 || 
                        score > getLowestHighscore()) {

                        // Loop until valid initials are entered
                        while (true) {
                            String initials = JOptionPane.showInputDialog(
                                program, 
                                "Enter initials (2 or 3 letters):", 
                                "New Highscore!", 
                                JOptionPane.PLAIN_MESSAGE
                            );

                            // Check if initials are valid (2 or 3 characters)
                            if (initials != null && (initials.length() == 2 || initials.length() == 3)) {
                                program.getHighscores().addScore(score, initials);
                                break; // Exit the loop if initials are valid
                            } else if (initials != null) {
                                // Show an error message if the input is invalid
                                JOptionPane.showMessageDialog(
                                    program, 
                                    "Initials must be 2 or 3 letters.", 
                                    "Invalid Input", 
                                    JOptionPane.ERROR_MESSAGE
                                );
                            } else {
                                // If the user cancels the dialog, break the loop
                                break;
                            }
                        }
                    }
                processedEndGame = true; // Mark as processed
            }

            // Gradually fade out bricks when game is over
            if (brickAlpha > 0) {
                brickAlpha = Math.max(brickAlpha - 0.01f, 0);
            }
            return; // Exit the update method to stop further game logic
        }

        // Normal game update logic
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
        private void resetGame() {
            isGameOver = false;
            isGameWon = false;
            isGameStarted = false;
            processedEndGame = false;
            brickAlpha = 1.0f;
            score = 0;
            lives = 3;

            // Reset the ball and bat
            Ball.reset(bat);

            // Reinitialize the bricks
            bricks.clear();
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
            
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.setColor(Color.WHITE);
            String restartMessage = "Press SPACE to restart";
            int restartTextWidth = graphics.getFontMetrics().stringWidth(restartMessage);
            int restartCenterX = (board.getWidth() - restartTextWidth) / 2;
            int restartCenterY = centerY + 50; // Position below the game over/win message
            graphics.drawString(restartMessage, restartCenterX, restartCenterY);
        }
    }
    private int getLowestHighscore() {
        DefaultListModel<String> model = program.getHighscores().getModel();
        if (model.isEmpty()) return 0;
        String lastEntry = model.get(model.size()-1);
        return Integer.parseInt(lastEntry.split(" ")[1]);
    }
}