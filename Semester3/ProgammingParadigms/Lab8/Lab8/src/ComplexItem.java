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
        boundingBox[0] = new Point(children.get(0).getPosition().coordX, children.get(0).getPosition().coordY);
        boundingBox[1] = new Point(children.get(0).getPosition().coordX + 1, children.get(0).getPosition().coordY);
        boundingBox[2] = new Point(children.get(0).getPosition().coordX, children.get(0).getPosition().coordY + 1);
        boundingBox[3] = new Point(children.get(0).getPosition().coordX + 1, children.get(0).getPosition().coordY + 1);
        for (Item child : children) {
            Point[] childBoundingBox = child.getBoundingBox();
            if (childBoundingBox[0].coordX < boundingBox[0].coordX) {
                boundingBox[0].coordX = childBoundingBox[0].coordX;
            }
            if (childBoundingBox[0].coordY < boundingBox[0].coordY) {
                boundingBox[0].coordY = childBoundingBox[0].coordY;
            }
            if (childBoundingBox[1].coordX > boundingBox[1].coordX) {
                boundingBox[1].coordX = childBoundingBox[1].coordX;
            }
            if (childBoundingBox[1].coordY < boundingBox[1].coordY) {
                boundingBox[1].coordY = childBoundingBox[1].coordY;
            }
            if (childBoundingBox[2].coordX < boundingBox[2].coordX) {
                boundingBox[2].coordX = childBoundingBox[2].coordX;
            }
            if (childBoundingBox[2].coordY > boundingBox[2].coordY) {
                boundingBox[2].coordY = childBoundingBox[2].coordY;
            }
            if (childBoundingBox[3].coordX > boundingBox[3].coordX) {
                boundingBox[3].coordX = childBoundingBox[3].coordX;
            }
            if (childBoundingBox[3].coordY > boundingBox[3].coordY) {
                boundingBox[3].coordY = childBoundingBox[3].coordY;
            }
        }
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
