public abstract class Item {
    
    public Point position;
    public abstract Point getPosition();
    public abstract Point[] getBoundingBox();
    public abstract void draw();
    public abstract void translate(Point p);


}
