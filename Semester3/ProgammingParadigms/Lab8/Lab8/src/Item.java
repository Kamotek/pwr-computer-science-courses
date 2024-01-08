import java.awt.Graphics;

public abstract class Item {
    
    public Point position;
    public abstract Point getPosition();
    public abstract Point[] getBoundingBox();
    public abstract void draw();
    public abstract void draw(Graphics g);
    public abstract void translate(Point p);

    public boolean partOfComplex = false;


}
