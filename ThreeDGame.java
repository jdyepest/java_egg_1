import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class ThreeDGame extends JFrame implements KeyListener {

    private int width = 800;
    private int height = 600;
    private int cubeSize = 100; // Size of the cube (house)
    private int playerX = 315; // Initial X position of the player
    private int playerZ = 370; // Initial Z position of the player
    private int playerSpeed = 3; // Speed of the player
    private int shift = 50; // Depth shift for the cube

    private ThreeDGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("3D Game");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Clear the screen
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        // Draw the house (cube) at (25, 25)
        drawHouse(g2d);

        // Draw the player
        drawPlayer(g2d);
    }

    // Method to draw a 3D cube representing the house
    private void drawHouse(Graphics2D g2d) {
        int x = 250; // X-coordinate of the house
        int y = 250; // Y-coordinate of the house

        // Define the 8 vertices of the cube (house)
        Point[] cubeOnePoints = getCubeOnePoints(x, y, cubeSize);
        Point[] cubeTwoPoints = getCubeTwoPoints(x, y, cubeSize, shift);

        // Draw the front face
        g2d.setColor(Color.WHITE);
        g2d.drawRect(cubeOnePoints[0].x, cubeOnePoints[0].y, cubeSize, cubeSize);

        // Draw the back face
        g2d.drawRect(cubeTwoPoints[0].x, cubeTwoPoints[0].y, cubeSize, cubeSize);

        // Draw the connecting lines between the front and back faces
        for (int i = 0; i < 4; i++) {
            g2d.drawLine(cubeOnePoints[i].x, cubeOnePoints[i].y, cubeTwoPoints[i].x, cubeTwoPoints[i].y);
        }
    }

    private Point[] getCubeOnePoints(int x, int y, int size) {
        Point[] points = new Point[4];
        points[0] = new Point(x, y); // Top-left
        points[1] = new Point(x + size, y); // Top-right
        points[2] = new Point(x + size, y + size); // Bottom-right
        points[3] = new Point(x, y + size); // Bottom-left
        return points;
    }

    private Point[] getCubeTwoPoints(int x, int y, int size, int shift) {
        int newX = x + shift; // Shifted X position
        int newY = y + shift; // Shifted Y position
        Point[] points = new Point[4];
        points[0] = new Point(newX, newY); // Top-left of back face
        points[1] = new Point(newX + size, newY); // Top-right of back face
        points[2] = new Point(newX + size, newY + size); // Bottom-right of back face
        points[3] = new Point(newX, newY + size); // Bottom-left of back face
        return points;
    }

    // Method to draw the player as a small rectangle
    private void drawPlayer(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(playerX - 5, playerZ - 5, 10, 10);
    }

    // Keyboard event listeners for player movement
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_W: // Move forward
                playerZ -= playerSpeed;
                break;
            case KeyEvent.VK_S: // Move backward
                playerZ += playerSpeed;
                break;
            case KeyEvent.VK_A: // Strafe left
                playerX -= playerSpeed;
                break;
            case KeyEvent.VK_D: // Strafe right
                playerX += playerSpeed;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThreeDGame());
    }
}
