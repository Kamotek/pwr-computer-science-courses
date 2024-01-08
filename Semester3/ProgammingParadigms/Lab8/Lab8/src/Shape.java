public abstract class Shape extends Primitive{

    public Point position;
    public boolean isFilled;
    public abstract Point getPosition();
    public abstract Point[] getBoundingBox();
    public abstract void draw();
    public abstract void translate(Point p);
    public abstract boolean getFilled();
}
