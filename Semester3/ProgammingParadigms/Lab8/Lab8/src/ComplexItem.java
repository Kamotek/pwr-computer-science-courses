import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public class ComplexItem extends Item{

    private ArrayList<Item> children;


    public ComplexItem() {
        children = new ArrayList<Item>();

        calculateBoundingBox();
        position = boundingBox[0];
    }

    public ArrayList<Item> getChildren() {
        return children;
    }

    public void addChild(Item child) {
        children.add(child);
        child.partOfComplex = true;
        calculateBoundingBox();
        position = boundingBox[0];
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
        g2d.drawRect(position.getCoordX(), position.getCoordY(), maxX - minX, maxY - minY);
    }

    public void translate(Point p) {
        for (Item child : children) {
            child.translate(p);
        }
        calculateBoundingBox();
        position = boundingBox[0];
    }

    private void calculateBoundingBox() {
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        maxY = Integer.MIN_VALUE;

        for (Item child : children) {
            Point[] childBoundingBox = child.getBoundingBox();
            for (Point p : childBoundingBox) {
                if (p.getCoordX() < minX) {
                    minX = p.getCoordX();
                }
                if (p.getCoordY() < minY) {
                    minY = p.getCoordY();
                }
                if (p.getCoordX() > maxX) {
                    maxX = p.getCoordX();
                }
                if (p.getCoordY() > maxY) {
                    maxY = p.getCoordY();
                }
            }
        }

        boundingBox[0] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[3] = new Point(minX, maxY);


    }
}
