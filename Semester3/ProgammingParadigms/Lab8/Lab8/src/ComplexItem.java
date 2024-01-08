import java.util.ArrayList;

public class ComplexItem extends Item{

    ArrayList<Item> children;
    String name;
    Point position;

    public ComplexItem(String name) {
        this.name = name;
        children = new ArrayList<Item>();
    }

    public ArrayList<Item> getChildren() {
        return children;
    }

    public void addChild(Item child) {
        children.add(child);
    }

    public Point getPosition() {
        return children.get(0).getPosition();
    }

    public Point[] getBoundingBox() {
        Point[] boundingBox = new Point[4];

        // go through all the items and find the min and max x and y

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

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

        boundingBox[3] = new Point(minX, minY);
        boundingBox[1] = new Point(maxX, minY);
        boundingBox[2] = new Point(maxX, maxY);
        boundingBox[0] = new Point(minX, maxY);

        return boundingBox;
    }

    public void draw() {
        for (Item child : children) {
            child.draw();
        }
    }

    public void translate(Point p) {
        for (Item child : children) {
            child.translate(p);
        }
    }



}
