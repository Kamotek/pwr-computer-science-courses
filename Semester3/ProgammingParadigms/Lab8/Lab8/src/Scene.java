import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Scene extends JPanel {

    private ArrayList<Item> items;

    public Scene() {
        items = new ArrayList<>();
    }

    public void addChildren(Item item) {
        this.items.add(item);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw items in the ArrayList using Graphics object 'g'
        for (Item item : items) {
            item.draw(g);
        }
    }

    public void draw() {
        JFrame frame = new JFrame("Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this); // Add the Scene JPanel to the JFrame

        // Create items and add them to the scene


        // Set the frame size and make it visible
        frame.setSize(500, 500);
        frame.setVisible(true);

        // Trigger the repaint of the JPanel
        repaint();
    }

    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.draw(); // Start the drawing process
    }
}
