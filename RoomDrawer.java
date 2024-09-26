import java.awt.*;
import javax.swing.*;

public class RoomDrawer extends JPanel {

    private int length = 100;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Call the drawRoom method
        drawRoom(g2d);
    }

    private void drawRoom(Graphics2D g2d) {
        // Set line color to white
        g2d.setColor(Color.WHITE);
    
        // Draw floor by connecting four points
        g2d.drawLine(getX(0, 0, 0), getY(0, 0, 0), getX(length, 0, 0), getY(length, 0, 0));
        g2d.drawLine(getX(length, 0, 0), getY(length, 0, 0), getX(length, 0, -length), getY(length, 0, -length));
        g2d.drawLine(getX(length, 0, -length), getY(length, 0, -length), getX(0, 0, -length), getY(0, 0, -length));
        g2d.drawLine(getX(0, 0, -length), getY(0, 0, -length), getX(0, 0, 0), getY(0, 0, 0));
    
        // Draw ceiling by connecting corresponding points
        g2d.drawLine(getX(0, length, 0), getY(0, length, 0), getX(length, length, 0), getY(length, length, 0));
        g2d.drawLine(getX(length, length, 0), getY(length, length, 0), getX(length, length, -length), getY(length, length, -length));
        g2d.drawLine(getX(length, length, -length), getY(length, length, -length), getX(0, length, -length), getY(0, length, -length));
        g2d.drawLine(getX(0, length, -length), getY(0, length, -length), getX(0, length, 0), getY(0, length, 0));
    
        // Draw walls by connecting floor and ceiling points
        for (int i = 0; i < 4; i++) {
            int x1 = getX(0, 0, i);
            int y1 = getY(0, 0, i);
            int x2 = getX(0, length, i);
            int y2 = getY(0, length, i);
            g2d.drawLine(x1, y1, x2, y2);
    
            int x3 = getX(length, 0, i);
            int y3 = getY(length, 0, i);
            int x4 = getX(length, length, i);
            int y4 = getY(length, length, i);
            g2d.drawLine(x3, y3, x4, y4);
        }
    }

    // Dummy implementations of getX and getY for testing
    private int getX(int x, int y, int z) {
        return x + y + z; // Modify this to suit your actual logic
    }

    private int getY(int x, int y, int z) {
        return x + y + z; // Modify this to suit your actual logic
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("3D Room Drawing");
        RoomDrawer panel = new RoomDrawer();
        panel.setBackground(Color.BLACK); // Set background color to black for contrast

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
