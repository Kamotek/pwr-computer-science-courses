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

        for (Item item : items) {
            item.draw(g);
        }
    }

    public void draw() {
        JFrame frame = new JFrame("Paint-Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this); 

        frame.setSize(500, 500);
        frame.setVisible(true);
        repaint();
    }

    public static void main(String[] args) {
        Scene scene = new Scene();
        scene.draw(); 
    }
}
