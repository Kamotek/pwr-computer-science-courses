import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class ComplexItem extends Item{

    ArrayList<Item> children;
    String name;
    Point position;
    Point[] boundingBox = new Point[4];
    int maxX;
    int maxY;
    int minX;
    int minY;


    public ComplexItem(String name) {
        this.name = name;
        children = new ArrayList<Item>();

        calculateBoundingBox();
    }

    public ArrayList<Item> getChildren() {
        return children;
    }

    public void addChild(Item child) {
        children.add(child);
        child.partOfComplex = true;
        calculateBoundingBox();
    }

    public Point getPosition() {
        return children.get(0).getPosition();
    }

    public Point[] getBoundingBox() {
        return boundingBox;
    }

    public void draw() {
        for (Item child : children) {
            child.draw();
        }
    }

    public void draw(Graphics g) {
        for (Item child : children) {
            child.draw(g);
        }

        Graphics2D g2d = (Graphics2D) g;
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
        g2d.setStroke(dashed);
        g2d.drawRect(boundingBox[0].getCoordX(), boundingBox[0].getCoordY(), maxX - minX, maxY - minY);
        System.out.println("BoundingBox0: " + boundingBox[0].coordX + " " + boundingBox[0].coordY);
        System.out.println("BoundingBox1: " + boundingBox[1].coordX + " " + boundingBox[1].coordY);
        System.out.println("BoundingBox2: " + boundingBox[2].coordX + " " + boundingBox[2].coordY);
        System.out.println("BoundingBox3: " + boundingBox[3].coordX + " " + boundingBox[3].coordY);
    }

    public void translate(Point p) {
        for (Item child : children) {
            child.translate(p);
        }
        calculateBoundingBox();
    }

    private void calculateBoundingBox(){
    
                // go through all the items and find the min and max x and y

        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;

        for (Item child : children) {
            Point[] childBoundingBox = child.getBoundingBox();
            for (Point p : childBoundingBox) {
                if (p.coordX < minX) {
                    minX = p.coordX;
                }
                if (p.coordY < minY) {
                    minY = p.coordY;
                }
                if (p.coordX > maxX) {
                    maxX = p.coordX;
                }
                if (p.coordY > maxY) {
                    maxY = p.coordY;
                }
            }
        }

        boundingBox[0] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[3] = new Point(minX, maxY);


    }
}
