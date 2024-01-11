import java.awt.Graphics;

public abstract class Item {

    protected int minX;
    protected int minY;
    protected int maxX;
    protected int maxY;
    
    public Point position;
    public Point[] boundingBox = new Point[4];
    public abstract Point getPosition();
    public abstract Point[] getBoundingBox();
    public abstract void draw();
    public abstract void draw(Graphics g);
    public abstract void translate(Point p);

    public boolean partOfComplex = false;


}
